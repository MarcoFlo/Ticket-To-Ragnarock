package com.example.giuseppegarone.tickettoragnarok;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends Activity {
    public static final String TAG = "mainActivity:";

    Unbinder unbinder;

    private String host_url = "192.168.1.32";
    private int host_port = 8080;

    private TextWatcher myIpTextWatcher;
    private JSONArray pixels_array;
    private Handler mNetworkHandler, mMainHandler;
    private NetworkThread mNetworkThread = null;

    public TextView mainTitle;
    public Typeface customFont;

    // START button
    @BindView(R.id.start_button)
    ImageButton startButton;

    @BindViews({R.id.first_byte_ip, R.id.second_byte_ip, R.id.third_byte_ip, R.id.fourth_byte_ip})
    List<EditText> ip_address_bytes;

    @BindView(R.id.host_port)
    EditText hostPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        customFont = Typeface.createFromAsset(getAssets(), "gameplay.ttf");
        mainTitle = (TextView)findViewById(R.id.main_title);
        mainTitle.setTypeface(customFont);

        unbinder = ButterKnife.bind(this);

        startButton.setImageResource(R.drawable.start_disabled_btn);
        startButton.setEnabled(false);

        myIpTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(checkCorrectIp()){
                    startButton.setImageResource(R.drawable.start_btn);
                    createAndCheckIp();
                    startButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        for (EditText ip_byte : ip_address_bytes) {
            ip_byte.addTextChangedListener(myIpTextWatcher);
        }

        hostPort.addTextChangedListener(myIpTextWatcher);

        pixels_array = preparePixelsArray();

        mMainHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Toast.makeText(MainActivity.this, (String) msg.obj, Toast.LENGTH_LONG).show();
            }
        };

    }

    public void startHandlerThread() {
        mNetworkThread = new NetworkThread(mMainHandler);
        mNetworkThread.start();
        mNetworkHandler = mNetworkThread.getNetworkHandler();
    }

    /**
     * Create and store in global variables the server address list.
     *
     * @return true if success, false otherwise
     */
    private boolean createAndCheckIp(){
        StringBuilder sb = new StringBuilder();
        String port = hostPort.getText().toString();
        if(hostPort.getText().length() == 0 || port.length() == 0){
            //Err: no text
            Log.e(TAG, "No text");
            return false;
        }
        if(Integer.parseInt(port)<0 || Integer.parseInt(port)>65000){
            //Err: wrong port
            Log.e(TAG, "Wrong port");
            return false;
        }
        for(int i = 0; i<ip_address_bytes.size(); i++){ //TODO: already check
            sb.append(ip_address_bytes.get(i).getText());
            if(i<ip_address_bytes.size()-1){
                sb.append(".");
            } else {
                sb.append(":");
            }
        }
        sb.append(port);
        Log.d(TAG, "Host url: " + sb.toString());
        GlobalVariables.setParsedServerURL(sb.toString());
        return true;
    }

    private boolean checkCorrectIp() {
        StringBuilder sb = new StringBuilder();
        int port;

        if (hostPort.getText().length() == 0)
            return false;

        for (EditText editText : ip_address_bytes) {
            sb.append(editText.getText().toString());
            sb.append(".");
        }
        //cancello l'ultimo "."
        sb.deleteCharAt(sb.length() - 1);

        port = Integer.parseInt(hostPort.getText().toString());
        if (validIP(sb.toString()) && port >= 0 & port <= 65535) {
            host_url = sb.toString();
            host_port = port;
            return true;
        } else
            return false;
    }

    // From http://stackoverflow.com/questions/4581877/validating-ipv4-string-in-java
    public static boolean validIP(String ip) {
        try {
            if (ip == null || ip.isEmpty()) {
                return false;
            }

            String[] parts = ip.split("\\.");
            if (parts.length != 4) {
                return false;
            }

            for (String s : parts) {
                int i = Integer.parseInt(s);
                if ((i < 0) || (i > 255)) {
                    return false;
                }
            }
            if (ip.endsWith(".")) {
                return false;
            }

            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startHandlerThread();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mNetworkThread != null && mNetworkHandler != null) {
            mNetworkThread.quitSafely();
            try {
                mNetworkThread.join();
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            } finally {
                mNetworkThread = null;
                mNetworkHandler = null;
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    // Listener START button
    @OnClick(R.id.start_button)
    void startApp(){

        handleNetworkRequest(NetworkThread.SET_SERVER_DATA, host_url, host_port ,0);

        final int DISPLAY_SIZE = 1024;
        final int LED_SIZE = 1072;

        // Spengo tutti i pixel del display
        try {
            JSONArray pixels_array = new JSONArray();

            for (int i = 0; i < DISPLAY_SIZE; i++) {
                JSONObject o=new JSONObject();
                o.put("r", 0);
                o.put("g", 0);
                o.put("b", 0);
                pixels_array.put(o);
            }
            handleNetworkRequest(NetworkThread.SET_DISPLAY_PIXELS, pixels_array, 0 ,0);
        } catch (JSONException e) {
            // There should be no Exception
        }

       // Spengo tutti i led
        try {
            JSONArray pixels_array = new JSONArray();

            for (int i = 0; i < LED_SIZE; i++) {
                JSONObject o=new JSONObject();
                o.put("r", 0);
                o.put("g", 0);
                o.put("b", 0);
                pixels_array.put(o);
            }
            handleNetworkRequest(NetworkThread.SET_PIXELS, pixels_array, 0 ,0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Intent i = new Intent(getApplicationContext(), GameMenuActivity.class);
            i.putExtra("sender", "splashscreen");
            i.putExtra("hostUrl", host_url);
            i.putExtra("hostPort", host_port);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();
        }catch (Exception e){
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

    JSONArray preparePixelsArray() {
        JSONArray pixels_array = new JSONArray();
        JSONObject tmp;
        try {
            for (int i = 0; i < 1072; i++) {
                tmp = new JSONObject();
                tmp.put("a", 0);
                if (i < 522) {
                    tmp.put("g", 255);
                    tmp.put("popupButton", 0);
                    tmp.put("r", 0);
                } else if (i < 613) {
                    tmp.put("r", 255);
                    tmp.put("g", 0);
                    tmp.put("popupButton", 0);
                } else if (i < 791) {
                    tmp.put("popupButton", 255);
                    tmp.put("g", 0);
                    tmp.put("r", 0);
                } else {
                    tmp.put("popupButton", 255);
                    tmp.put("g", 0);
                    tmp.put("r", 255);
                }
                pixels_array.put(tmp);
            }
        } catch (JSONException exception) {
            // No errors expected here
        }
        return pixels_array;
    }

}
