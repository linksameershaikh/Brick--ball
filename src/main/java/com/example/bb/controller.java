package com.example.bb;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class controller implements Initializable {
    @FXML
    private Circle circle;

    @FXML
    private AnchorPane scene;
    ArrayList<Rectangle> all_bricks = new ArrayList<>();
    double deltaX=0.5;
    double deltaY = 0.5;
    private Rectangle slider;
    private Button right;
    private Button left;


    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(8), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
        //TODO move the ball , and add colision feature
        circle.setLayoutX(circle.getLayoutX() + deltaX);
            circle.setLayoutY(circle.getLayoutY() + deltaY);
            //check for boundary collision
            check_scene_collision();
            // brick collision
            check_brick_collision();
            check_slider_collision();

        }
    }));
    public void check_slider_collision(){
        if(circle.getBoundsInParent().intersects(slider.getBoundsInParent())){
            deltaY *= -1;
        }

    }
    public void check_brick_collision(){
        if(!all_bricks.isEmpty()){
            all_bricks.removeIf(component -> check_collision(component));
        }
        else System.exit(1);
    }
    public boolean check_collision(Rectangle current_brick){
        if(circle.getBoundsInParent().intersects(current_brick.getBoundsInParent())){

            boolean rightside = circle.getLayoutX() >= ((current_brick.getLayoutX() + current_brick.getWidth()) + circle.getRadius());
            boolean leftside = circle.getLayoutX() <= ((current_brick.getLayoutX()) - circle.getRadius());
            boolean upside = circle.getLayoutY() <= ((current_brick.getLayoutY()) - circle.getRadius());
            boolean bottomside = circle.getLayoutY() >= ((current_brick.getLayoutY() + current_brick.getHeight()) - circle.getRadius());

            System.out.println(" right " + rightside + " left " + leftside + " up " + upside + " bottom " + bottomside);
            if(rightside || leftside){
                deltaX *= -1;
            }

            if(upside || bottomside){
                deltaY *= -1;
            }

            scene.getChildren().remove(current_brick);
            return true;
        }
        else return false;
    }
    public void check_scene_collision(){
        Bounds bound = scene.getBoundsInLocal() ;
        boolean rightside =  circle.getLayoutX() >= (bound.getMaxX() -circle.getRadius());
        boolean leftside =  circle.getLayoutX() <= (bound.getMinX() +circle.getRadius());
        boolean upside =  circle.getLayoutY() <= (bound.getMinY() +circle.getRadius());
        boolean downside =  circle.getLayoutY() >= (bound.getMaxY() -circle.getRadius());

        if(rightside || leftside){
            deltaX *= -1;
        }
        if(upside || downside){
            deltaY *= -1;
        }
        if(downside){
//            deltaY *= -1;
        System.exit(1);
        all_bricks.clear();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline.setCycleCount(Animation.INDEFINITE);
        creatbrick();
        add_button();
        addslider();
        timeline.play();
    }
    public void creatbrick(){
        int flag =1;
        for (int i = 290; i >0; i-=50) {
            for (int j = 440; j >0 ; j-=30) {
                if(flag%2==0){
                Rectangle rectangle = new Rectangle(j, i, 40,25);
                    if(flag%3==0){
                        rectangle.setFill(Color.ORANGE);

                    } else if (flag%3==1) {
                        rectangle.setFill(Color.GREEN);
                    }
                     else {
                        rectangle.setFill(Color.AQUA);
                    }
                    scene.getChildren().add(rectangle);
                    all_bricks.add(rectangle);
                }
                flag++;
            }
        }
    }
    public void addslider(){

        slider = new Rectangle(300,475,100,15);
        slider.setFill(Color.WHITE);
        scene.getChildren().add(slider);
    }

    public void add_button(){
        right = new Button("right");
        right.setLayoutX(440);
        right.setLayoutY(450);

        left = new Button("left");
        left.setLayoutX(20);
        left.setLayoutY(450);

        right.setOnAction(moveright);
        left.setOnAction(moveleft);

        scene.getChildren().add(right);
        scene.getChildren().add(left);
    }
    EventHandler<ActionEvent> moveright = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            slider.setLayoutX(slider.getLayoutX()+30);
        }
    };



    EventHandler<ActionEvent> moveleft = new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent actionEvent) {
            slider.setLayoutX(slider.getLayoutX()-30);
        }
    };
}
