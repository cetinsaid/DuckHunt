import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a custom stage for the game.
 */
public class CustomStage extends Stage {
    private final CustomPane customPane;
    private final CustomBackGroundPane customBackGroundPane;
    private final CustomGamePane customGamePane;
    private final Scene scene;
    private final Scene gameScene;

    /**
     * Constructs a new CustomStage with custom panes and scenes.
     */
    public CustomStage(){
        Image icon = new Image("assets/favicon/1.png");
        String gameName = "HUBBM Duck Hunt";

        customPane = new CustomPane();
        customBackGroundPane = new CustomBackGroundPane();
        customGamePane = new CustomGamePane();

        scene = new Scene(customPane,customPane.getImage().getWidth()*Scaler.SCALE,customPane.getImage().getHeight()*Scaler.SCALE);
        gameScene = new Scene(customGamePane,customPane.getImage().getWidth()*Scaler.SCALE,customPane.getImage().getHeight()*Scaler.SCALE);
        setScene(scene);
        setResizable(false);
        getIcons().add(icon);
        setTitle(gameName);

        // Handle key events in the scene
        scene.setOnKeyPressed(event -> {
            if(scene.getRoot() == customPane){
                switch (event.getCode()){
                    case ENTER:
                        customBackGroundPane.resetBackGround();
                        customBackGroundPane.resetCrossHair();
                        scene.setRoot(customBackGroundPane);
                        break;
                    case ESCAPE:
                        close();}
            }else if(scene.getRoot() ==customBackGroundPane){
                switch (event.getCode()){
                    case RIGHT:
                        customBackGroundPane.changeBackGroundKey(KeyCode.RIGHT);
                        customBackGroundPane.changeImageView();
                        break;
                    case LEFT:
                        customBackGroundPane.changeBackGroundKey(KeyCode.LEFT);
                        customBackGroundPane.changeImageView();
                        break;
                    case ENTER:
                        customGamePane.setBackGround(customBackGroundPane.getBackGroundKey(),customBackGroundPane.getCrossHairKey());
                        customGamePane.setLevel(1);
                        changeMedia("Intro",1);
                        customPane.getMediaPlayer().setOnEndOfMedia(() -> {
                            setScene(gameScene);
                            customGamePane.setWin(false);
                            customGamePane.setLost(false);
                        });
                        break;
                    case ESCAPE:
                        scene.setRoot(customPane);
                    case UP:
                        customBackGroundPane.changeCrossHairKey(KeyCode.UP);
                        customBackGroundPane.changeImageCursor();
                        break;
                    case DOWN:
                        customBackGroundPane.changeCrossHairKey(KeyCode.DOWN);
                        customBackGroundPane.changeImageCursor();
                        break;

                }
            }
        });

        AtomicBoolean keepHandleMouse = new AtomicBoolean(true);
        gameScene.setOnMouseClicked(event -> {
            if(keepHandleMouse.get()){
                customGamePane.decreaseLevelAmmo();
                customGamePane.getTextArrayList().get(12).setText("Ammo Left: "+ customGamePane.getLevelAmmo());
                playMedia("Gunshot");
                if(customGamePane.getLevel()==6 && customGamePane.isWin()){
                    MediaPlayer mediaPlayer = playMedia("GameCompleted");
                    customGamePane.setLevel("Game Completed");
                    keepHandleMouse.set(false);
                    gameScene.setOnKeyPressed(event1 -> {
                        if(event1.getCode() == KeyCode.ENTER && customGamePane.isWin()){
                            mediaPlayer.stop();
                            customGamePane.resetLevel();
                            customGamePane.setLevel(customGamePane.getLevel());
                            keepHandleMouse.set(true);
                        }else if(event1.getCode() == KeyCode.ESCAPE && customGamePane.isWin()){
                            mediaPlayer.stop();
                            customGamePane.resetLevel();
                            scene.setRoot(customPane);
                            setScene(scene);
                            keepHandleMouse.set(true);
                            changeMedia("Title",-1);
                        }
                    });
                }
                else if(customGamePane.isWin()){
                   MediaPlayer mediaPlayer = playMedia("LevelCompleted");
                    customGamePane.setLevel("Win");
                    keepHandleMouse.set(false);
                    gameScene.setOnKeyPressed(event1 -> {
                        if(event1.getCode() == KeyCode.ENTER && customGamePane.isWin()){
                            mediaPlayer.stop();
                            customGamePane.increaseLevel();
                            customGamePane.setLevel(customGamePane.getLevel());
                            customGamePane.setWin(false);
                            keepHandleMouse.set(true);
                        }
                    });
                }else if(customGamePane.isLost()){
                    MediaPlayer mediaPlayer = playMedia("GameOver");
                    customGamePane.setLevel("Lost");
                    keepHandleMouse.set(false);
                    gameScene.setOnKeyPressed(event1 -> {
                        if(event1.getCode() == KeyCode.ENTER && customGamePane.isLost()){
                            mediaPlayer.stop();
                            customGamePane.resetLevel();
                            customGamePane.setLevel(customGamePane.getLevel());
                            customGamePane.setLost(false);
                            keepHandleMouse.set(true);
                        }else if(event1.getCode() == KeyCode.ESCAPE && customGamePane.isLost()){
                            mediaPlayer.stop();
                            customGamePane.resetLevel();
                            customGamePane.setLost(false);
                            keepHandleMouse.set(true);
                            scene.setRoot(customPane);
                            setScene(scene);
                            changeMedia("Title",-1);
                        }
                    });
                }
            }
        });


    }

    /**
     * Changes the media player and starts playing the specified media.
     *
     * @param key         the key of the media player to change
     * @param cycleCount  the cycle count for the media player
     */
    public void changeMedia(String key,int cycleCount){
        customPane.getMediaPlayer().stop();
        customPane.setMediaPlayer(Assets.getMediaPlayers().get(key));
        customPane.getMediaPlayer().setAutoPlay(false);
        customPane.getMediaPlayer().setCycleCount(cycleCount);
        customPane.getMediaPlayer().play();
    }

    /**
     * Plays the specified media.
     *
     * @param key  the key of the media player to play
     * @return the media player
     */
    public MediaPlayer playMedia(String key){
        MediaPlayer mediaPlayer = Assets.getMediaPlayers().get(key);
        mediaPlayer.setAutoPlay(false);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.play();
        return mediaPlayer;
    }

    /**
     * Returns the custom pane used in the stage.
     *
     * @return the custom pane
     */
    public CustomPane getCustomPane() {
        return customPane;
    }
}
