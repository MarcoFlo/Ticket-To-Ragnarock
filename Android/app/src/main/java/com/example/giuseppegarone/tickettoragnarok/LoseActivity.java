package com.example.giuseppegarone.tickettoragnarok;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class LoseActivity extends AppCompatActivity {

    public ImageButton tryAgainBtn, backToMenuBtn;
    public TextView titoloPerdita;
    public Typeface customFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);

        tryAgainBtn = (ImageButton)findViewById(R.id.try_again_btn);
        backToMenuBtn = (ImageButton)findViewById(R.id.back_to_menu_btn);
        titoloPerdita = (TextView)findViewById(R.id.lose_activty_title);

        customFont = Typeface.createFromAsset(getAssets(), "gameplay.ttf");
        titoloPerdita.setTypeface(customFont);

        // Listener TRY AGAIN button
        tryAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GamePlayingActivity.class);
                startActivity(i);
            }
        });

        // Listener MAIN MENU button
        backToMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GameMenuActivity.class);
                i.putExtra("sender", "loseactivity");
                startActivity(i);
            }
        });
    }

}
