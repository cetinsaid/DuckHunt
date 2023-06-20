import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;


public class CustomGamePane extends Pane {

    private ImageView backGroundImageView;

    private ImageView foreGroundImageView;
    private Image cursor;
    private Level levelSetter;
    private ArrayList<Text> textArrayList;
//    private ArrayList<Duck> duckArrayList;
    private int level;


    /**
     * Constructs a new CustomGamePane with default settings.
     * Initializes the level to 1 and sets up the list of texts.
     */
    public CustomGamePane(){
        level =1;
        textArrayList = new ArrayList<>();
        levelSetter = new Level();
        texts();
    }

    /**
     * Sets the background, foreground, and cursor for the game pane.
     *
     * @param key The key representing the background image key.
     * @param crossHairKey The key representing the cursor image key.
     */
    public void setBackGround(int key,int crossHairKey){
        backGroundImageView = Assets.getBackGrounds().get(key);
        foreGroundImageView = Assets.getForeGrounds().get(key);
        foreGroundImageView.setMouseTransparent(true);
        cursor = Assets.getCrossHairs().get(crossHairKey);
        Cursor cursor1 = new ImageCursor(cursor,cursor.getWidth()/2,cursor.getHeight()/2);
        setCursor(cursor1);
    }

    /**
     * Initializes and adds text elements to the game pane.
     */
    public void texts(){
        addText("Level1",0,10*Scaler.SCALE);
        addText("Level2",0,10*Scaler.SCALE);
        addText("Level3",0,10*Scaler.SCALE);
        addText("Level4",0,10*Scaler.SCALE);
        addText("Level5",0,10*Scaler.SCALE);
        addText("Level6",0,10*Scaler.SCALE);
        addText("YOU WIN!",0,12*Scaler.SCALE);
        addText("GAME OVER!",0,10*Scaler.SCALE);
        addText("You have completed the game!",0,12*Scaler.SCALE);
        addText("Press ENTER to play next level",0,10*Scaler.SCALE);
        addText("Press ENTER to play again",0,10*Scaler.SCALE);
        addText("Press ESC to Exit",0,10*Scaler.SCALE);
        fadeAnimationForText(textArrayList.get(9));
        fadeAnimationForText(textArrayList.get(10));
        fadeAnimationForText(textArrayList.get(11));
    }

    /**
     * Sets the game level based on the provided level number.
     * Each level corresponds to a specific configuration defined by the Level class.
     *
     * @param level The level number to set.
     */
    public void setLevel(int level){
        if(level ==1){
            levelSetter.level1();
        }else if(level==2){
            levelSetter.level2();
        }else if(level ==3){
            levelSetter.level3();
        }else if(level ==4){
            levelSetter.level4();
        }else if(level == 5){
            levelSetter.level5();
        }else if (level ==6){
            levelSetter.level6();
        }
    }

    /**
     * Sets the game level based on the result of the game.
     * The result can be "Win", "Lost", or "Game Completed", each corresponding to a specific configuration defined by the Level class.
     *
     * @param result The result of the game: "Win", "Lost", or "Game Completed".
     */
    public void setLevel(String result){
        if(result.equals("Win")){
            levelSetter.win();
        }else if(result.equals("Lost")){
            levelSetter.lost();
        }else if(result.equals("Game Completed")){
            levelSetter.gameCompleted();
        }
    }

    /**
     * Retrieves the current ammo count for the level.
     *
     * @return The current ammo count for the level.
     */
    public int getLevelAmmo(){
        return levelSetter.getAmmo();
    }

    /**
     * Decreases the ammo count for the level by one.
     * This method is called when the player uses a bullet in the game.
     */
    public void decreaseLevelAmmo(){
        levelSetter.decreaseAmmo();
    }

    /**
     * Adds a text element to the game pane.
     *
     * @param text The text content to be displayed.
     * @param size The font size of the text.
     */
    public void addText(String text ,double y,double size){
        Text textDeneme = new Text(text);
        textDeneme.setFont(Font.font("New Times Roman",size));
        textDeneme.setFill(Color.YELLOW);
        textArrayList.add(textDeneme);
    }

