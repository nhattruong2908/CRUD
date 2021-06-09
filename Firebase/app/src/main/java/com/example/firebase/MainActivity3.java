package com.example.firebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {
    private Button btngetdata,btngetjson,btngetarrayjson,btnpost,btnput,btndelete;
    private TextView textView;
    String url = "https://60b6b44b17d1dc0017b88369.mockapi.io/test/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btngetdata = (Button) findViewById(R.id.btngetdata);
        btngetarrayjson = (Button) findViewById(R.id.btngetarrayjson);
        btngetjson = (Button) findViewById(R.id.btngetjson);
        btnpost = (Button) findViewById(R.id.btnpost);
        btnput = (Button) findViewById(R.id.btnput);
        btndelete = (Button) findViewById(R.id.btndelete);
        textView = (TextView) findViewById(R.id.textView);


        btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetData(url);






            }
        });
        btngetjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetJson(url);
            }
        });
        btngetarrayjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetArrayJson(url);
            }
        });
        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostAPI(url);
            }
        });
        btnput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PutAPI(url);
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAPI(url);
            }
        });
    }
    public  void GetData(String url){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3.this,"error make by api",Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void GetJson(String url){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    textView.setText(response.getString("name").toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3.this,"error by get json",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(MainActivity3.this);
        queue.add(jsonObjectRequest);

    }
    public void GetArrayJson(String url){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3.this,"error by get json array",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    private  void PostAPI(String url){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity3.this, "thanh cong", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3.this,"error by post data",Toast.LENGTH_SHORT).show();
            }
        })
        {
            protected Map<String, String> GetParam() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("id", "123");
                param.put("name", "Truong");
                return param;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private  void PutAPI(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url + '/' + 2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity3.this, "thanh cong", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3.this,"error",Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param=new HashMap<>();
                param.put("name","abc");
                return param;
            }
        };

        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private  void DeleteAPI(String url){
        StringRequest stringRequest=new StringRequest(Request.Method.DELETE, url + "/" + 1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity3.this, "thanh cong", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3.this,"Error by delete",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}