import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashMap;

public class Assets {

    /**
     * Retrieves the background images used in the game.
     *
     * @return a HashMap containing the background images
     */
    public static HashMap<Integer, ImageView> getBackGrounds(){
        HashMap<Integer,ImageView> backGrounds = new HashMap<>();
        backGrounds.put(1,new ImageView("assets/background/1.png"));
        backGrounds.put(2,new ImageView("assets/background/2.png"));
        backGrounds.put(3,new ImageView("assets/background/3.png"));
        backGrounds.put(4,new ImageView("assets/background/4.png"));
        backGrounds.put(5,new ImageView("assets/background/5.png"));
        backGrounds.put(6,new ImageView("assets/background/6.png"));
        backGrounds.forEach((integer, imageView) -> {
            imageView.setFitHeight(imageView.getImage().getHeight()*Scaler.SCALE);
            imageView.setFitWidth(imageView.getImage().getWidth()*Scaler.SCALE);
        });
        return backGrounds;
    }

    /**
     * Retrieves the crosshair images used in the game.
     *
     * @return a HashMap containing the crosshair images
     */
    public static HashMap<Integer, Image> getCrossHairs(){
        HashMap<Integer,Image> crossHairs = new HashMap<>();
        crossHairs.put(1,new Image("assets/crosshair/1.png"));
        crossHairs.put(2,new Image("assets/crosshair/2.png"));
        crossHairs.put(3,new Image("assets/crosshair/3.png"));
        crossHairs.put(4,new Image("assets/crosshair/4.png"));
        crossHairs.put(5,new Image("assets/crosshair/5.png"));
        crossHairs.put(6,new Image("assets/crosshair/6.png"));
        crossHairs.put(7,new Image("assets/crosshair/7.png"));
        return crossHairs;
    }

    /**
     * Retrieves the foreground images used in the game.
     *
     * @return a HashMap containing the foreground images
     */
    public static HashMap<Integer, ImageView> getForeGrounds(){
        HashMap<Integer,ImageView> foreGrounds = new HashMap<>();
        foreGrounds.put(1,new ImageView("assets/foreground/1.png"));
        foreGrounds.put(2,new ImageView("assets/foreground/2.png"));
        foreGrounds.put(3,new ImageView("assets/foreground/3.png"));
        foreGrounds.put(4,new ImageView("assets/foreground/4.png"));
        foreGrounds.put(5,new ImageView("assets/foreground/5.png"));
        foreGrounds.put(6,new ImageView("assets/foreground/6.png"));
        foreGrounds.forEach((integer, imageView) -> {
            imageView.setFitHeight(imageView.getImage().getHeight()*Scaler.SCALE);
            imageView.setFitWidth(imageView.getImage().getWidth()*Scaler.SCALE);
        });
        return foreGrounds;
    }

    /**
     * Retrieves the black duck images used in the game.
     *
     * @return a HashMap containing the black duck images
     */
    public static HashMap<Integer,ImageView> getBlackDuck(){
        HashMap<Integer,ImageView> blackDuck = new HashMap<>();
        blackDuck.put(1,new ImageView("assets/duck_black/1.png"));
        blackDuck.put(2,new ImageView("assets/duck_black/2.png"));
        blackDuck.put(3,new ImageView("assets/duck_black/3.png"));
        blackDuck.put(4,new ImageView("assets/duck_black/4.png"));
        blackDuck.put(5,new ImageView("assets/duck_black/5.png"));
        blackDuck.put(6,new ImageView("assets/duck_black/6.png"));
        blackDuck.put(7,new ImageView("assets/duck_black/7.png"));
        blackDuck.put(8,new ImageView("assets/duck_black/8.png"));
        blackDuck.forEach((integer, imageView) -> {
            imageView.setFitHeight(imageView.getImage().getHeight()*Scaler.SCALE);
            imageView.setFitWidth(imageView.getImage().getWidth()*Scaler.SCALE);
        });
        return blackDuck;
    }

