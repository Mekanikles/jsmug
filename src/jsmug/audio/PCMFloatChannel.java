package jsmug.audio;

import java.nio.FloatBuffer;
import java.nio.channels.Channel;

public interface PCMFloatChannel extends Channel {
	public void close();

        public int getSampleRate();
	public int getChannels();
	public int getBits();

        public long position();
	public PCMFloatChannel position(long position);

	public long read(FloatBuffer dst);
	public long read(FloatBuffer dst, long position);
	public long read(FloatBuffer dsts[]);
	public long read(FloatBuffer dsts[], int offset, int length);
	
	public long size();
}