    /**
     * Returns the list of text elements in the game pane.
     *
     * @return The list of text elements.
     */
    public ArrayList<Text> getTextArrayList() {
        return textArrayList;
    }

    /**
     * Checks if the player has lost the game.
     *
     * @return {@code true} if the player has lost, {@code false} otherwise.
     */
    public boolean isLost(){
        levelSetter.checkCondition();
        return levelSetter.isLost;
    }

    /**
     * Checks if the player has won the game.
     *
     * @return {@code true} if the player has won, {@code false} otherwise.
     */
    public boolean isWin(){
        levelSetter.checkCondition();
        return levelSetter.isWin;
    }

    /**
     * Sets the win status of the game.
     *
     * @param win {@code true} if the player has won, {@code false} otherwise.
     */
    public void setWin(boolean win){
        levelSetter.setWin(win);
    }

    /**
     * Sets the lost status of the game.
     *
     * @param lost {@code true} if the player has lost, {@code false} otherwise.
     */
    public void setLost(boolean lost){
        levelSetter.setLost(lost);
    }

    /**
     * Returns the current level of the game.
     *
     * @return The current level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Increases the level of the game by one.
     */
    public void increaseLevel(){
        level +=1;
    }

    /**
     * Resets the level of the game to 1.
     */
    public void resetLevel(){
        level = 1;
    }

