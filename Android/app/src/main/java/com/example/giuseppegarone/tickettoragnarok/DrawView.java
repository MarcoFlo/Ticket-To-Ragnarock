package com.example.giuseppegarone.tickettoragnarok;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawView extends View {

    public Paint paint = new Paint();
    public Paint paintPart = new Paint();
    public Paint paintArr = new Paint();
    public Paint paintProva = new Paint();
    public Point posAttuale = new Point();
    public Point posArrivoFin = new Point();
    public int i = 100;
    public int h = 100;
    public boolean controllo = false;
    public boolean finito = false;

    Context context;

    public DrawView(Context context) {
        super(context);
        this.context=context;

        int coloreGiuntoPartenza = context.getResources().getColor(R.color.giuntoPartenza);
        int coloreGiuntoArrivo = context.getResources().getColor(R.color.giuntoArrivo);

        paintPart.setColor(coloreGiuntoPartenza);
        paintArr.setColor(coloreGiuntoArrivo);
        paint.setColor(Color.BLACK);
        paintProva.setColor(Color.BLUE);

        paint.setAntiAlias(true);
        paintPart.setAntiAlias(true);
        paintArr.setAntiAlias(true);
        paintProva.setAntiAlias(true);

        paint.setStrokeWidth(4);
    }

    public void startRandom() {
        if(i == 100) {
            Random r = new Random();
            i = r.nextInt(15 - 0) + 0;
        }
    }

    public void finishRandom() {
        if(h == 100) {
            Random s = new Random();
            h = s.nextInt(15 - 0) + 0;
            while(h == i) {
                h = s.nextInt(15 - 0) + 0;
            }
        }
    }

    /**
     * Disegno ragnatela completa.
      * @param canvas
     */
    @Override
    public void onDraw(Canvas canvas) {

        final GamePlayingActivity gp = new GamePlayingActivity();

        List<Point> possibiliDirezioni = new ArrayList<Point>();
        List<Giunto> giunti = new ArrayList<Giunto>();

        Point p = new Point(100, 300);
        Point p1 = new Point(300, 300);
        Point p2 = new Point(50, 150);
        Point p3 = new Point(200, 50);
        Point p4 = new Point(350, 150);

        // Anello esterno
        canvas.drawLine(100,300, 300,300, paint);
        canvas.drawLine(100,300, 50,150,  paint);
        canvas.drawLine(200,50,  50,150,  paint);
        canvas.drawLine(200,50,  350,150, paint);
        canvas.drawLine(300,300, 350,150, paint);

        Linea linea1 = new Linea();
        boolean bho1=linea1.setPartenzaArrivo(p, p1);
        Linea linea2 = new Linea();
        boolean bho2=linea2.setPartenzaArrivo(p, p2);
        Linea linea3 = new Linea();
        boolean bho3=linea3.setPartenzaArrivo(p3, p2);
        Linea linea4 = new Linea();
        boolean bho4=linea4.setPartenzaArrivo(p3, p4);
        Linea linea5 = new Linea();
        boolean bho5=linea5.setPartenzaArrivo(p1, p4);

        List<Linea> linee = new ArrayList<Linea>();
        linee.add(linea1);
        linee.add(linea2);
        linee.add(linea3);
        linee.add(linea4);
        linee.add(linea5);

        // Anello centrale
        Point p5 = new Point(125, 275);
        Point p6 = new Point(275, 275);
        Point p7 = new Point(90, 170);
        Point p8 = new Point(200, 90);
        Point p9 = new Point(310, 170);

        canvas.drawLine(125,275, 275,275, paint);
        canvas.drawLine(90,170,  125,275, paint);
        canvas.drawLine(200,90,  90,170,  paint);
        canvas.drawLine(200,90,  310,170, paint);
        canvas.drawLine(310,170, 275,275, paint);

        Linea linea6 = new Linea();
        boolean bho6=linea6.setPartenzaArrivo(p5, p6);
        Linea linea7 = new Linea();
        boolean bho7=linea7.setPartenzaArrivo(p7, p5);
        Linea linea8 = new Linea();
        boolean bho8=linea8.setPartenzaArrivo(p8, p7);
        Linea linea9 = new Linea();
        boolean bho9=linea9.setPartenzaArrivo(p8, p9);
        Linea linea10 = new Linea();
        boolean bho10=linea10.setPartenzaArrivo(p9, p6);

        linee.add(linea6);
        linee.add(linea7);
        linee.add(linea8);
        linee.add(linea9);
        linee.add(linea10);

        // Anello interno
        Point p10=new Point(150,250);
        Point p11=new Point(250,250);
        Point p12=new Point(120,180);
        Point p13=new Point(200,120);
        Point p14=new Point(280,180);

        canvas.drawLine(150,250, 250,250, paint);
        canvas.drawLine(150,250, 120,180, paint);
        canvas.drawLine(200,120, 120,180, paint);
        canvas.drawLine(280,180, 200,120, paint);
        canvas.drawLine(250,250, 280,180, paint);

        Linea linea11 = new Linea();
        boolean bho11=linea11.setPartenzaArrivo(p10, p11);
        Linea linea12 = new Linea();
        boolean bho12=linea12.setPartenzaArrivo(p10, p12);
        Linea linea13 = new Linea();
        boolean bho13=linea13.setPartenzaArrivo(p13, p12);
        Linea linea14 = new Linea();
        boolean bho14=linea14.setPartenzaArrivo(p14, p13);
        Linea linea15 = new Linea();
        boolean bho15=linea15.setPartenzaArrivo(p11, p14);

        linee.add(linea11);
        linee.add(linea12);
        linee.add(linea13);
        linee.add(linea14);
        linee.add(linea15);

        // Tiranti esterni
        canvas.drawLine(100,300, 125,275, paint);
        canvas.drawLine(50,150,  90,170,  paint);
        canvas.drawLine(200,50,  200,90,  paint);
        canvas.drawLine(350,150, 310,170, paint);
        canvas.drawLine(300,300, 275,275, paint);

        Linea linea16 = new Linea();
        boolean bho16=linea16.setPartenzaArrivo(p, p5);
        Linea linea17 = new Linea();
        boolean bho17=linea17.setPartenzaArrivo(p2,  p7);
        Linea linea18 = new Linea();
        boolean bho18=linea18.setPartenzaArrivo(p3,  p8);
        Linea linea19 = new Linea();
        boolean bho19=linea19.setPartenzaArrivo(p4, p9);
        Linea linea20 = new Linea();
        boolean bho20=linea20.setPartenzaArrivo(p1, p6);

        linee.add(linea16);
        linee.add(linea17);
        linee.add(linea18);
        linee.add(linea19);
        linee.add(linea20);

        // Tiranti interni
        canvas.drawLine(150,250, 125,275, paint);
        canvas.drawLine(120,180, 90,170,  paint);
        canvas.drawLine(200,120, 200,90,  paint);
        canvas.drawLine(280,180, 310,170, paint);
        canvas.drawLine(250,250, 275,275, paint);

        Linea linea21 = new Linea();
        boolean bho21=linea21.setPartenzaArrivo(p10, p5);
        Linea linea22 = new Linea();
        boolean bho22=linea22.setPartenzaArrivo(p12, p7);
        Linea linea23 = new Linea();
        boolean bho23=linea23.setPartenzaArrivo(p13, p8);
        Linea linea24 = new Linea();
        boolean bho24=linea24.setPartenzaArrivo(p14, p9);
        Linea linea25 = new Linea();
        boolean bho25=linea25.setPartenzaArrivo(p11, p6);

        linee.add(linea21);
        linee.add(linea22);
        linee.add(linea23);
        linee.add(linea24);
        linee.add(linea25);

        // Giunti anello esterno
        Giunto giunto1 = new Giunto();
        boolean mah1 = giunto1.setCentro(p);
        Giunto giunto2 = new Giunto();
        boolean mah2 = giunto2.setCentro(p2);
        Giunto giunto3 = new Giunto();
        boolean mah3 = giunto3.setCentro(p3);
        Giunto giunto4 = new Giunto();
        boolean mah4 = giunto4.setCentro(p4);
        Giunto giunto5 = new Giunto();
        boolean mah5 = giunto5.setCentro(p1);

        canvas.drawCircle(100,300, 10, paint);
        canvas.drawCircle(50,150,  10, paint);
        canvas.drawCircle(200,50,  10, paint);
        canvas.drawCircle(350,150, 10, paint);
        canvas.drawCircle(300,300, 10, paint);

        // Giunti anello centrale
        Giunto giunto6 = new Giunto();
        boolean mah6 = giunto6.setCentro(p5);
        Giunto giunto7 = new Giunto();
        boolean mah7 = giunto7.setCentro(p7);
        Giunto giunto8 = new Giunto();
        boolean mah8 = giunto8.setCentro(p8);
        Giunto giunto9 = new Giunto();
        boolean mah9 = giunto9.setCentro(p9);
        Giunto giunto10 = new Giunto();
        boolean mah10 = giunto10.setCentro(p6);
        canvas.drawCircle(125,275, 10, paint);
        canvas.drawCircle(90,170,  10, paint);
        canvas.drawCircle(200,90,  10, paint);
        canvas.drawCircle(310,170, 10, paint);
        canvas.drawCircle(275,275, 10, paint);

        // Giunti anello interno
        Giunto giunto11 = new Giunto();
        boolean mah11 = giunto11.setCentro(p10);
        Giunto giunto12 = new Giunto();
        boolean mah12 = giunto12.setCentro(p12);
        Giunto giunto13 = new Giunto();
        boolean mah13 = giunto13.setCentro(p13);
        Giunto giunto14 = new Giunto();
        boolean mah14 = giunto14.setCentro(p14);
        Giunto giunto15 = new Giunto();
        boolean mah15 = giunto15.setCentro(p11);

        //giunti in lista
        giunti.add(giunto1);
        giunti.add(giunto2);
        giunti.add(giunto3);
        giunti.add(giunto4);
        giunti.add(giunto5);
        giunti.add(giunto6);
        giunti.add(giunto7);
        giunti.add(giunto8);
        giunti.add(giunto9);
        giunti.add(giunto10);
        giunti.add(giunto11);
        giunti.add(giunto12);
        giunti.add(giunto13);
        giunti.add(giunto14);
        giunti.add(giunto15);

        canvas.drawCircle(150,250, 10, paint);
        canvas.drawCircle(120,180, 10, paint);
        canvas.drawCircle(200,120, 10, paint);
        canvas.drawCircle(280,180, 10, paint);
        canvas.drawCircle(250,250, 10, paint);

        startRandom();
        canvas.drawCircle(giunti.get(i).getCentro().x, giunti.get(i).getCentro().y, 10, paintPart);
        posAttuale = giunti.get(i).getCentro();
        finishRandom();
        canvas.drawCircle(giunti.get(h).getCentro().x, giunti.get(h).getCentro().y, 10, paintArr);
        posArrivoFin = giunti.get(h).getCentro();

        if(posArrivoFin==posAttuale) {
            Intent i = new Intent(context, Popup.class);
            context.startActivity(i);
        }

        for(int y = 0; y < gp.stradePassate.size(); y++) {
            Point part = gp.stradePassate.get(y);

            if(gp.stradePassate.get(y+1) == null) {
                break;
            }

            Point arr = gp.stradePassate.get(y+1);
            canvas.drawLine(part.x, part.y, arr.x, arr.y, paintPart);
        }
    }

}
