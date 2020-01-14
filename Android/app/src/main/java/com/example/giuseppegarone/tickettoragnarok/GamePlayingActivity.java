package com.example.giuseppegarone.tickettoragnarok;

import android.content.Context;
import android.content.Intent;
import static com.example.giuseppegarone.tickettoragnarok.GlobalVariables.*;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GamePlayingActivity extends AppCompatActivity {

    public Canvas c = new Canvas();
    public ImageButton movimentoOrario;
    public ImageButton movimentoAntiOrario;
    public ImageButton movimentoInterno;
    public ImageButton movimentoEsterno;

    private NetworkThread mNetworkThread = null;
    private Handler mNetworkHandler, mMainHandler;

    List<Point> stradePassate = new ArrayList<Point>();
    AccensioneRagnatela a = null;

    int daPartenza, aPartenza, daArrivo, aArrivo=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_playing);

        final DrawView v = new DrawView(this);
        a = new AccensioneRagnatela();
        addContentView(v, new ViewGroup.LayoutParams(600, 600));
        v.setX(425);
        v.setY(70);
        v.draw(c);
        stradePassate.add(v.posAttuale);

        daPartenza=a.possibiliGiunti.get(v.i).partenza;
        aPartenza=a.possibiliGiunti.get(v.i).arrivo;
        daArrivo=a.possibiliGiunti.get(v.h).partenza;
        aArrivo=a.possibiliGiunti.get(v.h).arrivo;

        movimentoInterno = (ImageButton)findViewById(R.id.movimento2_btn);
        movimentoInterno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(v.i < 10){

                    a.accendere(v.i+15, GamePlayingActivity.this);
                    v.i = v.i + 5;
                } else {
                    v.i = v.i;
                }

                v.invalidate();
                stradePassate.add(v.posAttuale);
            }
        });

        movimentoEsterno = (ImageButton)findViewById(R.id.movimento3_btn);
        movimentoEsterno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(v.i > 4){
                    a.accendere(v.i+10, GamePlayingActivity.this);
                    v.i = v.i - 5;
                } else {
                    v.i = v.i;
                }

                v.invalidate();
                stradePassate.add(v.posAttuale);
            }
        });

        movimentoOrario = (ImageButton)findViewById(R.id.movimento1_btn);
        movimentoOrario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(v.i == 4 || v.i == 9 || v.i == 14) {
                    if(v.i == 4) {
                        a.accendere(0, GamePlayingActivity.this);
                        v.i = 0;
                    }
                    if(v.i == 9) {
                        a.accendere(5, GamePlayingActivity.this);
                        v.i = 5;
                    }
                    if(v.i == 14) {
                        a.accendere(10, GamePlayingActivity.this);
                        v.i = 10;
                    }
                } else {
                    if(v.i == 0 || v.i == 1 || v.i == 2 || v.i == 3 || v.i == 5 || v.i == 6 || v.i == 7 || v.i == 8 || v.i == 10 || v.i == 11 || v.i == 12 || v.i == 13) {
                        a.accendere(v.i+1, GamePlayingActivity.this);
                        v.i = v.i + 1;
                    }
                }

                v.invalidate();
                stradePassate.add(v.posAttuale);
            }
        });

        movimentoAntiOrario = (ImageButton)findViewById(R.id.change_color_btn);
        movimentoAntiOrario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(v.i == 0 || v.i == 5 || v.i == 10) {
                    if(v.i == 0) {
                        a.accendere(0, GamePlayingActivity.this);
                        v.i = 4;
                    }
                    if(v.i == 5){
                        a.accendere(5, GamePlayingActivity.this);
                        v.i = 9;
                    }
                    if(v.i == 10){
                        a.accendere(10, GamePlayingActivity.this);
                        v.i = 14;
                    }
                } else {
                    if(v.i == 1 || v.i == 2 || v.i == 3 || v.i == 4 || v.i == 6 || v.i == 7 || v.i == 8 || v.i == 9 || v.i == 11 || v.i == 12 || v.i == 13 || v.i == 14) {
                        a.accendere(v.i, GamePlayingActivity.this);
                        v.i = v.i - 1;
                    }
                }

                v.invalidate();
                stradePassate.add(v.posAttuale);
            }
        });

    }

    @Override
    public void onBackPressed() {
        // Disable BACK button
    }

    @Override
    protected void onResume() {
        super.onResume();
        startHandlerThread();
        a.accendiPartenzaArrivo(daPartenza,aPartenza,daArrivo,aArrivo,GamePlayingActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNetworkThread != null && mNetworkHandler != null) {
            mNetworkHandler.removeMessages(mNetworkThread.SET_PIXELS);
            mNetworkHandler.removeMessages(mNetworkThread.SET_DISPLAY_PIXELS);
            mNetworkHandler.removeMessages(mNetworkThread.SET_SERVER_DATA);
            mNetworkThread.quit();
            try {
                mNetworkThread.join(100);
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            } finally {
                mNetworkThread = null;
                mNetworkHandler = null;
            }
        }
    }

    public void startHandlerThread() {
        mMainHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Toast.makeText(GamePlayingActivity.this, (String) msg.obj, Toast.LENGTH_LONG).show();
            }
        };

        mNetworkThread = new NetworkThread(mMainHandler);
        mNetworkThread.start();
        mNetworkHandler = mNetworkThread.getNetworkHandler();
    }

    public Handler getNewtworkHandler() {
        return mNetworkHandler;
    }

}
