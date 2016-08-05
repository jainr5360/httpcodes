package me.kaelaela.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsTelephoneActivity extends AppCompatActivity {


    private TextView name_value ;
    private TextView mobile_no_value ;
    private TextView landline_no_value ;
    private TextView email_id_value ;
    private TextView mandir_name_value ;
    private TextView god_name_value ;
    private TextView mandir_address_value ;
    private TextView city_value ;
    private TextView state_value ;
    private ImageView image ;
    private  String profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_telephone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        name_value = (TextView)findViewById(R.id.name_value) ;
        mobile_no_value = (TextView)findViewById(R.id.mobile_no_value);
        landline_no_value = (TextView)findViewById(R.id.landline_no_value);
        email_id_value = (TextView)findViewById(R.id.email_id_value) ;
        mandir_name_value = (TextView) findViewById(R.id.mandir_name_value);
        god_name_value = (TextView) findViewById(R.id.god_name_value);
        mandir_address_value = (TextView)findViewById(R.id.mandir_address_value);
        city_value = (TextView)findViewById(R.id.city_value);
        state_value = (TextView)findViewById(R.id.state_value);
        image = (ImageView)findViewById(R.id.imageViewProfile);



        Bundle bundle = getIntent().getExtras();


        name_value.setText(bundle.getString("name"));
        mobile_no_value.setText(bundle.getString("mobile"));
        landline_no_value.setText(bundle.getString("telephone"));
        email_id_value.setText(bundle.getString("emailID"));
        god_name_value.setText(bundle.getString("contactperson"));
        mandir_name_value.setText(bundle.getString("parmatma"));
        mandir_address_value.setText(bundle.getString("address"));
        city_value.setText(bundle.getString("city"));
        state_value.setText(bundle.getString("state"));


        profileImage =getIntent().getStringExtra("image");

        System.out.println("DetailsTelphoneActivity:::" + "name_value:"+getIntent().getStringExtra("name")+"  mobile_no_value"+getIntent().getStringExtra("mobile")+
                "  landline_no_value"+getIntent().getStringExtra("mobile")+"  email_id_value"+getIntent().getStringExtra("emailid")+
        "  god_name_value"+getIntent().getStringExtra("god_name")+"  mandir_address_value"+getIntent().getStringExtra("mandir_address")+
        "  city_value"+getIntent().getStringExtra("city")+"  state_value"+getIntent().getStringExtra("state"));
        try {
            Picasso.with(getApplicationContext()).load(profileImage).into(image);
        }catch (Exception e){
            e.printStackTrace();
        }




    }

}
