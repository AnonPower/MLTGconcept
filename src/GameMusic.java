import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

@SuppressWarnings("restriction")
public class GameMusic {
	private InputStream iN;
	private AudioStream aS;
	private File audioFolder;

	private int prevAudioPlayed = -1;

	public void initMusic(String dir) throws IOException, IOException {
		audioFolder = new File("data/MLTG/audio" + dir);
		File[] listOfAudioFiles = audioFolder.listFiles();

		try {
			iN = new FileInputStream(listOfAudioFiles[grabRanMusicFromFolder()]);
			aS = new AudioStream(iN);
		} catch (Exception e) {
			System.out.print("\nAudio failed to load.\n");
		}
	}
	public void musicControl(String comm) throws IOException {
		try {
			if (comm.equals("start")) {
				AudioPlayer.player.start(aS);
			} else {
				if (comm.equals("stop")) {
					try {
						AudioPlayer.player.stop(aS);
					} catch (Exception e) {
					}
				}
			}
		} catch (Exception e) {
			System.out.print("\nAudio failed to load.\n");
		}
	}
	public int grabRanMusicFromFolder() {
		RanGen rG = new RanGen();

		int audioAmt = audioFolder.listFiles().length;
		int audioToPlay = -1;

		do {
			rG.setRanNum(audioAmt);
			audioToPlay = rG.getRanNum();
		} while (audioToPlay == prevAudioPlayed);

		if (audioToPlay == audioFolder.listFiles().length) {
			audioToPlay += -1;
		}

		prevAudioPlayed = audioToPlay;

		return audioToPlay;
	}
}
