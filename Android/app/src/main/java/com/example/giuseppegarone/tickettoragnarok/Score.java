package com.example.giuseppegarone.tickettoragnarok;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Score {

    private String nickname;
    private int score;

    public Score() {

    }

    public Score(String nickname, int score) {
        this.nickname = nickname;
        this.score  = score;
    }

    public String getNickname() {
        return nickname;
    }

    public int getScore() {
        return score;
    }

}
