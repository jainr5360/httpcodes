package com.example.genie7.recyclerviewdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvRecyclerview;
    ArrayList<ModelClass> mlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvRecyclerview = (RecyclerView) findViewById(R.id.rv_recyclerview);

//        CustomAdapter adapter = new CustomAdapter(this , )

        new Fetchdata().execute();

    }

    class Fetchdata extends AsyncTask<Boolean, Void, ArrayList<ModelClass>> {

        private ProgressDialog pDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

//        txView = (TextView)findViewById(R.id.txView);

            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setMessage("Loding....");
            pDialog.show();

        }

        @Override
        protected ArrayList<ModelClass> doInBackground(Boolean... params) {

            JSONObject jsonObject = new JSONObject();
            try {

                String url = getResources().getString(R.string.Fetchdata);

//                JSONObject resobj = RestJsonClient.connect(url);
                JSONObject resObj = RestJsonClient.connect(url);

                System.out.println("resobj1" + resObj);
                mlist = new ArrayList<ModelClass>();
                JSONArray arrayobject = resObj.getJSONArray("adsData");
                for (int i = 0; i <= arrayobject.length(); i++) {
                    ModelClass list = new ModelClass();

                    JSONObject jsonObject1 = arrayobject.getJSONObject(i);

                    list.setName(jsonObject1.getString("name"));
                    list.setProfile(jsonObject1.getString("largeimagepath"));


                    mlist.add(list);
                }

            } catch (JSONException e) {
                e.printStackTrace();
                String msg = "No Internet";

            }


            return mlist;
        }

        protected void onPostExecute(ArrayList<ModelClass> eventclass) {
            super.onPostExecute(eventclass);
            pDialog.dismiss();
            try {
//                rvRecyclerview.setAdapter(new CustomAdapter(MainActivity.this, eventclass));

//                rvRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerView);
                rvRecyclerview.setAdapter(new CustomAdapter(MainActivity.this,eventclass));
                rvRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rvRecyclerview.setHasFixedSize(true);





            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
