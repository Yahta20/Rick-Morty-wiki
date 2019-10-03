package com.example.rickmortywiki;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {

    private String jsonReqest;

    private JSONObject jsonObject = null;
    private LinearLayout lo4spc;

    private ArrayList<String> al = new ArrayList<String>();

    private RequestTask rt ;

    private final int BUTTONID = 6000;
    private int countID;

    class RequestTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String ...urls ) {
            ArrayList<String> tal = new ArrayList<String>();
            HttpURLConnection connection = null;
            BufferedReader reader =null;
            try {
                URL url = new URL (urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while (( line = reader.readLine())!= null){
                    buffer.append(line);
                }
                //-------------------------------------
                jsonObject = new JSONObject(buffer.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("results");


                for (int i=0;i<20;i++){
                    JSONObject obj1 = jsonArray.getJSONObject(i);
                    al.add((String) obj1.getString("name"));

                }
                //-------------------------------------
                return "True";
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
        setContentView(R.layout.activity_home);
        //mTextRes = findViewById(R.id.textView2);
        //cntx = getApplicationContext();
        lo4spc = findViewById(R.id.space4data);
        jsonReqest="5";
        //1-Creation-of-database--------------------------------------------
        OkHttpClient  client = new OkHttpClient();
        rt = new RequestTask();


        String url = "https://rickandmortyapi.com/api/character/";
        rt.execute(url);
        try {
            jsonReqest = rt.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i=0;i<al.size();i++){
            BtnGen(this,lo4spc,al.get(i));
        }

    }

    protected void BtnGen(Context c, LinearLayout inll,String tv1){

        LinearLayout.LayoutParams lilw = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,5);
        LinearLayout.LayoutParams lil = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
        LinearLayout ll = new LinearLayout(c);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.       setLayoutParams(lil);
        inll.addView(ll);


        final TextView tvID1       =   new TextView(c);
        tvID1.       setLayoutParams(lil);
        tvID1.setGravity(Gravity.CENTER);
        ll.         addView(tvID1     );

        final TextView tvID       =   new TextView(c);
        tvID.       setLayoutParams(lil);
        tvID.       setText(tv1       );
        tvID.setId(BUTTONID+countID);

        tvID.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // вызов окна с данными
                // tvID.setText("-0-");
            }
        });
        tvID.setGravity(Gravity.CENTER);
        ll.         addView(tvID     );

        final TextView tvID2       =   new TextView(c);
        tvID2.       setLayoutParams(lil);
        tvID2.setGravity(Gravity.CENTER);
        ll.         addView(tvID2     );



    }

    protected void ButtnGen(Context c, LinearLayout inll,String tv1,String tv2,String tv3,String tv4){

        LinearLayout.LayoutParams lil = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);

        LinearLayout ll = new LinearLayout(c);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.       setLayoutParams(lil);
        inll.addView(ll);


        final Button prof         =   new Button  (c);
        prof.       setLayoutParams(lil);
        prof.       setText("PIC"  );
        prof.setGravity(Gravity.CENTER);
        prof.setId(BUTTONID+countID);
        prof.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // вызов окна с данными
                prof.setText("-0-");
            }
        });
        ll.addView(prof     );


        TextView tvID       =   new TextView(c);
        tvID.       setLayoutParams(lil);
        tvID.       setText(tv1       );
        tvID.setGravity(Gravity.CENTER);
        ll.         addView(tvID     );

        TextView tvName     =   new TextView(c);
        tvName.     setLayoutParams(lil);
        tvName.     setText(tv2   );
        tvName.setGravity(Gravity.CENTER);
        ll.addView(tvName   );

        TextView tvGender   =   new TextView(c);
        tvGender.   setLayoutParams(lil);
        tvGender.   setText(tv3    );
        tvGender.setGravity(Gravity.CENTER);
        ll.addView(tvGender );

        TextView tvStatus   =   new TextView(c);
        tvStatus.   setLayoutParams(lil);
        tvStatus.   setText(tv4    );
        tvStatus.setGravity(Gravity.CENTER);
        ll.addView(tvStatus );

    }
}
/*



                 URL url  = urls[0];

                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setRequestMethod("POST");
                con.setRequestProperty("Accept-Language","en-US,en,q=0.5");

                String urlParameters = urls[1]+"="+urls[2];

                DataOutputStream wr = new DataOutputStream(con.getOutputStream());

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));

                writer.write(urlParameters);

                writer.close();

                wr.close();

                int responseCome = con.getResponseCode();

                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String inputLine;

                StringBuffer response = new StringBuffer();

                while (( inputLine = reader.readLine())!= null){

                    response.append(inputLine);
                }
                reader.close();

                respon = response.toString();




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

                        final String resp = response.body().string();

                        HomeActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvID.       setText(resp);


                            }
                        });
                    }
                }
            });

        String jsonReqest = (String) tvID.getText();
        //tvID.       setText(jsonReqest);
        try {

            jsonObject = new JSONObject(jsonReqest);
            if (jsonObject == null){
                tvID.setText(")_)_)_)_)_)");

            }
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            JSONObject obj1 = jsonArray.getJSONObject(0);
            tvID.       setText(obj1.getString("name"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //BtnGen(this,lo4spc,jsonReqest);


try {
    jsonObject = new JSONObject(resp);
    al.add(resp);
} catch (JSONException e) {
    e.printStackTrace();
}

//DBDriver dbDriver;

//do {
            //   url = jsonReqest;
        //}
        //while (jsonReqest !="");

        for (int i=0;i<al.size();i++){
            BtnGen(cntx,lo4spc,al.get(i));
        }

JSONArray arr4cond = jsonObject.getJSONArray("info");
                                    JSONObject obj4cond = arr4cond.getJSONObject(0);
                                    //emount = Integer.parseInt(obj4cond.getString("count"));
                                    obj4cond = arr4cond.getJSONObject(2);
                                    jsonReqest = obj4cond.getString("next");

                                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                                    for (int i=0;i<20;i++){
                                        JSONObject obj1 = jsonArray.getJSONObject(i);
                                        al.add(obj1.getString("name"));

                                    }


//jsonReqest = response.body().string();
//dbDriver = new DBDriver(this);
        //final SQLiteDatabase db = dbDriver.getWritableDatabase();
        //final ContentValues cv = new ContentValues();

//jsonReqest = "Name: " + obj1.getString("name") + "\nStatus: " + obj1.getString("status");

                                    //mTextRes.setText(jsonReqest);
//cv.put(dbDriver.KEY_NAME,obj1.getString("name"));
                                        //cv.put(dbDriver.KEY_GENDER,obj1.getString("gender"));
                                        //cv.put(dbDriver.KEY_STATUS,obj1.getString("status"));

                                        //db.insert(dbDriver.TABLE_NAME,null,cv);

                                        //ButtnGen(cntx,lo4spc,obj1.getString("id"),obj1.getString("name"),
                                        //        obj1.getString("gender"),obj1.getString("status"));


Cursor cursor = db.query(DBDriver.TABLE_NAME,null,null,null,null,null,null);

        if (cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(DBDriver.KEY_ID);
            int nameindex   = cursor.getColumnIndex(   DBDriver.KEY_NAME );
            int genderindex = cursor.getColumnIndex(   DBDriver.KEY_GENDER);
            int statusindex = cursor.getColumnIndex(   DBDriver.KEY_STATUS);

        }

//1-end-------------------------------------------------------------
//2-creation-of-view------------------------------------------------

//2-end------------------------------------------------------------


 */