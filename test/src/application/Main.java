package application;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.swing.JFileChooser;

import files.Folder;
import files.Queue;
import files.Song;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private final String TITLE = "Music Player | V0.1";
	
	static Folder folder;
	public static Queue queue;
	MediaPlayer mediaPlayer;
	public static SampleController controller;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle(TITLE);
			
			queue = playSongs();
			queue.start();
			mediaPlayer = queue.getNowPlaying().getMediaPlayer();
			
			mediaPlayer.setOnEndOfMedia(() -> {
				queue.skip();
				mediaPlayer = queue.getNowPlaying().getMediaPlayer();
			});
			
			controller = new SampleController();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sample.fxml"));
			fxmlLoader.setController(controller);
			VBox root = fxmlLoader.load();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			
			primaryStage.setMinWidth(900);
			primaryStage.setMinHeight(600);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogTitle("Select the folder to be scanned for music");
		chooser.showOpenDialog(null);
		folder = new Folder(chooser.getSelectedFile().getPath());
		
		launch(args);
	}
	
	public Queue playSongs() {
		int i = 0;
		Song[] songs = new Song[folder.getFiles().length];
		
		for(String path : folder.getFiles()) {
			try {
				songs[i++] = new Song(path, URLDecoder.decode(new File(path).getName(), StandardCharsets.UTF_8.toString()));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		Queue q = new Queue(songs);
		
		
		return q;
	}
}
