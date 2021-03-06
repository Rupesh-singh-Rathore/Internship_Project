package com.example.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView total_cases,today_cases, total_Recovered, today_recovered,total_death,today_death;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        total_cases = findViewById(R.id.first);
        today_cases = findViewById(R.id.two);
        total_Recovered = findViewById(R.id.three);
        today_recovered = findViewById(R.id.four);
        total_death = findViewById(R.id.five);
        today_death = findViewById(R.id.six);

        fetchData();

    }

    private void fetchData() {

        String url ="https://corona.lmao.ninja/v2/all/";

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());

                            total_cases.setText(jsonObject.getString("cases"));
                            today_cases.setText(jsonObject.getString("todayCases"));
                            total_Recovered.setText(jsonObject.getString("recovered"));
                            today_recovered.setText(jsonObject.getString("todayRecovered"));
                            total_death.setText(jsonObject.getString("deaths"));
                            today_death.setText(jsonObject.getString("todayDeaths"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
