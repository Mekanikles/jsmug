/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsmug.audio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.util.WaveData;

/**
 *
 * @author anders
 */
public class WaveFloatChannel implements PCMFloatChannel {
    private boolean isOpen = false;
    private WaveData data = null;
    private long position = 0;
    private long size = 0;
    private ByteBuffer convertBuffer = null;
    
    public WaveFloatChannel(FileChannel input) {
        this.isOpen = input.isOpen();
        this.convertBuffer = ByteBuffer.allocate(4096);
        
        try {
            int bufferSize = 40960;
            ByteBuffer ioBuffer = ByteBuffer.allocate(bufferSize);
            ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
            ByteBuffer other, tmp;

            while(input.read(ioBuffer) != -1) {
                if(buffer.capacity()-buffer.position() < ioBuffer.limit()) {
                    bufferSize = bufferSize*2;
                    other = ByteBuffer.allocate(bufferSize);
                    buffer.flip();
                    other.put(buffer);
                    tmp = buffer;
                    buffer = other;
                    other = tmp;
                }
                
                buffer.put(ioBuffer);
                ioBuffer.flip();
            }
            
            buffer.flip();
            this.data = WaveData.create(buffer);
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(WaveFloatChannel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void close() {
        this.data.dispose();
        this.isOpen = false;
    }

    @Override
    public long position() {
        return this.position;
    }

    @Override
    public PCMFloatChannel position(long position) {
        this.position = position;
        return this;
    }

    @Override
    public long read(FloatBuffer dst) {
        return this.read(new FloatBuffer[]{dst}, 0, 1);
    }

    @Override
    public long read(FloatBuffer dst, long position) {
        // Save current position
        long oldPosition = this.position;

        // Move to given position
        this.position(position);

        // Read floats and save floats read
        long length = this.read(new FloatBuffer[]{dst}, 0, 1);

        // Reset position back to original
        this.position(oldPosition);

        return length;
    }

    @Override
    public long read(FloatBuffer[] dsts) {
        return this.read(dsts, 0, dsts.length);
    }

    @Override
    public long read(FloatBuffer[] dsts, int offset, int length) {
        long count = 0;
        long pos = 0;

        if(this.data.data.position() == this.data.data.limit()) {
            return -1;
        }
        
        for(int i=offset; i<length; i++) {
            if(i < dsts.length) {
                pos = dsts[i].position();
                
                this.convertBuffer.clear();
                while(this.data.data.hasRemaining() && (dsts[i].remaining()-this.convertBuffer.position()) > 0) {
                    if(this.convertBuffer.remaining() > 0) {
                        this.convertBuffer.put(this.data.data.get());
                    } else {
                        this.convertBuffer.flip();
                        PCMUtils.convertByteToFloat(this.convertBuffer, dsts[i]);
                        this.convertBuffer.clear();
                    }
                }

                PCMUtils.convertByteToFloat(this.convertBuffer, dsts[i]);
                count += (dsts[i].position() - pos);
            }
        }

        this.position += count;
        return count;
    }

    @Override
    public long size() {
        if(this.size == 0) {
            this.size = (this.data.data.limit()) / (this.getBits()/8 * this.getChannels());
        }
        
        return this.size;
    }

    @Override
    public boolean isOpen() {
        return this.isOpen;
    }

    @Override
    public int getSampleRate() {
        return this.data.samplerate;
    }

    @Override
    public int getChannels() {
        return 2;
    }

    @Override
    public int getBits() {
        return 16;
    }
    
}
