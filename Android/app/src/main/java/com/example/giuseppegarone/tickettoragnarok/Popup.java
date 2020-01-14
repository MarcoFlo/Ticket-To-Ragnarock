package com.example.giuseppegarone.tickettoragnarok;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class Popup extends Activity {

    public ImageButton confirmButton;
    public CheckBox risp1, risp2, risp3, risp4;
    public CountDownTimer countDownTimer;
    public String risp1Testo = "";
    public String risp2Testo = "";
    public String risp3Testo = "";
    public String risp4Testo = "";
    public String rispScelta = "";
    public String rispGiusta = "";
    public TextView testoDomanda;
    public TextView time;

    public double bonus1 = 1.5;
    public double bonus2 = 2.5;
    public double bonus3 = 3.5;
    public double bonus4 = 4.5;
    public int finalScore;
    public int residualTime;
    public int timerDurationSecs = 21;

    // Database reference object
    DatabaseReference databaseDomande;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window);

        // Getting the reference of Domande node
        databaseDomande = FirebaseDatabase.getInstance().getReference("Domande");

        // Setting popup size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.85));

        time = (TextView) findViewById(R.id.time_value);
        testoDomanda = (TextView) findViewById(R.id.testo_domanda);
        risp1 = (CheckBox) findViewById(R.id.risposta1);
        risp2 = (CheckBox) findViewById(R.id.risposta2);
        risp3 = (CheckBox) findViewById(R.id.risposta3);
        risp4 = (CheckBox) findViewById(R.id.risposta4);
        confirmButton = (ImageButton) findViewById(R.id.confirm_button);
        confirmButton.setImageResource(R.drawable.confirm_disabled_btn);
        confirmButton.setEnabled(false);

        // Starting timer
        start();

        databaseDomande.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int n = (int)dataSnapshot.getChildrenCount();

                Random rand = new Random();
                int i = rand.nextInt(n);

                final ArrayList<Question> objects = new ArrayList<>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Question object = child.getValue(Question.class);
                    objects.add(object);
                }

                Question Dscelta = objects.get(i);

                // Saving question and answer values
                testoDomanda.setText(Dscelta.getDtext());
                risp1.setText(Dscelta.getR1());
                risp2.setText(Dscelta.getR2());
                risp3.setText(Dscelta.getR3());
                risp4.setText(Dscelta.getR4());
                risp1Testo = Dscelta.getR1();
                risp2Testo = Dscelta.getR2();
                risp3Testo = Dscelta.getR3();
                risp4Testo = Dscelta.getR4();
                rispGiusta = Dscelta.getRgiusta();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Listener CONFIRM button
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkAnswer(rispScelta, rispGiusta)) {

                    // Calculate final score
                    scoreBonus(residualTime);

                    // Stop timer
                    cancel();

                    Intent i = new Intent(getApplicationContext(), WinActivity.class);
                    i.putExtra("punti", finalScore);
                    startActivity(i);
                } else {

                    // Stop timer
                    cancel();

                    Intent i = new Intent(getApplicationContext(), LoseActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Disable BACK button
    }

    /**
     * Check which checkbox is checked
     *
     * @param view is a checkbox
     */
    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {

            case R.id.risposta1:
                if (checked) {
                    rispScelta = risp1Testo;

                    confirmButton.setImageResource(R.drawable.confirm_btn);
                    confirmButton.setEnabled(true);

                    risp2.setChecked(false);
                    risp3.setChecked(false);
                    risp4.setChecked(false);
                } else {
                    confirmButton.setEnabled(false);
                }
                break;

            case R.id.risposta2:
                if (checked) {
                    rispScelta = risp2Testo;

                    confirmButton.setImageResource(R.drawable.confirm_btn);
                    confirmButton.setEnabled(true);

                    risp1.setChecked(false);
                    risp3.setChecked(false);
                    risp4.setChecked(false);
                } else {
                    confirmButton.setEnabled(false);
                }
                break;

            case R.id.risposta3:
                if (checked) {
                    rispScelta = risp3Testo;

                    confirmButton.setImageResource(R.drawable.confirm_btn);
                    confirmButton.setEnabled(true);

                    risp1.setChecked(false);
                    risp2.setChecked(false);
                    risp4.setChecked(false);
                } else {
                    confirmButton.setEnabled(false);
                }
                break;

            case R.id.risposta4:
                if (checked) {
                    rispScelta = risp4Testo;

                    confirmButton.setImageResource(R.drawable.confirm_btn);
                    confirmButton.setEnabled(true);

                    risp1.setChecked(false);
                    risp2.setChecked(false);
                    risp3.setChecked(false);
                } else {
                    confirmButton.setEnabled(false);
                }
                break;
        }

    }

    /**
     * Start the timer
     */
    public void start() {

        countDownTimer = new CountDownTimer(timerDurationSecs*1000, 1000) {

            // onTick method manage the flow of the timer.
            @Override
            public void onTick(long millisUntilFinisched) {
                residualTime = (int)millisUntilFinisched/1000;
                time.setText("" + residualTime);

                if(millisUntilFinisched/1000 < 10) {
                    time.setTextColor(Color.RED);
                }
            }

            // End of the timer
            @Override
            public void onFinish() {
                cancel();

                Intent i = new Intent(getApplicationContext(), LoseActivity.class);
                startActivity(i);
            }
        };

        countDownTimer.start();
    }

    // Interrupt the timer
    public void cancel() {
        if(countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    /**
     * Compare the choosed answer to the right answer.
     *
     * @param a first string
     * @param b second string
     * @return true if the strings have the same value, false otherwise
     */
    public boolean checkAnswer(String a, String b) {
        if(a.equals(b)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method calculate the final score.
     *
     * @param n is the residualTime
     * @return the final score
     */
    public int scoreBonus (int n) {
        if(n > 17) {
            finalScore = n + (int) (n * bonus4 * bonus3 * bonus2 * bonus1);
        } else {
            if(n > 14 && n < 17) {
                finalScore = n + (int) (n * bonus3 * bonus2 * bonus1);
            } else {
                if(n > 10 && n < 14) {
                    finalScore = n + (int) (n * bonus2 * bonus1);
                } else {
                    finalScore = n + (int) (n * bonus1);
                }
            }
        }

        return finalScore;
    }

}
