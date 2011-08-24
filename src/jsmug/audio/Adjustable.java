package jsmug.audio;

public interface Adjustable {
	public void setVolume(double volume);
	public double getVolume();
	public void setPitch(double pitch);	
	public double getPitch();
	
	public void fadeIn(double duration);
	public void fadeOut(double duration);
	public void fadeTo(double duration, double volume);
}
