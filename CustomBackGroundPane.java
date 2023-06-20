import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class CustomBackGroundPane extends StackPane {
   private ImageView imageView;
   private int backGroundKey;
   private int crossHairKey;
   private Image crossHairImage;
   private ImageCursor imageCursor;
   private final ArrayList<Text> textArrayList;

    /**
     * Constructs a new CustomBackGroundPane with background image, media player, and text elements.
     */
    public CustomBackGroundPane(){
        textArrayList = new ArrayList<>();
        backGroundKey = 1;
        crossHairKey = 1;

        // Set the initial background image
        imageView = Assets.getBackGrounds().get(1);
        imageView.setPreserveRatio(true);

        // Set the initial image cursor
        imageCursor = new ImageCursor(crossHairImage);

        // Set cursor behavior on mouse events
        setOnMouseEntered(event -> setCursor(imageCursor));
        setOnMouseExited(event -> setCursor(Cursor.DEFAULT));

        // Create and configure the media player
        Media media = new Media(getClass().getResource("assets/effects/Intro.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(Volume.getVOLUME());
        mediaPlayer.setCycleCount(-1);
        MediaView mediaView = new MediaView(mediaPlayer);

        // Add text elements
        addText("USE ARROW KEYS TO NAVIGATE", -200*Scaler.SCALE,10*Scaler.SCALE);
        addText("PRESS ENTER TO START",-180*Scaler.SCALE,10*Scaler.SCALE);
        addText("PRESS ESC TO EXIT",-160*Scaler.SCALE,10*Scaler.SCALE);

        // Add the media player and text elements to the pane
        getChildren().addAll(mediaView,textArrayList.get(0),textArrayList.get(1),textArrayList.get(2));
    }

    /**
     * Changes the background image of the CustomBackGroundPane.
     */
    public void changeImageView(){
        imageView = Assets.getBackGrounds().get(backGroundKey);
        imageView.setPreserveRatio(true);
        getChildren().removeIf(node -> node instanceof ImageView);
        getChildren().add(0,imageView);
    }

    /**
     * Changes the image cursor of the CustomBackGroundPane.
     */
    public void changeImageCursor() {
        imageCursor = new ImageCursor(Assets.getCrossHairs().get(crossHairKey));
        setCursor(imageCursor);
    }

    /**
     * Changes the backGroundKey based on the given KeyCode.
     *
     * @param keyCode the KeyCode representing the key pressed.
     */
    public void changeBackGroundKey(KeyCode keyCode) {
        if(backGroundKey == 6 && keyCode == KeyCode.RIGHT){
            backGroundKey = 1;
        }else if(keyCode == KeyCode.RIGHT){
            backGroundKey +=1;
        }
        if(backGroundKey == 1 && keyCode == KeyCode.LEFT){
            backGroundKey = 6;
        }else if(keyCode == KeyCode.LEFT){
            backGroundKey -= 1;
        }
    }

    /**
     * Changes the crossHairKey based on the given KeyCode.
     *
     * @param keyCode the KeyCode representing the key pressed.
     */
    public void changeCrossHairKey(KeyCode keyCode) {
        if(crossHairKey == 7 && keyCode == KeyCode.UP){
            crossHairKey = 1;
        }else if(keyCode == KeyCode.UP){
            crossHairKey +=1;
        }
        if(crossHairKey == 1 && keyCode == KeyCode.DOWN){
            crossHairKey = 7;
        }else if(keyCode == KeyCode.DOWN){
            crossHairKey -= 1;
        }
    }

    /**
     * Returns the current backGroundKey.
     *
     * @return the current backGroundKey.
     */
    public int getBackGroundKey(){
        return backGroundKey;
    }

    /**
     * Returns the current crossHairKey.
     *
     * @return the current crossHairKey.
     */
    public int getCrossHairKey() {
        return crossHairKey;
    }

    /**
     * Adds a text to the CustomBackGroundPane.
     *
     * @param text the text to be added.
     * @param y the y-coordinate position of the text.
     * @param size the font size of the text.
     */
    public void addText(String text , double y, double size){
        Text textDeneme = new Text(text);
        textDeneme.setFont(Font.font("New Times Roman",size));
        textDeneme.setFill(Color.YELLOW);
        setMargin(textDeneme, new Insets(y,0,0,0));
        textArrayList.add(textDeneme);
    }

    /**
     * Resets the background image and backGroundKey to their initial values.
     */
    public void resetBackGround(){
        imageView = Assets.getBackGrounds().get(1);
        getChildren().removeIf(node -> node instanceof ImageView);
        getChildren().add(0,imageView);
        backGroundKey = 1;
    }

    /**
     * Resets the image cursor and crossHairKey to their initial values.
     */
    public void resetCrossHair(){
        imageCursor = new ImageCursor(Assets.getCrossHairs().get(1));
        crossHairKey = 1;
    }

}
