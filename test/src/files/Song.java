package files;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import javax.imageio.ImageIO;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Song {
	private String path;
	private String name;
	MediaPlayer mediaPlayer;
	
	public Song(String path, String name) {
		this.path = path;
		this.name = name;
	}
	
	public void play() {
		System.out.println("Now Playing song at \"" + path + "\"");
		Media media = new Media(path);
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
	
	public String getAlbumArt() throws UnsupportedTagException, InvalidDataException, IOException {
		Mp3File song = new Mp3File(new File("c://Users/hugov/Documents/Muziek/X/ecstasy.mp3").getAbsolutePath());
		ID3v2 tag = song.getId3v2Tag();
		byte[] data = tag.getAlbumImage();
		File file = new File("img.jpg");
		file.createNewFile();
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
		ImageIO.write(img, "jpg", file);
		return file.toURI().toString();
	}

}
