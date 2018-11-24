package com.teamtreehouse.pomodoro.controllers;

import com.sun.corba.se.impl.corba.RequestImpl;
import com.sun.javafx.binding.StringFormatter;
import com.teamtreehouse.pomodoro.model.Attempt;
import com.teamtreehouse.pomodoro.model.AttemptKind;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.management.timer.Timer;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Home {
    private Attempt mCurrentAttempt;
    @FXML private VBox container;
    @FXML private Label title;

    public TextArea getMessage() {
        return message;
    }

    @FXML private TextArea message;
    long time = Timer.ONE_MINUTE;
    private Timeline timeline;

    public StringProperty mTimerText; // Note this is a string property used for binding

    public String getTimerText() {
        return mTimerText.get();
    }

    public StringProperty timerTextProperty() {
        return mTimerText;
    }

    public void setTimerText(String timerText) {
        mTimerText.set(timerText);
    }

    public void setTimerText(int remainingSeconds){
        int minutes = remainingSeconds/60;
        int seconds = remainingSeconds%60;
        // To insture we have learding 0
        setTimerText(String.format("%2d:%02d",minutes,seconds));

    }


    public Home(){
         // Note this is a string property can be used for binding
        mTimerText = new SimpleStringProperty();
       setTimerText(0);
    }
    private void prepareAttempt(AttemptKind kind){
        clearAttemptStyles();
        reset();

        mCurrentAttempt = new Attempt("",kind);
        addAttemptStyle(kind);
        title.setText(kind.getDisplayName());
        setTimerText(mCurrentAttempt.getmRemainingSeconds());
        //TODO: csd This is creating multiple timlines must be fixed
        timeline = new Timeline();
        timeline.setCycleCount(kind.getmTotalSeconds());
        // Setting the cycle count how many animation frames we have
        //Now run timeline with how ever many seconds
        timeline.setCycleCount(kind.getmTotalSeconds());
        // Key frame return a list on our property
        // Duration is playing each key 1 second each
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e->{
        // Code to be ran ever second of the animation
              mCurrentAttempt.tick();
              setTimerText(mCurrentAttempt.getmRemainingSeconds());
        }));
        // Want this to happen with the timeline is doene it goes through all
        // the set cycle

        timeline.setOnFinished(e->{

            prepareAttempt(mCurrentAttempt.getMkind() == AttemptKind.Focus?AttemptKind.Focus.BREAK:AttemptKind.Focus);
        });

    }

    private  void saveCurrentAttempt () {
        mCurrentAttempt.save();
        mCurrentAttempt.setmMessage(message.getText());
    }

    private void reset() {
        if(timeline!=null && timeline.getStatus() == Animation.Status.RUNNING){
            timeline.stop();
        }
    }


    public void playTimer () {
        timeline.play();
    }

    public void pauseTimer () {
        timeline.pause();
    }
    private void addAttemptStyle(AttemptKind kind) {
        container.getStyleClass().add(kind.toString().toLowerCase());
    }

    private void clearAttemptStyles() {
        for(AttemptKind kind: AttemptKind.values()){
        container.getStyleClass().remove(kind.toString().toLowerCase());
//        getClass().getResource("java.avi").toExternalForm()
        }
    }

    public void DEBUG(ActionEvent actionEvent) {
        System.out.println("HI mum");


    }

    public void handleRestart(ActionEvent actionEvent) {
        prepareAttempt(AttemptKind.Focus);
        timeline.play();
    }
}
