package com.teamtreehouse.pomodoro.model;

// Enums used with Constant values
// think of enum working like a class
public enum AttemptKind {
    // Values of the enum used with the constructor
    Focus(25*60, "Focus time"), // uses the constroctore here below
    BREAK(5*60,"Break time");// this is an attempt kind being declared in a constructor

    private String mDisplayName;
    public int getmTotalSeconds() {
        return mTotalSeconds;
    }
    private int mTotalSeconds;

    AttemptKind(int mTotalSeconds, String Display) {

        this.mTotalSeconds = mTotalSeconds;
        this.mDisplayName = Display;
    }


    public String getDisplayName() {
        return mDisplayName;
    }
}
