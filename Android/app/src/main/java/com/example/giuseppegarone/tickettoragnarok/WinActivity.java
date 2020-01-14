package com.example.giuseppegarone.tickettoragnarok;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WinActivity extends AppCompatActivity {

    EditText editTextName;
    ImageButton buttonAddScore;
    TextView punti;
    TextView titoloVittoria;
    Typeface customFont;

    public int punteggio;

    // Database reference object
    DatabaseReference databaseClassifica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        // Getting the reference of classifica node
        databaseClassifica = FirebaseDatabase.getInstance().getReference("Classifica");

        punti = (TextView)findViewById(R.id.punteggio);
        titoloVittoria = (TextView)findViewById(R.id.win_activity_title);
        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonAddScore = (ImageButton) findViewById(R.id.buttonAddScore);

        buttonAddScore.setImageResource(R.drawable.savescore_btn);

        customFont = Typeface.createFromAsset(getAssets(), "gameplay.ttf");
        titoloVittoria.setTypeface(customFont);

        // Getting score from intent
        Bundle extra = getIntent().getExtras();
        punteggio = extra.getInt("punti");
        punti.setText(String.valueOf(punteggio));

        // Listener SAVE SCORE button
        buttonAddScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // addScore() method is actually performing the write operation
                addScore();

                Intent i = new Intent(getApplicationContext(), TopPlayersActivity.class);
                startActivity(i);
            }
        });
    }

    /*
    * This method is saving a new score to the
    * Firebase Realtime Database
    * */
    private void addScore() {

        // Getting the values to save
        String nickname = editTextName.getText().toString().trim();
        Log.d("Nome: ", nickname);
        int score = punteggio;
        Log.d("Punti: ", Integer.toString(score));

        // Checking if the value is provided
        if (!TextUtils.isEmpty(nickname)) {

            // push().getKey() method will create a unique id (which is the Primary Key)
            String id = databaseClassifica.push().getKey();

            // Creating a Score Object
            Score punteggiodatabase = new Score(nickname, score);

            // Saving the Score
            databaseClassifica.child(id).setValue(punteggiodatabase);

            // Setting edittext to blank again
            editTextName.setText("");

            // Displaying a success toast
            Toast.makeText(this, "New Score added", Toast.LENGTH_LONG).show();
        } else {
            // If the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }

}
