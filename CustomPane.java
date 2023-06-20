import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.StackPane;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;

import javafx.scene.text.Text;

import javafx.util.Duration;

import java.util.ArrayList;

/**
 * CustomPane is a custom implementation of StackPane in JavaFX.
 * It displays an image, plays a video, and provides fade animation for text elements.
 */
public class CustomPane extends StackPane {
    private final ArrayList<Text> textArrayList;
    private final Image image;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;

    /**
     * Constructs a new instance of CustomPane.
     * Sets up the image, media player, and text elements.
     */
    public CustomPane(){
        textArrayList = new ArrayList<>();
        setAlignment(Pos.CENTER);
        // Set the background image
        image = new Image("assets/welcome/1.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(image.getWidth()*Scaler.SCALE);
        imageView.setFitHeight(image.getHeight()*Scaler.SCALE);
        imageView.setPreserveRatio(true);

        // Set the media player
        mediaPlayer = Assets.getMediaPlayers().get("Title");
        mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(-1);

        // Add text elements
        addText("PRESS ENTER TO PLAY",60*Scaler.SCALE,15*Scaler.SCALE);
        addText("PRESS ESC TO EXIT",90*Scaler.SCALE,15*Scaler.SCALE);
        // Apply fade animation to text elements
        textArrayList.forEach(this::fadeAnimationForText);

        // Add the image, media player, and text elements to the pane
        getChildren().addAll(imageView,mediaView,textArrayList.get(0),textArrayList.get(1));

    }

    /**
     * Adds a text element to the CustomPane.
     *
     * @param text the text content
     * @param y    the vertical position of the text
     * @param size the font size of the text
     */
    public void addText(String text ,double y,double size){
        Text textDeneme = new Text(text);
        textDeneme.setFont(Font.font("New Times Roman",size));
        textDeneme.setFill(Color.YELLOW);
        setMargin(textDeneme, new Insets(y,0,0,0));
        textArrayList.add(textDeneme);
    }

    /**
     * Applies fade animation to a text element.
     *
     * @param text the text element to animate
     */
    public void fadeAnimationForText(Text text){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), text);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }

    /**
     * Returns the image displayed in the CustomPane.
     *
     * @return the image displayed in the CustomPane
     */
    public Image getImage() {
        return image;
    }

    /**
     * Returns the media player used in the CustomPane.
     *
     * @return the media player used in the CustomPane
     */
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * Sets the media player for the CustomPane.
     *
     * @param mediaPlayer the media player to set
     */
    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
}