    /**
     * Applies a fade animation to the specified text element.
     * The text will fade in and out continuously.
     *
     * @param text The text element to apply the fade animation to.
     */
    public void fadeAnimationForText(Text text){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), text);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }

    class Level {
        private int ammo;
        private boolean isWin = false;
        private boolean isLost = false;
        private int shottedDuckCount;

        /**
         * Sets up level 1 of the game.
         * Initializes the game state, including ducks, texts, and background images.
         * Starts the animation for the ducks.
         */
        public void level1(){
            isLost = false;
            isWin = false;
            placeTexts();
            ArrayList<Duck> duckArrayList = new ArrayList<>();
            shottedDuckCount = 0;
            ammo = 6;
            resetAmmoCount();

            duckArrayList.add(new Duck("Blue","Horizontal"));
            duckArrayList.add(new Duck("Red", "Horizontal"));

            duckArrayList.forEach(Duck::flyingAnimation);

            duckArrayList.get(0).flyingProperities(3200.0,0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE));
            duckArrayList.get(1).flyingProperities(3200.0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0);
            duckArrayList.get(0).getDuckImageView().setY(backGroundImageView.getFitHeight()/2);
            duckArrayList.forEach(Duck::start);

            getChildren().clear();
            getChildren().addAll(backGroundImageView,duckArrayList.get(0).getDuckImageView(),duckArrayList.get(1).getDuckImageView(),
                    foreGroundImageView,textArrayList.get(0),textArrayList.get(12));
            setDuckCondition(duckArrayList);
        }

        /**
         * Sets up level 2 of the game.
         * Resets the game state, including ducks, texts, and background images.
         * Starts the animation for the ducks.
         */
        public void level2(){
            isLost = false;
            isWin = false;
            ArrayList<Duck> duckArrayList = new ArrayList<>();
            shottedDuckCount = 0;
            ammo = 9;
            resetAmmoCount();
            backGroundImageView.setMouseTransparent(false);

            duckArrayList.add(new Duck("Blue","Horizontal"));
            duckArrayList.add(new Duck("Red", "Horizontal"));
            duckArrayList.add(new Duck("Black","Cross"));
            duckArrayList.forEach(Duck::flyingAnimation);

            duckArrayList.get(0).flyingProperities(3200.0,0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE));
            duckArrayList.get(1).flyingProperities(3200.0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0);
            duckArrayList.get(2).flyingProperities(3200.0,0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0,backGroundImageView.getFitHeight()-(20*Scaler.SCALE));
            duckArrayList.get(1).getDuckImageView().setY(100*Scaler.SCALE);
            duckArrayList.forEach(Duck::start);

            getChildren().clear();
            getChildren().addAll(backGroundImageView,duckArrayList.get(0).getDuckImageView(),duckArrayList.get(1).getDuckImageView()
                    ,duckArrayList.get(2).getDuckImageView(),foreGroundImageView,textArrayList.get(1),textArrayList.get(12));
            setDuckCondition(duckArrayList);
        }

        /**
         * Sets up level 3 of the game.
         * Resets the game state, including ducks, texts, and background images.
         * Starts the animation for the ducks.
         */
        public void level3(){
            isLost = false;
            isWin = false;
            ArrayList<Duck> duckArrayList = new ArrayList<>();
            shottedDuckCount = 0;
            ammo = 12;
            backGroundImageView.setMouseTransparent(false);
            resetAmmoCount();
            duckArrayList.add(new Duck("Blue","Horizontal"));
            duckArrayList.add(new Duck("Red", "Horizontal"));
            duckArrayList.add(new Duck("Black","Cross"));
            duckArrayList.add(new Duck("Red","Cross"));
            duckArrayList.forEach(Duck::flyingAnimation);

            duckArrayList.get(0).flyingProperities(3200.0,0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE));
            duckArrayList.get(1).flyingProperities(3200.0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0);
            duckArrayList.get(2).flyingProperities(3200.0,0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0,backGroundImageView.getFitHeight()-(20*Scaler.SCALE));
            duckArrayList.get(3).flyingProperities(3200.0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0,0,backGroundImageView.getFitHeight()-(20*Scaler.SCALE));
            duckArrayList.get(0).getDuckImageView().setY(backGroundImageView.getFitHeight()/6);
            duckArrayList.get(1).getDuckImageView().setY(backGroundImageView.getFitHeight()/9);
            duckArrayList.forEach(Duck::start);

            getChildren().clear();
            getChildren().addAll(backGroundImageView,duckArrayList.get(0).getDuckImageView(),duckArrayList.get(1).getDuckImageView()
                    ,duckArrayList.get(2).getDuckImageView(),duckArrayList.get(3).getDuckImageView(),foreGroundImageView,textArrayList.get(2),textArrayList.get(12));
            setDuckCondition(duckArrayList);
        }

        /**
         * Sets up level 4 of the game.
         * Resets the game state, including ducks, texts, and background images.
         * Starts the animation for the ducks.
         */
        public void level4(){
            isLost = false;
            isWin = false;
            ArrayList<Duck> duckArrayList = new ArrayList<>();
            shottedDuckCount = 0;
            ammo = 15;
            backGroundImageView.setMouseTransparent(false);
            resetAmmoCount();
            duckArrayList.add(new Duck("Blue","Horizontal"));
            duckArrayList.add(new Duck("Red", "Horizontal"));
            duckArrayList.add(new Duck("Black","Cross"));
            duckArrayList.add(new Duck("Red","Cross"));
            duckArrayList.add(new Duck("Blue","Cross"));
            duckArrayList.forEach(Duck::flyingAnimation);


            duckArrayList.get(0).flyingProperities(3200.0,0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE));
            duckArrayList.get(1).flyingProperities(3200.0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0);
            duckArrayList.get(2).flyingProperities(3200.0,0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0,backGroundImageView.getFitHeight()-(20*Scaler.SCALE));
            duckArrayList.get(3).flyingProperities(3200.0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0,0,backGroundImageView.getFitHeight()-(20*Scaler.SCALE));
            duckArrayList.get(4).flyingProperities(3200.0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0,backGroundImageView.getFitHeight()-(20*Scaler.SCALE),0);
            duckArrayList.get(0).getDuckImageView().setY(backGroundImageView.getFitHeight()-150*Scaler.SCALE);
            duckArrayList.forEach(Duck::start);


            getChildren().clear();
            getChildren().addAll(duckArrayList.get(0).getDuckImageView(),duckArrayList.get(1).getDuckImageView()
                    ,duckArrayList.get(2).getDuckImageView(),duckArrayList.get(3).getDuckImageView(),duckArrayList.get(4).getDuckImageView(),
                    textArrayList.get(3),textArrayList.get(12));
            getChildren().add(getChildren().size()-1, foreGroundImageView);
            getChildren().add(0,backGroundImageView);
            setDuckCondition(duckArrayList);
        }

        /**
         * Sets up level 5 of the game.
         * Resets the game state, including ducks, texts, and background images.
         * Starts the animation for the ducks.
         */
        public void level5(){
            isLost = false;
            isWin = false;
            ArrayList<Duck> duckArrayList = new ArrayList<>();
            shottedDuckCount = 0;
            ammo = 18;
            resetAmmoCount();
            backGroundImageView.setMouseTransparent(false);
            duckArrayList.add(new Duck("Blue","Horizontal"));
            duckArrayList.add(new Duck("Red", "Horizontal"));
            duckArrayList.add(new Duck("Black","Cross"));
            duckArrayList.add(new Duck("Red","Cross"));
            duckArrayList.add(new Duck("Blue","Cross"));
            duckArrayList.add(new Duck("Red","Cross"));
            duckArrayList.forEach(Duck::flyingAnimation);

            duckArrayList.get(0).flyingProperities(3200.0,0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE));
            duckArrayList.get(1).flyingProperities(3200.0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0);
            duckArrayList.get(2).flyingProperities(3200.0,0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0,backGroundImageView.getFitHeight()-(20*Scaler.SCALE));
            duckArrayList.get(3).flyingProperities(3200.0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0,0,backGroundImageView.getFitHeight()-(20*Scaler.SCALE));
            duckArrayList.get(4).flyingProperities(3200.0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0,backGroundImageView.getFitHeight()-(20*Scaler.SCALE),0);
            duckArrayList.get(5).flyingProperities(3200.0,0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),backGroundImageView.getFitHeight()-(20*Scaler.SCALE),0);
            duckArrayList.get(0).getDuckImageView().setY(backGroundImageView.getFitHeight()/6);
            duckArrayList.forEach(Duck::start);

            getChildren().clear();
            getChildren().addAll(duckArrayList.get(0).getDuckImageView(),duckArrayList.get(1).getDuckImageView()
                    ,duckArrayList.get(2).getDuckImageView(),duckArrayList.get(3).getDuckImageView(),duckArrayList.get(4).getDuckImageView(),
                    duckArrayList.get(5).getDuckImageView(),textArrayList.get(4),textArrayList.get(12));
            getChildren().add(getChildren().size()-1, foreGroundImageView);
            getChildren().add(0,backGroundImageView);
            setDuckCondition(duckArrayList);
        }

        /**
         * Sets up level 6 of the game.
         * Resets the game state, including ducks, texts, and background images.
         * Starts the animation for the ducks.
         */
        public void level6(){
            isLost = false;
            isWin = false;
            ArrayList<Duck> duckArrayList = new ArrayList<>();
            shottedDuckCount = 0;
            ammo = 21;
            resetAmmoCount();

            duckArrayList.add(new Duck("Blue","Horizontal"));
            duckArrayList.add(new Duck("Red", "Horizontal"));
            duckArrayList.add(new Duck("Black","Cross"));
            duckArrayList.add(new Duck("Red","Cross"));
            duckArrayList.add(new Duck("Blue","Cross"));
            duckArrayList.add(new Duck("Red","Cross"));
            duckArrayList.add(new Duck("Black","Horizontal"));
            duckArrayList.forEach(Duck::flyingAnimation);


            duckArrayList.get(0).flyingProperities(3200.0,0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE));
            duckArrayList.get(1).flyingProperities(3200.0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0);
            duckArrayList.get(2).flyingProperities(3200.0,0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0,backGroundImageView.getFitHeight()-(20*Scaler.SCALE));
            duckArrayList.get(3).flyingProperities(3200.0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0,0,backGroundImageView.getFitHeight()-(20*Scaler.SCALE));
            duckArrayList.get(4).flyingProperities(3200.0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0,backGroundImageView.getFitHeight()-(20*Scaler.SCALE),0);
            duckArrayList.get(5).flyingProperities(3200.0,0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),backGroundImageView.getFitHeight()-(20*Scaler.SCALE),0);
            duckArrayList.get(6).flyingProperities(3200.0,backGroundImageView.getFitWidth()-(22*Scaler.SCALE),0);
            duckArrayList.get(0).getDuckImageView().setY(backGroundImageView.getFitHeight()/8);
            duckArrayList.get(1).getDuckImageView().setY(backGroundImageView.getFitHeight()/4);
            duckArrayList.get(6).getDuckImageView().setY(backGroundImageView.getFitHeight()/2);
            duckArrayList.forEach(Duck::start);


            getChildren().clear();
            getChildren().addAll(duckArrayList.get(0).getDuckImageView(),duckArrayList.get(1).getDuckImageView()
                    ,duckArrayList.get(2).getDuckImageView(),duckArrayList.get(3).getDuckImageView(),duckArrayList.get(4).getDuckImageView(),
                    duckArrayList.get(5).getDuckImageView(),textArrayList.get(5),textArrayList.get(12));
            getChildren().add(getChildren().size()-1, foreGroundImageView);
            getChildren().add(0,backGroundImageView);
            setDuckCondition(duckArrayList);

        }

        /**
         * Decreases the ammo count by 1.
         */
        public void decreaseAmmo(){
            ammo -= 1 ;
        }

        /**
         * Retrieves the current ammo count.
         *
         * @return The current ammo count.
         */
        public int getAmmo(){
            return ammo;
        }

        /**
         * Sets the condition for handling duck interactions.
         *
         * @param duckArrayList The list of ducks to handle.
         */
        public void setDuckCondition(ArrayList<Duck> duckArrayList){
            duckArrayList.forEach(ducks -> ducks.getDuckImageView().setOnMousePressed(event -> {
                        ducks.setShot(true);
                        ducks.getDuckImageView().setMouseTransparent(true);
                        shottedDuckCount +=1;
                        MediaPlayer mediaPlayer = Assets.getMediaPlayers().get("DuckFalls");
                        mediaPlayer.setAutoPlay(false);
                        mediaPlayer.setCycleCount(1);
                        mediaPlayer.play();
                for (Duck otherDuck : duckArrayList) {
                    if (otherDuck != ducks && isTopOnEachOther(ducks.getDuckImageView(),otherDuck.getDuckImageView())) {
                        otherDuck.setShot(true);
                        otherDuck.getDuckImageView().setMouseTransparent(true);
                        shottedDuckCount +=1;}
                }
            }));

        }

        /**
         * Checks if two image views are on top of each other.
         *
         * @param imageView1 The first image view.
         * @param imageView2 The second image view.
         * @return {@code true} if the image views are on top of each other, {@code false} otherwise.
         */
        private boolean isTopOnEachOther(ImageView imageView1, ImageView imageView2) {
            Bounds imageView1BoundsInParent = imageView1.getBoundsInParent();
            Bounds imageView2BoundsInParent = imageView2.getBoundsInParent();
            return imageView1BoundsInParent.intersects(imageView2BoundsInParent.getMinX(), imageView2BoundsInParent.getMinY(), imageView2BoundsInParent.getWidth(), imageView2BoundsInParent.getHeight());
        }

        /**
         * Checks the current game condition based on the ammo count and the number of shot ducks.
         * Updates the 'isWin' and 'isLost' flags accordingly.
         */
        public void checkCondition(){
            if(ammo >= 0 && shottedDuckCount == getChildren().size()-4){
                isWin =true;
            }else if(ammo == 0 && shottedDuckCount < getChildren().size()-4){
                isLost = true;
            }
        }

        /**
         * Places the texts on the game pane in their appropriate positions.
         * Sets the X and Y coordinates of each text element relative to the background image view.
         */
        public void placeTexts(){
            addText(("Ammo Left: " + ammo),0,10*Scaler.SCALE);

            // Set X and Y coordinates for each text element
            textArrayList.forEach(text -> {
                text.setX(backGroundImageView.getFitWidth()/2 - text.getLayoutBounds().getWidth()/2);
                text.setY(text.getLayoutBounds().getHeight());
                text.setMouseTransparent(true);
            });

            // Set X coordinate for the Ammo count
            textArrayList.get(12).setX(backGroundImageView.getFitWidth()-textArrayList.get(12).getLayoutBounds().getWidth());

            // Set Y coordinates for specific text elements
            for (int i = 6; i < 9; i++) {
                textArrayList.get(i).setY(backGroundImageView.getFitHeight()/2 - textArrayList.get(i).getLayoutBounds().getHeight()/2);
            }
            for (int i = 9; i < 11; i++) {
                textArrayList.get(i).setY(textArrayList.get(7).getY() + textArrayList.get(i).getLayoutBounds().getHeight());
            }
            textArrayList.get(11).setY(textArrayList.get(10).getY() + textArrayList.get(11).getLayoutBounds().getHeight());
        }

        /**
         * Resets the ammo count and updates the corresponding text element.
         * The text element displaying the ammo count is updated with the new ammo value,
         * and its X coordinate is adjusted to maintain proper positioning.
         */
        public void resetAmmoCount(){
            textArrayList.get(12).setText("Ammo Left: "+ ammo);
            textArrayList.get(12).setX(backGroundImageView.getFitWidth()-textArrayList.get(12).getLayoutBounds().getWidth());
        }

        /**
         * Displays the "win" state by removing all image views from the game pane
         * and adding the appropriate win-related text elements. The background image
         * view is placed at the bottom layer, followed by the foreground image view.
         * The win-related text elements are added on top of them.
         */
        public void win(){
            getChildren().removeIf(node -> node instanceof ImageView);
            getChildren().addAll(textArrayList.get(6),textArrayList.get(9));
            getChildren().add(0,backGroundImageView);
            getChildren().add(1,foreGroundImageView);
        }

        /**
         * Displays the "lost" state by removing all image views from the game pane
         * and adding the appropriate lost-related text elements. The background image
         * view is placed at the bottom layer, followed by the foreground image view.
         * The lost-related text elements are added on top of them.
         */
        public void lost(){
            getChildren().removeIf(node -> node instanceof ImageView);
            getChildren().addAll(textArrayList.get(7),textArrayList.get(10),textArrayList.get(11));
            getChildren().add(0,backGroundImageView);
            getChildren().add(1,foreGroundImageView);
        }

        /**
         * Displays the "game completed" state by removing all image views from the game pane
         * and adding the appropriate text elements indicating the completion of the game.
         * The background image view is placed at the bottom layer, followed by the foreground image view.
         * The completion-related text elements are added on top of them.
         */
        public void gameCompleted(){
            getChildren().removeIf(node -> node instanceof ImageView);
            getChildren().addAll(textArrayList.get(8),textArrayList.get(10),textArrayList.get(11));
            getChildren().add(0,backGroundImageView);
            getChildren().add(1,foreGroundImageView);
        }

        /**
         * Sets the win status of the game.
         *
         * @param win true if the game is in a win state, false otherwise
         */
        public void setWin(boolean win) {
            isWin = win;
        }

        /**
         * Sets the lost status of the game.
         *
         * @param lost true if the game is in a lost state, false otherwise
         */
        public void setLost(boolean lost) {
            isLost = lost;
        }
    }
}
