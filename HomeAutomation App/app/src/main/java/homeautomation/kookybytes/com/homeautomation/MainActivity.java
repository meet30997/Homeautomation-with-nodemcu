package homeautomation.kookybytes.com.homeautomation;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
private ToggleButton li1, li2 ,fn, all;
private RadioButton b1, b2, b3,b4;
private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        li1 = (ToggleButton) findViewById(R.id.l1);
        li2 = (ToggleButton) findViewById(R.id.l2);
        fn = (ToggleButton) findViewById(R.id.f1);
        all = (ToggleButton) findViewById(R.id.allon);
        b1 = (RadioButton) findViewById(R.id.rb1);
        b2 = (RadioButton) findViewById(R.id.rb2);
        b3 = (RadioButton) findViewById(R.id.rb3);
        b4 = (RadioButton) findViewById(R.id.rb4);


        senddata("http://192.168.0.102/status0");
        senddata("http://192.168.0.102/status1");
        senddata("http://192.168.0.102/status2");
        senddata("http://192.168.0.102/onall");




        li1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
          senddata("http://192.168.0.102/on");
         b1.setChecked(true);
        } else {
            senddata("http://192.168.0.102/off");
            b1.setChecked(false);


        }
    }
});


        li2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    senddata("http://192.168.0.102/1on");
                    b2.setChecked(true);
                } else {
                    senddata("http://192.168.0.102/1off");
                    b2.setChecked(false);

                }
            }
        });


        fn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    senddata("http://192.168.0.102/2on");
                    b3.setChecked(true);
                } else {
                    senddata("http://192.168.0.102/2off");
                    b3.setChecked(false);



                }
            }
        });
        all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    senddata("http://192.168.0.102/allon");
                    li1.setChecked(true);
                    li2.setChecked(true);
                    fn.setChecked(true);
                    b4.setChecked(true);


                } else {
                    senddata("http://192.168.0.102/alloff");
                    li1.setChecked(false);
                    li2.setChecked(false);
                    fn.setChecked(false);
                    b4.setChecked(false);

                }
            }
        });



    }




    public void senddata(String URl){
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, URl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.toString().equals("0L1")){
                    li1.setChecked(true);
                }else if (response.toString().equals("1L1")){
                    li1.setChecked(false);


                }else if (response.toString().equals("0L2")) {
                    li2.setChecked(true);
                }else if (response.toString().equals("1L2")) {
                    li2.setChecked(false);
                }else if (response.toString().equals("0FN")) {
                    fn.setChecked(true);
                }else if (response.toString().equals("FN")) {
                    fn.setChecked(false);
                }else if (response.toString().equals("aon")) {
                    li1.setChecked(true);
                    li2.setChecked(true);
                    fn.setChecked(true);
                    b4.setChecked(true);
                    all.setChecked(true);
                }

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {

        };
queue.add(ExampleStringRequest);

    }



}



