package com.tropicthunder.firehub;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ClassDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);

        TextView teacherName = (TextView)findViewById(R.id.txt_teacherName);
        TextView description = (TextView)findViewById(R.id.txt_Description);
        TextView rating = (TextView)findViewById(R.id.txt_rating);
        TextView venue = (TextView)findViewById(R.id.txt_Venue);
        TextView time = (TextView)findViewById(R.id.txt_Time);
        TextView date = (TextView)findViewById(R.id.txt_Date);
        Button backBtn = (Button)findViewById(R.id.btn_Back);
        ImageView coursePicture = (ImageView) findViewById(R.id.img_classPicture);
        ImageView teacherPicture = (ImageView) findViewById(R.id.img_teacherPicture);

        Button btnJoinClass = (Button) findViewById(R.id.btn_joinClass);

        btnJoinClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(v.getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Please make sure that you want to attend this class.")
                        .setCancelText("Cancel")
                        .setConfirmText("Confirm")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                //do what if press confirm -> do API call?
                            }
                        })
                        .show();
            }
        });

        Intent intent = getIntent();
        if(intent.getStringArrayExtra("participants") != null) {
            for (int i = 0; i < intent.getStringArrayExtra("participants").length; i++) {
                System.out.println(intent.getStringArrayExtra("participants")[i]);
            }
        }

        teacherName.setText(intent.getStringExtra("name"));
        rating.setText(intent.getStringExtra("rating"));
        description.setText(intent.getStringExtra("description"));
        venue.setText(intent.getStringExtra("venue"));
        time.setText(intent.getStringExtra("time"));
        date.setText(intent.getStringExtra("date"));

        Picasso.with(getApplicationContext()).load(intent.getStringExtra("coursePicture"))
                .centerCrop()
                .fit()
                .into(coursePicture);

        Picasso.with(getApplicationContext()).load(intent.getStringExtra("teacherPicture"))
                .centerCrop()
                .fit()
                .into(teacherPicture);

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){
                finish();
            }
        });
    }
}
