package com.teamtreehouse.pomodoro.controllers;

import com.sun.javafx.binding.StringFormatter;
import com.teamtreehouse.pomodoro.model.Attempt;
import com.teamtreehouse.pomodoro.model.AttemptKind;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import javax.management.timer.Timer;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Home {
    private Attempt mCurrentAttempt;
    @FXML private VBox container;
    @FXML private Label title;
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
        mCurrentAttempt = new Attempt("",kind);
        addAttemptStyle(kind);
        title.setText(kind.getDisplayName());
        setTimerText(mCurrentAttempt.getmRemainingSeconds());
        timeline = new Timeline();
        timeline.setCycleCount(kind.getmTotalSeconds());
        // Setting the cycle count how many animation frames we have
        //Now run timeline with how ever many seconds
        timeline.setCycleCount(kind.getmTotalSeconds());
        // Key frame return a list on our property
    }

    private void addAttemptStyle(AttemptKind kind) {
        container.getStyleClass().add(kind.toString().toLowerCase());
    }

    private void clearAttemptStyles() {
        for(AttemptKind kind: AttemptKind.values()){
        container.getStyleClass().remove(kind.toString().toLowerCase());
        }
    }

    public void DEBUG(ActionEvent actionEvent) {
        System.out.println("HI mum");


    }
}
