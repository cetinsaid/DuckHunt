import javafx.animation.*;
import javafx.scene.image.*;
import javafx.util.Duration;

import java.util.HashMap;

public class Duck {
    private final String color;
    private final String flyingPath;
    private final HashMap<Integer, ImageView> flyingAnimation;
    private final HashMap<Integer, ImageView> reversedFlyingAnimation;
    private int starting;
    private int deneme;
    private boolean isReversed;
    private final ImageView duckImageView;
    private double startingX;
    private double endingX;
    private double startingY;
    private double endingY;
    private TranslateTransition translateTransition;
    private Timeline timeline;
    private boolean isShot;
    private static int shottedDuckCount;
    private TranslateTransition fallingAnimation;

    /**
     * Creates a Duck object with the specified color and flying path.
     *
     * @param color      the color of the duck
     * @param flyingPath the flying path of the duck
     */
    public Duck(String color,String flyingPath){
        shottedDuckCount = 0;
        fallingAnimation = new TranslateTransition();
        translateTransition = new TranslateTransition();
        isShot = false;
        this.isReversed = false;
        duckImageView = new ImageView();
        duckImageView.setFitHeight(duckImageView.getFitHeight()*Scaler.SCALE);
        duckImageView.setFitWidth(duckImageView.getFitWidth()*Scaler.SCALE);
        starting = 1;
        deneme = 4;
        this.color = color;
        this.flyingPath = flyingPath;
        this.flyingAnimation = new HashMap<>();
        this.reversedFlyingAnimation = new HashMap<>();

    }

    /**
     * Sets up the flying animation frames based on the duck's color and flying path.
     */
    public void flyingAnimation(){
        if(color.equals("Blue") && flyingPath.equals("Horizontal")){
            for (int i = 4; i < 7 ; i++) {
                flyingAnimation.put(i-3,Assets.getBlueDuck().get(i));
                reversedFlyingAnimation.put(i-3,symmetryTaker(Assets.getBlueDuck().get(i).getImage()));
            }
            flyingAnimation.put(4,Assets.getBlueDuck().get(7));
            flyingAnimation.put(5,Assets.getBlueDuck().get(8));
        }
        else if(color.equals("Red") && flyingPath.equals("Horizontal")){
            for (int i = 4; i < 7 ; i++) {
                flyingAnimation.put(i-3,Assets.getRedDuck().get(i));
                reversedFlyingAnimation.put(i-3,symmetryTaker(Assets.getRedDuck().get(i).getImage()));
            }
            flyingAnimation.put(4,Assets.getRedDuck().get(7));
            flyingAnimation.put(5,Assets.getRedDuck().get(8));
        }
        else if(color.equals("Black") && flyingPath.equals("Horizontal")){
            for (int i = 4; i < 7 ; i++) {
                flyingAnimation.put(i-3,Assets.getBlackDuck().get(i));
                reversedFlyingAnimation.put(i-3,symmetryTaker(Assets.getBlackDuck().get(i).getImage()));

            }
            flyingAnimation.put(4,Assets.getBlackDuck().get(7));
            flyingAnimation.put(5,Assets.getBlackDuck().get(8));
        }
        else if(color.equals("Blue") && flyingPath.equals("Cross")){
            for (int i = 1; i < 4 ; i++) {
                flyingAnimation.put(i,Assets.getBlueDuck().get(i));
                reversedFlyingAnimation.put(i,symmetryTaker(Assets.getBlueDuck().get(i).getImage()));
            }
            flyingAnimation.put(4,Assets.getBlueDuck().get(7));
            flyingAnimation.put(5,Assets.getBlueDuck().get(8));
        }
        else if(color.equals("Red") && flyingPath.equals("Cross")){
            for (int i = 1; i < 4 ; i++) {
                flyingAnimation.put(i,Assets.getRedDuck().get(i));
                reversedFlyingAnimation.put(i,symmetryTaker(Assets.getRedDuck().get(i).getImage()));
            }
            flyingAnimation.put(4,Assets.getRedDuck().get(7));
            flyingAnimation.put(5,Assets.getRedDuck().get(8));
        }
        else if(color.equals("Black") && flyingPath.equals("Cross")){
            for (int i = 1; i < 4 ; i++) {
                flyingAnimation.put(i,Assets.getBlackDuck().get(i));
                reversedFlyingAnimation.put(i,symmetryTaker(Assets.getBlackDuck().get(i).getImage()));
            }
            flyingAnimation.put(4,Assets.getBlackDuck().get(7));
            flyingAnimation.put(5,Assets.getBlackDuck().get(8));
        }
    }




    /**
     * Sets up the flying properties for the duck animation with the specified duration and coordinates.
     *
     * @param duration   the duration of the flying animation in milliseconds
     * @param startingX  the starting x-coordinate of the duck
     * @param endingX    the ending x-coordinate of the duck
     */
    public void flyingProperities(Double duration, double startingX, double endingX){
        this.startingX = startingX;
        this.endingX = endingX;
        Duration DURATION = Duration.millis(duration);
        translateTransition = new TranslateTransition(DURATION,duckImageView);
        translateTransition.setFromX(startingX);
        translateTransition.setToX(endingX);
        animations();

    }

    /**
     * Gets the duck's image view.
     *
     * @return the duck's image view
     */
    public ImageView getDuckImageView() {
        return duckImageView;
    }


