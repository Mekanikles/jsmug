package jsmug.audio;

import java.nio.FloatBuffer;
import jsmug.Component;
import org.lwjgl.BufferUtils;

public class Sound extends Component {
	public enum Command {PLAY, RESUME, PAUSE, STOP, NONE};
	
	boolean isPlaying = false;
	boolean isPaused = false;
	boolean isStopped = true;
	boolean isStream = true;
	boolean isLooping = false;
	boolean eos = true;
	
	int source = -1;
	int stopSource = -1;
	int buffer = -1;
	
	double volume = 1.0f;
	double pitch = 1.0f;

	int sampleRate = 0;
	int bits = 0;
	int channels = 0;
        int type = 0;
	
	double fadeFrom = 0.0;
	double fadeTo = 0.0;
	double fadeTimer = 0.0;
	double fadeDuration = 0.0;
	
	private Command nextCommand = Command.NONE;
	
	PCMFloatChannel input;
	FloatBuffer data = null;
	
	public Sound(PCMFloatChannel input, int type) {
                this.type = type;
		this.input = input;
		this.eos = false;
		this.isStream = true;
		
		this.sampleRate = input.getSampleRate();
		this.bits = input.getBits();
		this.channels = input.getChannels();

	}
	
	public Sound(PCMFloatChannel input, int bufferSize, int type) {
                this.type = type;
		this.input = input;
		this.eos = false;
		this.isStream = false;
		
		this.sampleRate = input.getSampleRate();
		this.bits = input.getBits();
		this.channels = input.getChannels();

		FloatBuffer tmp = BufferUtils.createFloatBuffer(bufferSize);
		FloatBuffer tmp2;
		
		// Allocate tmp buffer for about 10s of data
		while(input.read(tmp) > 0) {
			tmp2 = BufferUtils.createFloatBuffer(tmp.limit()+bufferSize);
			tmp.flip();
			tmp2.put(tmp);
			tmp = tmp2;
		}
		
		// Force stream mode if we don't fit into one buffer
		if(tmp.position() >= bufferSize) {
			this.isStream = true;
		}

		this.input = null;
		this.data = tmp;
		this.data.flip();
	}

	public long read(FloatBuffer dst) {
		long length = 0;
		
		if(this.data != null) {
			if(!this.data.hasRemaining()) {
				this.eos = true;
				return -1;
			}
			
			while(dst.hasRemaining() && this.data.hasRemaining()) {
				dst.put(this.data.get()*((float)this.volume));
				length += 1;
			}
			
			if(!this.data.hasRemaining()) {
				this.eos = true;
			}
		} else {
			length = this.input.read(dst);
			
			if(length == -1) {
				this.eos = true;
			}
		}
		
		return length;
	}

	public int getStopSource() {
		return this.stopSource;
	}

	public void setStopSource(int stopSource) {
		this.stopSource = stopSource;
	}

	public int getSource() {
		return this.source;
	}

	public void setSource(int source) {
		this.source = source;
	}
	
	public int getBuffer() {
		return this.buffer;
	}

	public void setBuffer(int buffer) {
		this.buffer = buffer;
	}
	
	public void play() {
		this.nextCommand = Command.PLAY;
	}

	public void pause() {
		this.nextCommand = Command.PAUSE;
	}

	public void resume() {
		this.nextCommand = Command.RESUME;
	}

	public void stop() {
		this.nextCommand = Command.STOP;
	}

	public void setLooping(boolean looping) {
		this.isLooping = looping;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getVolume() {
		return this.volume;
	}

	public boolean isStream() {
		return this.isStream;
	}

	public boolean isLooping() {
		return this.isLooping;
	}

	public boolean isPlaying() {
		return this.isPlaying;
	}
	
	public boolean isPaused() {
		return this.isPaused;
	}
	
	public boolean isStopped() {
		return this.isStopped;
	}
	
	public boolean eos() {
		return this.eos;
	}

	public void reset() {
		if(this.input != null) {
			this.input.position(0);
		} 
		
		if(this.data != null) {
			this.data.position(0);
		}

		this.eos = false;
	}

	public boolean seek(int position) {
		if(this.data != null) {
			if(this.data.limit() >= position) {
				this.data.position(position);
				return true;
			} else {
				return false;
			}
		}

		if(this.input != null) {
			this.input.position(position);
			
			return this.input.position() == position;
		}
		
		return false;
	}

	public int getSampleRate() {
		return this.sampleRate;
	}

	public int getChannels() {
		return this.channels;
	}

	public int getBits() {
		return this.bits;
	}

	public void setPitch(double pitch) {
		this.pitch = pitch;
	}

	public double getPitch() {
		return this.pitch;
	}

	public void fadeIn(double duration) {
		this.play();
		this.volume = 0.0;
		this.fadeDuration = duration;
		this.fadeFrom = this.volume;
		this.fadeTo = 1.0;
		this.fadeTimer = 0.0;
	}

	public void fadeOut(double duration) {
		this.fadeTo(duration, 0.0);
	}

	public void fadeTo(double duration, double volume) {
		this.fadeDuration = duration;
		this.fadeFrom = this.volume;
		this.fadeTo = volume;
		this.fadeTimer = 0.0;
	}
	
	public void updateFade(double deltaTime) {
		if(this.fadeTimer < this.fadeDuration) {
			this.fadeTimer += deltaTime;
			this.volume = (this.fadeTo - this.fadeFrom)*this.fadeTimer/this.fadeDuration+this.fadeFrom;
		}
	}
	
	public Command popNextCommant() {
		Command command = this.nextCommand;
		this.nextCommand = Command.NONE;
		return command;
	}
	
	public void setIsPlaying(boolean isPlaying) {
		if(isPlaying) {
			this.isPaused = false;
			this.isStopped = false;
		}
		
		this.isPlaying = isPlaying;
	}

	public void setIsStopped(boolean isStopped) {
		if(isStopped) {
			this.isPaused = false;
			this.isPlaying = false;
		}
		
		this.isStopped = isStopped;
	}

	public void setIsPaused(boolean isPaused) {
		if(isPaused) {
			this.isPlaying = false;
			this.isStopped = false;
		}
		
		this.isPaused = isPaused;
	}
        
        public int type() {
            return this.type;
        }
}
