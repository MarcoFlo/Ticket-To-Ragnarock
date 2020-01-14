package com.example.giuseppegarone.tickettoragnarok;

public class GiuntoRagnatelaFisica {

    public int partenza, arrivo;
    public boolean acceso = false;

    public boolean setPartenzaArrivo(int partenza,  int arrivo) {
        this.partenza = partenza;
        this.arrivo = arrivo;
        return true;
    }

}

