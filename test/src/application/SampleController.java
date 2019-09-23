package application;


import java.io.IOException;
import java.net.URISyntaxException;

import com.jfoenix.controls.JFXSlider;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class SampleController {
	
	boolean addedListener;
	
	@FXML
    private Label nowPlayingText;
	
	@FXML
    private Label totalDuration;
	
	@FXML
	private Label currentDuration;

    @FXML
    private JFXSlider volumeSlider;
    
    @FXML
    private JFXSlider durationSlider;
    
    @FXML
    private ImageView playIcon;
    
    @FXML ImageView albumCover;
    
    public void initialize() throws URISyntaxException, UnsupportedTagException, InvalidDataException, IOException{
    	nowPlayingText.setText("Now Playing: " + Main.queue.getNowPlaying().getName());
    	setPlayIcon();
    	
    	//albumCover.setImage(new Image(Main.queue.getNowPlaying().getAlbumArt()));
    	
    	volumeSlider.valueProperty().addListener(new ChangeListener<Object>(){

			@Override
			public void changed(ObservableValue<?extends Object> arg0, Object arg1, Object arg2) {
				Main.queue.getNowPlaying().getMediaPlayer().setVolume(volumeSlider.getValue()/100);
				
			}
    		
    	});
    	
    	addedListener = false;
    	
    	durationSlider.setOnMouseDragged(new EventHandler<MouseEvent>() {
    		
    		
    		
    		@Override
			public void handle(MouseEvent e) {
    			
    			
    			if(!addedListener) {
	    			ChangeListener<Object> changeListener = new ChangeListener<Object>() {
	
	    				@Override
	    				public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
	    					Main.queue.getNowPlaying().getMediaPlayer().pause();
	    					Main.queue.getNowPlaying().getMediaPlayer().seek(Duration.seconds(durationSlider.getValue() / 100 * Main.queue.getNowPlaying().getMediaPlayer().getTotalDuration().toSeconds()));
	    					
	    					
	    				}
	
	    				
	        			
	        		};
	        		
	        		durationSlider.valueProperty().addListener(changeListener);
	    			
	    			durationSlider.setOnMouseReleased(event -> {
						durationSlider.valueProperty().removeListener(changeListener);
						Main.queue.getNowPlaying().getMediaPlayer().play();
						addedListener = false;
						event.consume();
					});
    			}
    			
    			addedListener = true;
    			
    			
    			
    			
			}
    	});
    }

    @FXML
    void onClickPlay(ActionEvent event) throws URISyntaxException {
    	if(Main.queue.getNowPlaying().getMediaPlayer().getStatus().equals(Status.PLAYING)) {
    		Main.queue.getNowPlaying().getMediaPlayer().pause();
    	}
    	else {
    		Main.queue.getNowPlaying().getMediaPlayer().play();
    	}
    	setPlayIcon();

    }

    @FXML
    void onClickReverse(ActionEvent event) {
    	Main.queue.rewind();
    	nowPlayingText.setText("Now Playing: " + Main.queue.getNowPlaying().getName());

    }

    @FXML
    void onClickSkip(ActionEvent event) {
    	Main.queue.skip();
    	nowPlayingText.setText("Now Playing: " + Main.queue.getNowPlaying().getName());

    }
    
    public void setLabelText(String text) {
    	nowPlayingText.setText(text);
    }
    
    public double getVolumeSliderValue() {
    	return volumeSlider.getValue();
    }
    
    public void setPlayIcon() throws URISyntaxException {
    	if(Main.queue.getNowPlaying().getMediaPlayer().getStatus().equals(Status.PLAYING)) {
    		playIcon.setImage(new Image(getClass().getResource("/res/img/play.png").toURI().toString()));
    	}
    	else {
    		playIcon.setImage(new Image(getClass().getResource("/res/img/pause.png").toURI().toString()));
    	}
    }
    
    public void setDurationSlider(double value) {
    	durationSlider.adjustValue(value);
    }

    
    public Label getTotalDuration() {
    	return totalDuration;
    }
    
    public Label getCurrentDuration() {
    	return currentDuration;
    }
}
