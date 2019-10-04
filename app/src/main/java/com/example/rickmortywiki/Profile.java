package com.example.rickmortywiki;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Profile extends AppCompatActivity {

    private String URL = "https://rickandmortyapi.com/api/character/";
    private JSONObject jsonObject = null;
    private RequestTask rt ;
    private LinearLayout lo4spc;
    private ImageView iv;
    //Profile (String s){
    //    this.URL+=s;
    //}

    class RequestTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String ...urls ) {
            ArrayList<String> tal = new ArrayList<String>();
            HttpURLConnection connection = null;
            BufferedReader reader =null;
            try {
                java.net.URL url = new URL (urls[0]);
                String y = "_";
                //-1-получение списка всех персонаже
                    //-11-запрос в "космос"

                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream stream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuffer buffer = new StringBuffer();
                    String line = "";
                    while (( line = reader.readLine())!= null){
                        buffer.append(line);
                    }
                    //-11-запрос в "космос"

                return buffer.toString();
                //-1-получение списка всех персонажей
            }
            catch (Exception e){
                return e.getMessage();
            }finally{
                if (connection!=null){
                    connection.disconnect();
                }
                try{
                    if (reader!=null){
                        reader.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }

        @Override
        protected void onPostExecute(String massage){
            super.onPostExecute(massage);
            //Toast.makeText(HomeActivity.this, massage, Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Intent i = getIntent();
        String Id = i.getStringExtra("Id");
        rt = new RequestTask();
        URL+=Id;
        iv = findViewById(R.id.imageView2);
        lo4spc = findViewById(R.id.mainlay);
        rt.execute(URL);
        try {
            jsonObject = new JSONObject(rt.get());
            TextView mTextid    = findViewById(R.id.textView11);
            TextView mTextname  = findViewById(R.id.textView13);
            TextView mTextspc   = findViewById(R.id.textView15);
            TextView mTextgen   = findViewById(R.id.textView19);
            TextView mTextsts   = findViewById(R.id.textView23);

            mTextid.    setText(jsonObject.getString("id"));
            mTextname.  setText(jsonObject.getString("name"));
            mTextspc.   setText(jsonObject.getString("species"));
            mTextgen.   setText(jsonObject.getString("gender"));
            mTextsts.   setText(jsonObject.getString("status"));
            loadImgFromUrl(jsonObject.getString("image"));
            //lo4spc.setBackgroundResource(iv);



            //obj1.getString("name")
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadImgFromUrl(String image) {
        Picasso.with(this).load(image).placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .into(iv, new com.squareup.picasso.Callback(){

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
        ;
    }


}
