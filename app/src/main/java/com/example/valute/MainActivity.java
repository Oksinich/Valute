package com.example.valute;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends Activity implements ValuteAdapter.OnValuteListener{
    RecyclerView rec;
    ValuteAdapter adapter;
    DataTask task=null;
    List<OneValute> oneValutes = new ArrayList<>();;
    FloatingActionButton fl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rec = findViewById(R.id.rec);
        fl = findViewById(R.id.floatingActionButton);

        task = new DataTask();
        task.execute();

        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.cancel(true);
                task.cancel(false);
                Toast.makeText(getApplicationContext(),"Данные обновлены",Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onValuteClick(int position) {
        Intent intent = new Intent(this,Convert.class);
        intent.putExtra("name",oneValutes.get(position).getName());
        intent.putExtra("value",oneValutes.get(position).getValue());
        startActivity(intent);
    }

    class DataTask extends AsyncTask<Void,Void,Void>{


        @Override
        protected Void doInBackground(Void... voids) {

              if (isCancelled())
                  return null;
                  try {
                    JSONObject json = readJsonFromUrl("https://www.cbr-xml-daily.ru/daily_json.js");
                    getKeysJSOB(json.toString(),"Valute");

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setAdapter();

        }
    }

    private void setAdapter() {
        rec.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ValuteAdapter(oneValutes,this);
        rec.setAdapter(adapter);
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private String[] getKeysJSOB(String body, String keyJS) {
        JSONObject first = null;

        String name;
        float value;
        try {
            first = new JSONObject(body);
            JSONObject JO = first.getJSONObject(keyJS);
            Iterator keysToCopyIterator = JO.keys();
            List<String> keysList = new ArrayList<String>();
            while (keysToCopyIterator.hasNext()) {
                String key = (String) keysToCopyIterator.next();
                keysList.add(key);
                JSONObject jo = first.getJSONObject("Valute").getJSONObject(key);
                name = jo.get("Name").toString();
                Float v = Float.parseFloat(jo.get("Value").toString());
                DecimalFormat format = new DecimalFormat("##.00");
                v = Float.parseFloat(format.format(v));
                OneValute one = new OneValute(name,v);
                oneValutes.add(one);
            }

            String[] keysArray = keysList.toArray(new String[keysList.size()]);


            return keysArray;
        } catch (JSONException e) {
            Log.e("Error", "Error " + e.toString());
            e.printStackTrace();
            return null;
        }
    }
}