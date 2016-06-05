package com.tropicthunder.firehub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ParticipantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants);

        // Array of strings...
        String[] participantNamesArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};

        //list view adapter
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listview_basic, participantNamesArray);

        //mount adapter to listview
        ListView listView = (ListView) findViewById(R.id.listView_participants);
        listView.setAdapter(adapter);

        //back button
        Button btnBack = (Button) findViewById(R.id.btn_Back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
