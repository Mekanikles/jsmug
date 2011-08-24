package jsmug.audio;

public interface Audio {
	public boolean init();
	public void finish();
	public String getDefaultDeviceName();
	public String[] getDeviceNames();
	public Sound newSound(String filename, int type);
	public Sound newSound(PCMFloatChannel input, int type);
	public Sound newSoundStream(String filename, int type);
	public Sound newSoundStream(PCMFloatChannel input, int type);
	public void update(double deltaTime, double[] volumes);
}
