package com.example.giuseppegarone.tickettoragnarok;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameMenuActivity extends AppCompatActivity {

    public ImageButton playButton, tutorialButton, topPlayersButton;
    public TextView creditsView;
    public Typeface customFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        String sender = getIntent().getExtras().getString("sender");
        if(sender != null && sender.equals("splashScreen")){
            GlobalVariables.RAW_IP = getIntent().getExtras().getString("hostUrl");
            GlobalVariables.RAW_PORT = getIntent().getExtras().getInt("hostPort");
        }

        creditsView = (TextView)findViewById(R.id.credits);
        customFont = Typeface.createFromAsset(getAssets(), "gameplay.ttf");
        creditsView.setTypeface(customFont);
        playButton = (ImageButton)findViewById(R.id.play_game_button);
        tutorialButton = (ImageButton)findViewById(R.id.tutorial_button);
        topPlayersButton = (ImageButton)findViewById(R.id.top_players_button);

        // Listener PLAY button
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GamePlayingActivity.class);
                i.putExtra("hostUrl", GlobalVariables.RAW_IP);
                i.putExtra("hostPort", GlobalVariables.RAW_PORT);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            }
        });

        // Listener TUTORIAL button
        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GameTutorialActivity.class);
                startActivity(i);
            }
        });

        // Listener TOP PLAYERS button
        topPlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TopPlayersActivity.class);
                startActivity(i);
            }
        });
    }

}
