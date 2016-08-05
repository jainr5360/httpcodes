package me.kaelaela.sample;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import me.kaelaela.sample.Adapter.TelephoneCustomAdapter;
import me.kaelaela.sample.Model.TelephoneModel;

public class TelephoneDirectory extends AppCompatActivity {


    ListView lvTelephone;
    ArrayList<TelephoneModel> mlist;
    ImageView iv_Menutelephone;
    EditText searchengine;
    TelephoneCustomAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephone_directory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchengine = (EditText) findViewById(R.id.searchengine);

        lvTelephone = (ListView) findViewById(R.id.lv_Telephone);
        iv_Menutelephone = (ImageView) findViewById(R.id.iv_menutelephone);
        new gettelephonedirectory().execute();

        lvTelephone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TelephoneDirectory.this, "gotclicked:::" + position, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), DetailsTelephoneActivity.class);


                Bundle bundle = new Bundle();
                bundle.putString("image", mlist.get(position).getImage());
                bundle.putString("name", mlist.get(position).getName());
                bundle.putString("mobile", mlist.get(position).getPhone_no());
                bundle.putString("telephone", mlist.get(position).getLandline_no());
                bundle.putString("emailID", mlist.get(position).getEmail_id());
                bundle.putString("parmatma", mlist.get(position).getMandirname());
                bundle.putString("contactperson", mlist.get(position).getGodname());
                bundle.putString("address", mlist.get(position).getMandir_address());
                bundle.putString("city", mlist.get(position).getCity());
                bundle.putString("state", mlist.get(position).getState());
                intent.putExtras(bundle);
                startActivity(intent);

                System.out.println("DetailsTelphoneActivityInten:::" + "name_value:" + mlist.get(position).getName() + "  mobile_no_value" + mlist.get(position).getPhone_no() +
                        "  landline_no_value" + mlist.get(position).getLandline_no() + "  email_id_value" + getIntent().getStringExtra("emailid") +
                        "  god_name_value" + mlist.get(position).getMandirname() + "  mandir_address_value" + mlist.get(position).getMandir_address() +
                        "  city_value" + mlist.get(position).getCity() + "  state_value" + mlist.get(position).getState());

            }
        });

        iv_Menutelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageFragment homeFrag = new ImageFragment();
                FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
                fragTransaction.replace(R.id.content_frame, homeFrag);
                fragTransaction.addToBackStack(null);
                fragTransaction.commit();


            }
        });

        searchengine.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                try {
                    dataAdapter.filter(searchengine.getText().toString().toLowerCase(Locale.getDefault()));
                } catch (Exception e) {
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub


            }
        });

//        telephone.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
//                // When user changed the Text
//                TelephoneDirectory.this.dataAdapter.getFilter().filter(cs);
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
//                                          int arg3) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//                // TODO Auto-generated method stub
//            }
//        });
//
//



    }


    class gettelephonedirectory extends AsyncTask<Boolean, Void, ArrayList<TelephoneModel>> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(TelephoneDirectory.this);
            pd.setMessage("loading....");
            pd.show();
        }

        @Override
        protected ArrayList<TelephoneModel> doInBackground(Boolean... params) {

            JSONObject obj = new JSONObject();

            try {

                String url = getResources().getString(R.string.gettelephonedirectory);

                JSONObject resObj = RestJsonClient.connect(url);

                System.out.println("resObj 1 " + resObj);
                mlist = new ArrayList<TelephoneModel>();

                JSONArray arryObject = resObj.getJSONArray("adsData");


                for (int i = 0; i < arryObject.length(); i++)

                {

                    Log.d("ghghghg", "guyguyg");

                    TelephoneModel list = new TelephoneModel();
                    JSONObject jsonObject = arryObject.getJSONObject(i);

                    list.setID(jsonObject.getString("id"));
                    list.setName(jsonObject.getString("name"));
                    list.setPhone_no(jsonObject.getString("phone_no"));
                    list.setLandline_no(jsonObject.getString("landline_no"));
                    list.setEmail_id(jsonObject.getString("emailid"));
                    list.setMandirname(jsonObject.getString("mandir_name"));
                    list.setGodname(jsonObject.getString("god_name"));
                    list.setCity(jsonObject.getString("city"));
                    list.setImage(jsonObject.getString("image"));
                    list.setState(jsonObject.getString("state"));
                    list.setMandir_address(jsonObject.getString("mandir_address"));

                    mlist.add(list);

                }
            } catch (JSONException e) {
                e.printStackTrace();
                String msg = "No Internet";
            }
            return mlist;


        }

        @Override
        protected void onPostExecute(ArrayList<TelephoneModel> eventModels) {
            super.onPostExecute(eventModels);
            pd.dismiss();
            try {
                dataAdapter = new TelephoneCustomAdapter(getApplicationContext(), mlist);
                lvTelephone.setAdapter(dataAdapter);

                lvTelephone.setAdapter(new TelephoneCustomAdapter(getApplicationContext(), eventModels));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}


