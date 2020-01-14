package com.example.giuseppegarone.tickettoragnarok;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AccensioneRagnatela {

    private int daPartenza;
    private int aPartenza;
    private ArrayList<SegmentoRagnatelaFisica> segmenti = new ArrayList<>();
    public List<GiuntoRagnatelaFisica> possibiliGiunti = new ArrayList<GiuntoRagnatelaFisica>();
    List<SegmentoRagnatelaFisica> possibiliSegmenti = new ArrayList<SegmentoRagnatelaFisica>();
    private Handler mNetworkHandler;

    GiuntoRagnatelaFisica giunto1 = new GiuntoRagnatelaFisica();
    boolean mah1 = giunto1.setPartenzaArrivo(358,361);
    GiuntoRagnatelaFisica giunto2 = new GiuntoRagnatelaFisica();
    boolean mah2 = giunto2.setPartenzaArrivo(461,464);
    GiuntoRagnatelaFisica giunto3 = new GiuntoRagnatelaFisica();
    boolean mah3 = giunto3.setPartenzaArrivo(22,29);
    GiuntoRagnatelaFisica giunto4 = new GiuntoRagnatelaFisica();
    boolean mah4 = giunto4.setPartenzaArrivo(107,110);
    GiuntoRagnatelaFisica giunto5 = new GiuntoRagnatelaFisica();
    boolean mah5 = giunto5.setPartenzaArrivo(232,235);

    GiuntoRagnatelaFisica giunto6 = new GiuntoRagnatelaFisica();
    boolean mah6 = giunto6.setPartenzaArrivo(344,347);
    GiuntoRagnatelaFisica giunto7 = new GiuntoRagnatelaFisica();
    boolean mah7 = giunto7.setPartenzaArrivo(445,448);
    GiuntoRagnatelaFisica giunto8 = new GiuntoRagnatelaFisica();
    boolean mah8 = giunto8.setPartenzaArrivo(14,16);
    GiuntoRagnatelaFisica giunto9 = new GiuntoRagnatelaFisica();
    boolean mah9 = giunto9.setPartenzaArrivo(85,88);
    GiuntoRagnatelaFisica giunto10 = new GiuntoRagnatelaFisica();
    boolean mah10 = giunto10.setPartenzaArrivo(217,220);

    GiuntoRagnatelaFisica giunto11 = new GiuntoRagnatelaFisica();
    boolean mah11 = giunto11.setPartenzaArrivo(329,332);
    GiuntoRagnatelaFisica giunto12 = new GiuntoRagnatelaFisica();
    boolean mah12 = giunto12.setPartenzaArrivo(433,435);
    GiuntoRagnatelaFisica giunto13 = new GiuntoRagnatelaFisica();
    boolean mah13 = giunto13.setPartenzaArrivo(4,6);
    GiuntoRagnatelaFisica giunto14 = new GiuntoRagnatelaFisica();
    boolean mah14 = giunto14.setPartenzaArrivo(64,67);
    GiuntoRagnatelaFisica giunto15 = new GiuntoRagnatelaFisica();
    boolean mah15 = giunto15.setPartenzaArrivo(200,203);

    SegmentoRagnatelaFisica segmento1 = new SegmentoRagnatelaFisica();
    boolean bho1 = segmento1.setPartenzaArrivo(906, 973);
    SegmentoRagnatelaFisica segmento2 = new SegmentoRagnatelaFisica();
    boolean bho2 = segmento2.setPartenzaArrivo(976, 1031);
    SegmentoRagnatelaFisica segmento3 = new SegmentoRagnatelaFisica();
    boolean bho3 = segmento3.setPartenzaArrivo(1034, 1070);
    SegmentoRagnatelaFisica segmento4 = new SegmentoRagnatelaFisica();
    boolean bho4 = segmento4.setPartenzaArrivo(791, 842);
    SegmentoRagnatelaFisica segmento5 = new SegmentoRagnatelaFisica();
    boolean bho5 = segmento5.setPartenzaArrivo(844, 902);

    SegmentoRagnatelaFisica segmento6 = new SegmentoRagnatelaFisica();
    boolean bho6 = segmento6.setPartenzaArrivo(686, 727);
    SegmentoRagnatelaFisica segmento7 = new SegmentoRagnatelaFisica();
    boolean bho7 = segmento7.setPartenzaArrivo(729, 764);
    SegmentoRagnatelaFisica segmento8 = new SegmentoRagnatelaFisica();
    boolean bho8 = segmento8.setPartenzaArrivo(765, 789);
    SegmentoRagnatelaFisica segmento9 = new SegmentoRagnatelaFisica();
    boolean bho9 = segmento9.setPartenzaArrivo(613, 649);
    SegmentoRagnatelaFisica segmento10 = new SegmentoRagnatelaFisica();
    boolean bho10 = segmento10.setPartenzaArrivo(653, 685);

    SegmentoRagnatelaFisica segmento11 = new SegmentoRagnatelaFisica();
    boolean bho11 = segmento11.setPartenzaArrivo(560, 576);
    SegmentoRagnatelaFisica segmento12 = new SegmentoRagnatelaFisica();
    boolean bho12 = segmento12.setPartenzaArrivo(578, 597);
    SegmentoRagnatelaFisica segmento13 = new SegmentoRagnatelaFisica();
    boolean bho13 = segmento13.setPartenzaArrivo(599, 611);
    SegmentoRagnatelaFisica segmento14 = new SegmentoRagnatelaFisica();
    boolean bho14 = segmento14.setPartenzaArrivo(524, 541);
    SegmentoRagnatelaFisica segmento15 = new SegmentoRagnatelaFisica();
    boolean bho15 = segmento15.setPartenzaArrivo(544, 557);

    SegmentoRagnatelaFisica segmento16 = new SegmentoRagnatelaFisica();
    boolean bho16 = segmento16.setPartenzaArrivo(348, 357);
    SegmentoRagnatelaFisica segmento17 = new SegmentoRagnatelaFisica();
    boolean bho17 = segmento17.setPartenzaArrivo(449, 460);
    SegmentoRagnatelaFisica segmento18 = new SegmentoRagnatelaFisica();
    boolean bho18 = segmento18.setPartenzaArrivo(17, 21);
    SegmentoRagnatelaFisica segmento19 = new SegmentoRagnatelaFisica();
    boolean bho19 = segmento19.setPartenzaArrivo(89, 106);
    SegmentoRagnatelaFisica segmento20 = new SegmentoRagnatelaFisica();
    boolean bho20 = segmento20.setPartenzaArrivo(221, 231);
    SegmentoRagnatelaFisica segmento21 = new SegmentoRagnatelaFisica();
    boolean bho21 = segmento21.setPartenzaArrivo(333, 343);
    SegmentoRagnatelaFisica segmento22 = new SegmentoRagnatelaFisica();
    boolean bho22 = segmento22.setPartenzaArrivo(436, 444);
    SegmentoRagnatelaFisica segmento23 = new SegmentoRagnatelaFisica();
    boolean bho23 = segmento23.setPartenzaArrivo(7, 13);
    SegmentoRagnatelaFisica segmento24 = new SegmentoRagnatelaFisica();
    boolean bho24 = segmento24.setPartenzaArrivo(68, 84);
    SegmentoRagnatelaFisica segmento25 = new SegmentoRagnatelaFisica();
    boolean bho25 = segmento25.setPartenzaArrivo(204, 216);

    HashMap<Integer, Integer> map_giunti = new HashMap<Integer, Integer>();
    Map<Integer, Integer> map_corrispondenze = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> map_lati = new HashMap<Integer, Integer>();
    Map<Integer, Integer> mapAccesi;

    public AccensioneRagnatela() {

        mapAccesi = new TreeMap<>();

        map_giunti.put(329, 332);
        map_giunti.put(433, 435);
        map_giunti.put(4, 6);
        map_giunti.put(64, 67);
        map_giunti.put(200, 203);
        map_giunti.put(344, 347);
        map_giunti.put(445, 448);
        map_giunti.put(14, 16);
        map_giunti.put(85, 88);
        map_giunti.put(217, 220);
        map_giunti.put(358, 361);
        map_giunti.put(461, 464);
        map_giunti.put(22, 29);
        map_giunti.put(107, 110);
        map_giunti.put(232, 235);

        map_lati.put(524, 541);
        map_lati.put(541, 524);
        map_lati.put(613, 649);
        map_lati.put(649, 613);
        map_lati.put(791, 842);
        map_lati.put(842, 791);
        map_lati.put(544, 557);
        map_lati.put(557, 544);
        map_lati.put(653, 685);
        map_lati.put(685, 653);
        map_lati.put(844, 902);
        map_lati.put(902, 844);
        map_lati.put(560, 576);
        map_lati.put(576, 560);
        map_lati.put(686, 727);
        map_lati.put(727, 686);
        map_lati.put(906, 973);
        map_lati.put(973, 906);
        map_lati.put(578, 597);
        map_lati.put(597, 578);
        map_lati.put(729, 764);
        map_lati.put(764, 729);
        map_lati.put(976, 1031);
        map_lati.put(1031, 976);
        map_lati.put(599, 611);
        map_lati.put(611, 599);
        map_lati.put(765, 789);
        map_lati.put(789, 765);
        map_lati.put(1034, 1070);
        map_lati.put(1070, 1034);
        map_lati.put(7, 13);
        map_lati.put(13, 7);
        map_lati.put(17, 21);
        map_lati.put(21, 17);
        map_lati.put(68, 84);
        map_lati.put(84, 68);
        map_lati.put(89, 106);
        map_lati.put(106, 89);
        map_lati.put(204, 216);
        map_lati.put(216, 204);
        map_lati.put(221, 231);
        map_lati.put(231, 221);
        map_lati.put(333, 343);
        map_lati.put(343, 333);
        map_lati.put(348, 357);
        map_lati.put(357, 348);
        map_lati.put(436, 444);
        map_lati.put(444, 436);
        map_lati.put(449, 460);
        map_lati.put(460, 449);

        map_corrispondenze.put(7, 44);
        map_corrispondenze.put(13, 39);
        map_corrispondenze.put(17, 34);
        map_corrispondenze.put(21, 30);
        map_corrispondenze.put(68, 169);
        map_corrispondenze.put(84, 153);
        map_corrispondenze.put(89, 148);
        map_corrispondenze.put(106, 131);
        map_corrispondenze.put(204, 299);
        map_corrispondenze.put(216, 287);
        map_corrispondenze.put(221, 282);
        map_corrispondenze.put(231, 272);
        map_corrispondenze.put(333, 408);
        map_corrispondenze.put(343, 398);
        map_corrispondenze.put(348, 393);
        map_corrispondenze.put(357, 384);
        map_corrispondenze.put(436, 509);
        map_corrispondenze.put(444, 501);
        map_corrispondenze.put(449, 496);
        map_corrispondenze.put(460, 485);

        possibiliSegmenti.add(segmento1);
        possibiliSegmenti.add(segmento2);
        possibiliSegmenti.add(segmento3);
        possibiliSegmenti.add(segmento4);
        possibiliSegmenti.add(segmento5);
        possibiliSegmenti.add(segmento6);
        possibiliSegmenti.add(segmento7);
        possibiliSegmenti.add(segmento8);
        possibiliSegmenti.add(segmento9);
        possibiliSegmenti.add(segmento10);
        possibiliSegmenti.add(segmento11);
        possibiliSegmenti.add(segmento12);
        possibiliSegmenti.add(segmento13);
        possibiliSegmenti.add(segmento14);
        possibiliSegmenti.add(segmento15);
        possibiliSegmenti.add(segmento16);
        possibiliSegmenti.add(segmento17);
        possibiliSegmenti.add(segmento18);
        possibiliSegmenti.add(segmento19);
        possibiliSegmenti.add(segmento20);
        possibiliSegmenti.add(segmento21);
        possibiliSegmenti.add(segmento22);
        possibiliSegmenti.add(segmento23);
        possibiliSegmenti.add(segmento24);
        possibiliSegmenti.add(segmento25);

        possibiliGiunti.add(giunto1);
        possibiliGiunti.add(giunto2);
        possibiliGiunti.add(giunto3);
        possibiliGiunti.add(giunto4);
        possibiliGiunti.add(giunto5);
        possibiliGiunti.add(giunto6);
        possibiliGiunti.add(giunto7);
        possibiliGiunti.add(giunto8);
        possibiliGiunti.add(giunto9);
        possibiliGiunti.add(giunto10);
        possibiliGiunti.add(giunto11);
        possibiliGiunti.add(giunto12);
        possibiliGiunti.add(giunto13);
        possibiliGiunti.add(giunto14);
        possibiliGiunti.add(giunto15);
    }


    public void accendere(int i, final GamePlayingActivity ctx) {

        mNetworkHandler = ctx.getNewtworkHandler();

        SegmentoRagnatelaFisica daAccendere = possibiliSegmenti.get(i);

        int da = daAccendere.partenza;
        int a = daAccendere.arrivo;

        int daCorrispondenza = 0;
        int aCorrispondenza = 0;

        if (map_corrispondenze.containsKey(da)) {
            daCorrispondenza = map_corrispondenze.get(da);
            aCorrispondenza = map_corrispondenze.get(map_lati.get(da));
        }

        if (da > a) {
            int supp = da;
            da = a;
            a = supp;
        }
        if (daCorrispondenza > aCorrispondenza) {
            int supp = daCorrispondenza;
            daCorrispondenza = aCorrispondenza;
            aCorrispondenza = supp;
        }

        mapAccesi.put(da, a);

        if (daCorrispondenza != 0 && aCorrispondenza != 0) {
            mapAccesi.put(daCorrispondenza, aCorrispondenza);
        }

        try {
            JSONArray pixels_array = new JSONArray();
            JSONObject tmp;
            try {

                Iterator<Integer> it = mapAccesi.keySet().iterator();
                int past = 0;

                while (it.hasNext()) {
                    Integer next = it.next();

                    for (int j = past; j < next; j++) {
                        tmp = new JSONObject();
                        tmp.put("r", 0);
                        tmp.put("g", 0);
                        tmp.put("b", 0);
                        pixels_array.put(tmp);
                    }
                    for (int j = next; j < mapAccesi.get(next); j++)
                    {
                        if(map_giunti.containsKey(next) && next!=daPartenza && next!=aPartenza)
                        {
                            tmp = new JSONObject();
                            tmp.put("r", 255);
                            tmp.put("g", 0);
                            tmp.put("b", 0);
                            pixels_array.put(tmp);
                        }
                        else if(map_giunti.containsKey(next) && next==daPartenza || next==aPartenza) {
                            tmp = new JSONObject();
                            tmp.put("r", 0);
                            tmp.put("g", 255);
                            tmp.put("b", 0);
                            pixels_array.put(tmp);
                        }
                        else if(!map_giunti.containsKey(next))
                        {
                            tmp = new JSONObject();
                            tmp.put("r", 251);
                            tmp.put("g", 175);
                            tmp.put("b", 49);
                            pixels_array.put(tmp);
                        }
                    }
                    past = mapAccesi.get(next);
                }
                for (int j = past; j < 1072; j++) {
                    tmp = new JSONObject();
                    tmp.put("r", 0);
                    tmp.put("g", 0);
                    tmp.put("b", 0);
                    pixels_array.put(tmp);
                }
            } catch (JSONException exception) {
                // No errors expected here
            }
            handleNetworkRequest(NetworkThread.SET_PIXELS, pixels_array, 0, 0);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void accendiPartenzaArrivo(int daPartenza,int aPartenza,int daArrivo,int aArrivo, final GamePlayingActivity ctx) {

        mNetworkHandler = ctx.getNewtworkHandler();
        mapAccesi.clear();

        this.daPartenza = daPartenza;
        this.aPartenza = aPartenza;

        if (daArrivo > aArrivo) {
            int supp = daArrivo;
            daArrivo = aArrivo;
            aArrivo = supp;
        }
        if (daPartenza > aPartenza) {
            int supp = daPartenza;
            daPartenza = aPartenza;
            aPartenza = supp;
        }
        mapAccesi.put(daPartenza,aPartenza);
        mapAccesi.put(daArrivo,aArrivo);

        Log.d("giunto partenza da ",String.valueOf(daPartenza));
        Log.d("giunto partenza a ",String.valueOf(aPartenza));
        Log.d("giunto arrivo da ",String.valueOf(daArrivo));
        Log.d("giunto arrivo a",String.valueOf(aArrivo));

        try {
            JSONArray pixels_array = new JSONArray();
            JSONObject tmp;
            try {
                for (int j = 0; j < 1072; j++) {
                    tmp = new JSONObject();
                    tmp.put("a", 0);

                    if (j < daArrivo || j > aArrivo && j < daPartenza || j > aPartenza) {
                        tmp.put("r", 0);
                        tmp.put("g", 0);
                        tmp.put("b", 0);
                    } else if (j > daPartenza && j < aPartenza) {
                        tmp.put("r", 0);
                        tmp.put("g", 255);
                        tmp.put("b", 0);
                    } else if (j > daArrivo && j < aArrivo) {
                        tmp.put("r", 255);
                        tmp.put("g", 0);
                        tmp.put("b", 0);
                    }
                    pixels_array.put(tmp);
                }
            } catch (JSONException exception) {
                // No errors expected here
            }
            handleNetworkRequest(NetworkThread.SET_PIXELS, pixels_array, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void handleNetworkRequest(int what, Object payload, int arg1, int arg2) {
        Message msg = mNetworkHandler.obtainMessage();
        msg.what = what;
        msg.obj = payload;
        msg.arg1 = arg1;
        msg.arg2 = arg2;
        msg.sendToTarget();
    }

}
