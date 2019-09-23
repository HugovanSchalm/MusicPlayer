package files;

import java.util.ArrayList;

import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Queue{
	private ArrayList<Song> songs = new ArrayList<Song>();
	private Song nowPlaying;
	private int index = 0;
	
	
	public Queue(Song[] inputSongs){
		for(Song song : inputSongs) { 
			try {
				if(song.getName().substring(song.getName().lastIndexOf(".")).contains(".mp3")) {
					System.out.println(song.getName() + " is an mp3 file");
					songs.add(song);
				}
			} catch (Exception e) {
				System.out.println(" ERROR: couldn't get extension of file: " + song.getPath());
			}
		}
	}
	
	public void start() {
		songs.get(index).play();
		nowPlaying = songs.get(index);
		nowPlaying.getMediaPlayer().setOnEndOfMedia(() -> {
			skip();
		});
		
		nowPlaying.getMediaPlayer().currentTimeProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
				Main.controller.setDurationSlider(nowPlaying.getMediaPlayer().getCurrentTime().toSeconds() / nowPlaying.getMediaPlayer().getTotalDuration().toSeconds() * 100  );	
				
				String minutes = String.valueOf((int)nowPlaying.mediaPlayer.getCurrentTime().toMinutes());
				if((int)nowPlaying.mediaPlayer.getCurrentTime().toMinutes() < 10) {
					minutes = "0" + minutes;
				}
				
				String seconds = String.valueOf((int)nowPlaying.getMediaPlayer().getCurrentTime().toSeconds() - (int) nowPlaying.getMediaPlayer().getCurrentTime().toMinutes() * 60);
				if((int)nowPlaying.getMediaPlayer().getCurrentTime().toSeconds() - (int) nowPlaying.getMediaPlayer().getCurrentTime().toMinutes() * 60 < 10) {
					seconds = "0" + seconds;
				}
				Main.controller.getCurrentDuration().setText(minutes + ":" + seconds);
				
				String totalMinutes = String.valueOf((int)nowPlaying.mediaPlayer.getTotalDuration().toMinutes());
				if((int)nowPlaying.getMediaPlayer().getTotalDuration().toMinutes() < 10) {
					totalMinutes = "0" + totalMinutes;
				}
				
				String totalSeconds = String.valueOf((int)nowPlaying.getMediaPlayer().getTotalDuration().toSeconds() - (int)nowPlaying.getMediaPlayer().getTotalDuration().toMinutes() * 60);
				if((int)nowPlaying.getMediaPlayer().getTotalDuration().toSeconds() < 10 - (int) nowPlaying.getMediaPlayer().getTotalDuration().toMinutes() * 60) {
					totalSeconds = "0" + totalSeconds;
				}
				
				Main.controller.getTotalDuration().setText(totalMinutes + ":" + totalSeconds); 
			}
			
		});
		
		
		
		
	}
	
	public void skip() {
		
		nowPlaying.getMediaPlayer().dispose();
		
		if(index >= songs.size() -1) {index=0;}else {index++;}
		
		songs.get(index).play();
		nowPlaying = songs.get(index);
		getNowPlaying().getMediaPlayer().setVolume(Main.controller.getVolumeSliderValue()/100);
		Main.controller.setLabelText("Now Playing: " + nowPlaying.getName());
		nowPlaying.getMediaPlayer().setOnEndOfMedia(() -> {
			skip();
		});
		nowPlaying.getMediaPlayer().currentTimeProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
				Main.controller.setDurationSlider(nowPlaying.getMediaPlayer().getCurrentTime().toSeconds() / nowPlaying.getMediaPlayer().getTotalDuration().toSeconds() * 100  );	
				
				String minutes = String.valueOf((int)nowPlaying.mediaPlayer.getCurrentTime().toMinutes());
				if((int)nowPlaying.mediaPlayer.getCurrentTime().toMinutes() < 10) {
					minutes = "0" + minutes;
				}
				
				String seconds = String.valueOf((int)nowPlaying.getMediaPlayer().getCurrentTime().toSeconds() - (int) nowPlaying.getMediaPlayer().getCurrentTime().toMinutes() * 60);
				if((int)nowPlaying.getMediaPlayer().getCurrentTime().toSeconds() - (int) nowPlaying.getMediaPlayer().getCurrentTime().toMinutes() * 60 < 10) {
					seconds = "0" + seconds;
				}
				Main.controller.getCurrentDuration().setText(minutes + ":" + seconds);
				
				String totalMinutes = String.valueOf((int)nowPlaying.mediaPlayer.getTotalDuration().toMinutes());
				if((int)nowPlaying.getMediaPlayer().getTotalDuration().toMinutes() < 10) {
					totalMinutes = "0" + totalMinutes;
				}
				
				String totalSeconds = String.valueOf((int)nowPlaying.getMediaPlayer().getTotalDuration().toSeconds() - (int)nowPlaying.getMediaPlayer().getTotalDuration().toMinutes() * 60);
				if((int)nowPlaying.getMediaPlayer().getTotalDuration().toSeconds() < 10 - (int) nowPlaying.getMediaPlayer().getTotalDuration().toMinutes() * 60) {
					totalSeconds = "0" + totalSeconds;
				}
				
				Main.controller.getTotalDuration().setText(totalMinutes + ":" + totalSeconds); 
			}
			
		});
	}
	
	public void rewind() {
		
		nowPlaying.getMediaPlayer().dispose();
		
		if(index != 0) {index--;} else {index = songs.size() - 1;}
		
		songs.get(index).play();
		nowPlaying = songs.get(index);
		getNowPlaying().getMediaPlayer().setVolume(Main.controller.getVolumeSliderValue()/100);
		Main.controller.setLabelText("Now Playing: " + nowPlaying.getName());
		nowPlaying.getMediaPlayer().setOnEndOfMedia(() -> {
			skip();
		});
		nowPlaying.getMediaPlayer().currentTimeProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
				Main.controller.setDurationSlider(nowPlaying.getMediaPlayer().getCurrentTime().toSeconds() / nowPlaying.getMediaPlayer().getTotalDuration().toSeconds() * 100  );	
				
				String minutes = String.valueOf((int)nowPlaying.mediaPlayer.getCurrentTime().toMinutes());
				if((int)nowPlaying.mediaPlayer.getCurrentTime().toMinutes() < 10) {
					minutes = "0" + minutes;
				}
				
				String seconds = String.valueOf((int)nowPlaying.getMediaPlayer().getCurrentTime().toSeconds() - (int) nowPlaying.getMediaPlayer().getCurrentTime().toMinutes() * 60);
				if((int)nowPlaying.getMediaPlayer().getCurrentTime().toSeconds() - (int) nowPlaying.getMediaPlayer().getCurrentTime().toMinutes() * 60 < 10) {
					seconds = "0" + seconds;
				}
				Main.controller.getCurrentDuration().setText(minutes + ":" + seconds);
				
				String totalMinutes = String.valueOf((int)nowPlaying.mediaPlayer.getTotalDuration().toMinutes());
				if((int)nowPlaying.getMediaPlayer().getTotalDuration().toMinutes() < 10) {
					totalMinutes = "0" + totalMinutes;
				}
				
				String totalSeconds = String.valueOf((int)nowPlaying.getMediaPlayer().getTotalDuration().toSeconds() - (int)nowPlaying.getMediaPlayer().getTotalDuration().toMinutes() * 60);
				if((int)nowPlaying.getMediaPlayer().getTotalDuration().toSeconds() < 10 - (int) nowPlaying.getMediaPlayer().getTotalDuration().toMinutes() * 60) {
					totalSeconds = "0" + totalSeconds;
				}
				
				Main.controller.getTotalDuration().setText(totalMinutes + ":" + totalSeconds); 
			}
			
		});
	}
	
	public Song getNowPlaying() {
		return nowPlaying;
	}

}
