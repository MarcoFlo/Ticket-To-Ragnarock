package com.example.giuseppegarone.tickettoragnarok;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TopPlayersActivity extends AppCompatActivity {

    ImageButton buttonBackHome;
    ListView listViewScores;
    TextView header;
    Typeface customFont;

    // List to store all the scores from firebase database
    List<Score> scores;

    // Database reference object
    DatabaseReference databaseClassifica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_players);

        // Getting the reference of classifica node
        databaseClassifica = FirebaseDatabase.getInstance().getReference("Classifica");

        listViewScores = (ListView) findViewById(R.id.listViewScores);
        buttonBackHome = (ImageButton) findViewById(R.id.buttonBackHome);
        header = (TextView) findViewById(R.id.textView);
        customFont = Typeface.createFromAsset(getAssets(), "gameplay.ttf");
        header.setTypeface(customFont);

        // List to store scores
        scores = new ArrayList<>();

        // Listener BACK MENU button
        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), GameMenuActivity.class);
                i.putExtra("sender", "winactivity");
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Query query = databaseClassifica.orderByChild("score");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Clearing the previous score list
                scores.clear();

                // Iterating through all the nodes
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    // Getting score
                    Score punteggio = child.getValue(Score.class);

                    // Adding score to the list
                    scores.add(punteggio);
                }

                // Creating adapter, than attaching it to the listview
                TopPlayersList scoreAdapter = new TopPlayersList(TopPlayersActivity.this, scores);
                listViewScores.setAdapter(scoreAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
