package com.example.giuseppegarone.tickettoragnarok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class GameTutorialActivity extends AppCompatActivity {

    public ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_tutorial);

        // Adding GIF image
        GifImageView gifImageView = (GifImageView) findViewById(R.id.GifImageView);
        gifImageView.setGifImageResource(R.drawable.tutorial_p1);
        gifImageView.setScaleX(1.2f);
        gifImageView.setScaleY(1.2f);

        backButton = (ImageButton)findViewById(R.id.back_button);

        // Listener MAIN MENU button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), GameMenuActivity.class);
                i.putExtra("sender", "winactivity");
                startActivity(i);
            }
        });
    }
}
