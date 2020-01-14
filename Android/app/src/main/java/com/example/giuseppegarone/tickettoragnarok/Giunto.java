package com.example.giuseppegarone.tickettoragnarok;

import android.graphics.Paint;
import android.graphics.Point;

public class Giunto {

    public int raggio;
    public Paint paint;
    public Point centro;

    public Giunto() {
        super();
    }

    public boolean setCentro(Point centro) {
        this.centro=centro;
        return true;
    }

    public boolean setPaintGiunto (Paint paint) {
        this.paint=paint;
        return true;
    }

    public Point getCentro() {
        return centro;
    }

    public Paint getPaintGiunto() {
        return paint;
    }

}