    /**
     * Retrieves the blue duck images used in the game.
     *
     * @return a HashMap containing the blue duck images
     */
    public static HashMap<Integer,ImageView> getBlueDuck(){
        HashMap<Integer,ImageView> blueDuck = new HashMap<>();
        blueDuck.put(1,new ImageView("assets/duck_blue/1.png"));
        blueDuck.put(2,new ImageView("assets/duck_blue/2.png"));
        blueDuck.put(3,new ImageView("assets/duck_blue/3.png"));
        blueDuck.put(4,new ImageView("assets/duck_blue/4.png"));
        blueDuck.put(5,new ImageView("assets/duck_blue/5.png"));
        blueDuck.put(6,new ImageView("assets/duck_blue/6.png"));
        blueDuck.put(7,new ImageView("assets/duck_blue/7.png"));
        blueDuck.put(8,new ImageView("assets/duck_blue/8.png"));
        blueDuck.forEach((integer, imageView) -> {
            imageView.setFitHeight(imageView.getImage().getHeight()*Scaler.SCALE);
            imageView.setFitWidth(imageView.getImage().getWidth()*Scaler.SCALE);
        });
        return blueDuck;
    }

    /**
     * Retrieves the red duck images used in the game.
     *
     * @return a HashMap containing the red duck images
     */
    public static HashMap<Integer, ImageView> getRedDuck(){
        HashMap<Integer,ImageView> redDuck = new HashMap<>();
        redDuck.put(1,new ImageView("assets/duck_red/1.png"));
        redDuck.put(2,new ImageView("assets/duck_red/2.png"));
        redDuck.put(3,new ImageView("assets/duck_red/3.png"));
        redDuck.put(4,new ImageView("assets/duck_red/4.png"));
        redDuck.put(5,new ImageView("assets/duck_red/5.png"));
        redDuck.put(6,new ImageView("assets/duck_red/6.png"));
        redDuck.put(7,new ImageView("assets/duck_red/7.png"));
        redDuck.put(8,new ImageView("assets/duck_red/8.png"));
        redDuck.forEach((integer, imageView) -> {
            imageView.setFitHeight(imageView.getImage().getHeight()*Scaler.SCALE);
            imageView.setFitWidth(imageView.getImage().getWidth()*Scaler.SCALE);
        });
        return redDuck;
    }

    /**
     * Retrieves the media players used for sound effects in the game.
     *
     * @return a HashMap containing the media players for sound effects
     */
    public static HashMap<String, MediaPlayer> getMediaPlayers(){
        HashMap<String,MediaPlayer> mediaPlayers = new HashMap<>();
        Media media = new Media(Assets.class.getResource("assets/effects/Title.mp3").toExternalForm());
        Media media1 = new Media(Assets.class.getResource("assets/effects/Intro.mp3").toExternalForm());
        Media media2 = new Media(Assets.class.getResource("assets/effects/LevelCompleted.mp3").toExternalForm());
        Media media3 = new Media(Assets.class.getResource("assets/effects/GameOver.mp3").toExternalForm());
        Media media4 = new Media(Assets.class.getResource("assets/effects/Gunshot.mp3").toExternalForm());
        Media media5 = new Media(Assets.class.getResource("assets/effects/DuckFalls.mp3").toExternalForm());
        Media media6 = new Media(Assets.class.getResource("assets/effects/GameCompleted.mp3").toExternalForm());
        mediaPlayers.put("Title",new MediaPlayer(media));
        mediaPlayers.put("Intro",new MediaPlayer(media1));
        mediaPlayers.put("LevelCompleted",new MediaPlayer(media2));
        mediaPlayers.put("GameOver",new MediaPlayer(media3));
        mediaPlayers.put("Gunshot",new MediaPlayer(media4));
        mediaPlayers.put("DuckFalls",new MediaPlayer(media5));
        mediaPlayers.put("GameCompleted",new MediaPlayer(media6));

        mediaPlayers.forEach((s, mediaPlayer)  -> mediaPlayer.setVolume(Volume.getVOLUME()));
        return mediaPlayers;



        







    }
}
