package com.example.giuseppegarone.tickettoragnarok;

import android.graphics.Paint;
import android.graphics.Point;

public class Linea {

    public int partenza1,partenza2,arrivo1,arrivo2;
    public Point partenza, arrivo;
    public Paint paint;

    public Linea() {
        super();
    }

    public boolean setPartenzaArrivo(Point partenza,  Point arrivo) {
        this.partenza=partenza;
        this.arrivo=arrivo;
        return true;
    }

    public boolean setPaint (Paint paint) {
        this.paint=paint;
        return true;
    }

    public Paint getPaint() {
        return paint;
    }

    public Point getArrivo() {
        return arrivo;
    }

    public Point getPartenza() {
        return partenza;
    }

}
