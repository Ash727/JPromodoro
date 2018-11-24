package com.teamtreehouse.pomodoro.model;

public class Attempt {
    private String mMessage;
    private  int mRemainingSeconds;

    private AttemptKind mkind;

    public Attempt(String message, AttemptKind kind) {
        this.mMessage = message;
        this.mkind = kind;
        this.mRemainingSeconds= kind.getmTotalSeconds();
    }

    public String getmMessage() {
        return mMessage;
    }

    public int getmRemainingSeconds() {
        return mRemainingSeconds;
    }

    public AttemptKind getMkind() {
        return mkind;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public void tick() {
     this.mRemainingSeconds--;
    }

    public void save() {
        System.out.printf("Saving:%s %n",this);
    }

    @Override
    public String toString() {
        return "Attempt{" +
                "mMessage='" + mMessage + '\'' +
                ", mRemainingSeconds=" + mRemainingSeconds +
                ", mkind=" + mkind +
                '}';
    }
}
