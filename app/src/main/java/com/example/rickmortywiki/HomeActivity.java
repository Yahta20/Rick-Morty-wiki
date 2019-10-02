package com.example.rickmortywiki;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {

    private String jsonReqest = "";
    private TextView mTextRes;
    private JSONObject jsonObject;
    private LinearLayout lo4spc;
    DBDriver dbDriver;

    //HomeActivity(String s){
    //    jsonReqest = s;
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //mTextRes = findViewById(R.id.textView2);

        //1-Creation-of-database--------------------------------------------
//
        /*
        dbDriver = new DBDriver(this);
        OkHttpClient  client = new OkHttpClient();
        String url = "https://rickandmortyapi.com/api/character/";

        final SQLiteDatabase db = dbDriver.getWritableDatabase();
        final ContentValues cv = new ContentValues();

        do {

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        //jsonReqest = response.body().string();
                        final String resp = response.body().string();
                        HomeActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    jsonObject = new JSONObject(resp);
                                    JSONArray arr4cond = jsonObject.getJSONArray("info");
                                    JSONObject obj4cond = arr4cond.getJSONObject(2);
                                    jsonReqest = obj4cond.getString("next");

                                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                                    for (int i=0;i<20;i++){
                                        JSONObject obj1 = jsonArray.getJSONObject(i);
                                        cv.put(dbDriver.KEY_NAME,obj1.getString("name"));
                                        cv.put(dbDriver.KEY_GENDER,obj1.getString("gender"));
                                        cv.put(dbDriver.KEY_STATUS,obj1.getString("status"));
                                    }



                                    //jsonReqest = "Name: " + obj1.getString("name") + "\nStatus: " + obj1.getString("status");

                                    //mTextRes.setText(jsonReqest);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            });
            url = jsonReqest;
        }
        while (jsonReqest !="");
*/
//
        //1-end-------------------------------------------------------------
        //2-creation-of-view------------------------------------------------

        lo4spc = findViewById(R.id.space4data);
        LinearLayout.LayoutParams lil = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.       setLayoutParams(lil);
        lo4spc.addView(ll);


        Button prof         =   new Button  (this);
        prof.       setLayoutParams(lil);
        prof.       setText("PIC"      );
       // prof.
        ll.addView(prof     );


        TextView tvID       =   new TextView(this);
        tvID.       setLayoutParams(lil);
        tvID.       setText("ID"        );
        ll.         addView(tvID     );

        TextView tvName     =   new TextView(this);
        tvName.     setLayoutParams(lil);
        tvName.     setText("Name"      );
        ll.addView(tvName   );

        TextView tvGender   =   new TextView(this);
        tvGender.   setLayoutParams(lil);
        tvGender.   setText("Gender"    );
        ll.addView(tvGender );

        TextView tvStatus   =   new TextView(this);
        tvStatus.   setLayoutParams(lil);
        tvStatus.   setText("Status"    );
        ll.addView(tvStatus );




        //2-end------------------------------------------------------------

    }
}
