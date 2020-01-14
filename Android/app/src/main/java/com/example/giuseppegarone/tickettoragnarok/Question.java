package com.example.giuseppegarone.tickettoragnarok;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Question {

    private String Dtext;
    private String R1, R2, R3, R4;
    private String Rgiusta;

    public Question() {

    }

    public Question(String Dtext, String R1, String R2, String R3, String R4, String Rgiusta) {
        this.Dtext = Dtext;
        this.R1 = R1;
        this.R2 = R2;
        this.R3 = R3;
        this.R4 = R4;
        this.Rgiusta = Rgiusta;
    }

    public String getDtext() {
        return Dtext;
    }

    public String getR1() {
        return R1;
    }

    public String getR2() {
        return R2;
    }

    public String getR3() {
        return R3;
    }

    public String getR4() {
        return R4;
    }

    public String getRgiusta() {
        return Rgiusta;
    }

}