    /**
     * Generates the symmetric image of the given image.
     *
     * @param image the image to generate the symmetric image from
     * @return the symmetric image as an ImageView
     */
    public static ImageView symmetryTaker(Image image){
        WritableImage symmetricalImage = new WritableImage((int)image.getWidth(),(int)image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = symmetricalImage.getPixelWriter();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int mirroredX = (int)image.getWidth() - x - 1;
                int color = pixelReader.getArgb(mirroredX, y);
                pixelWriter.setArgb(x, y, color);
            }
        }
        return new ImageView(symmetricalImage);
    }

    /**
     * Sets up the flying properties for the duck animation with the specified duration and coordinates.
     * This method also includes the y-axis coordinates for the flying animation.
     *
     * @param duration   the duration of the flying animation in milliseconds
     * @param startingX  the starting x-coordinate of the duck
     * @param endingX    the ending x-coordinate of the duck
     * @param startingY  the starting y-coordinate of the duck
     * @param endingY    the ending y-coordinate of the duck
     */
    public void flyingProperities(Double duration, double startingX, double endingX, double startingY, double endingY){
        this.startingX = startingX;
        this.endingX = endingX;
        this.startingY = startingY;
        this.endingY = endingY;
        Duration DURATION = Duration.millis(duration);
        translateTransition.setDuration(DURATION);
        translateTransition.setNode(duckImageView);
        translateTransition.setFromX(startingX);
        translateTransition.setToX(endingX);
        translateTransition.setFromY(startingY);
        translateTransition.setToY(endingY);
        animations();
        duckImageView.setOnMousePressed(event -> System.out.println("olalaala"));
    }


    /**
     * Sets up the animation and reversed animation for the duck object.
     */
    private void animations() {
        translateTransition.setCycleCount(-1);
        translateTransition.setAutoReverse(true);
        translateTransition.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.lessThan(oldValue)) {
                isReversed = true;}
            else {
                isReversed = false;}
        });
        timeline = new Timeline(new KeyFrame(Duration.ZERO, event ->{
            if(starting==4){
                starting = 1;}
            else{
                if(!isReversed){
                    if(flyingPath.equals("Cross")){
                        if((startingX<endingX) && (startingY<endingY)){
                            duckImageView.setImage(flyingAnimation.get(starting++).getImage());
                            duckImageView.setRotate(90);
                        }else if((startingX>endingX) && (startingY<endingY)){
                            duckImageView.setImage(reversedFlyingAnimation.get(starting++).getImage());
                            duckImageView.setRotate(-90);
                        }
                        else if((startingX<endingX) && (startingY>endingY)){
                            duckImageView.setImage(flyingAnimation.get(starting++).getImage());
                            duckImageView.setRotate(0);
                        }
                        else if((startingX>endingX) && (startingY>endingY)){
                            duckImageView.setImage(reversedFlyingAnimation.get(starting++).getImage());
                            duckImageView.setRotate(0);
                        }
                    }
                    else{
                        if(startingX<endingX){
                            duckImageView.setImage(flyingAnimation.get(starting++).getImage());
                        }else{
                            duckImageView.setImage(reversedFlyingAnimation.get(starting++).getImage());
                        }
                    }
                }
                else{
                    if(flyingPath.equals("Cross") ){
                        if((startingX<endingX) && (startingY<endingY)){
                            duckImageView.setImage(reversedFlyingAnimation.get(starting++).getImage());
                            duckImageView.setRotate(-15);}
                        else if((startingX>endingX) && (startingY<endingY)){
                            duckImageView.setImage(flyingAnimation.get(starting++).getImage());
                            duckImageView.setRotate(15);
                        }
                        else if((startingX<endingX) && (startingY>endingY)){
                            duckImageView.setImage(reversedFlyingAnimation.get(starting++).getImage());
                            duckImageView.setRotate(-75);
                        }
                        else if((startingX>endingX) && (startingY>endingY)){
                            duckImageView.setImage(flyingAnimation.get(starting++).getImage());
                            duckImageView.setRotate(75);
                        }
                    }
                    else{
                        if(startingX<endingX){
                            duckImageView.setImage(reversedFlyingAnimation.get(starting++).getImage());
                        }else{
                            duckImageView.setImage(flyingAnimation.get(starting++).getImage());
                        }
                    }
                }
                if(isShot){
                    if(deneme==5){
                        translateTransition.stop();
                        timeline.stop();
                        fallingAnimation.setNode(duckImageView);
                        fallingAnimation.setDuration(Duration.millis(2400.0));
                        fallingAnimation.setToY(Assets.getBackGrounds().get(1).getFitHeight());
                        fallingAnimation.setCycleCount(1);
                        fallingAnimation.play();
                    }
                    duckImageView.setImage(flyingAnimation.get(deneme++).getImage());
                    duckImageView.setRotate(0);
                }
                duckImageView.setFitHeight(duckImageView.getImage().getHeight()*Scaler.SCALE);
                duckImageView.setFitWidth(duckImageView.getImage().getHeight()*Scaler.SCALE);
            }
        }),new KeyFrame(Duration.millis(150)));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * Starts the flying animation for the duck.
     */
    public void start(){
        translateTransition.play();
        timeline.play();

    }

    /**
     * Sets the shot status of the duck.
     *
     * @param shot the shot status to set for the duck
     */
    public void setShot(boolean shot) {
        shottedDuckCount +=1;
        isShot = shot;
    }

}
