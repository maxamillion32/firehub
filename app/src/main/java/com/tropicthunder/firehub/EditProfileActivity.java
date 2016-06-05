package com.tropicthunder.firehub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        final SessionManager session = new SessionManager(this);

        Button btnLogout = (Button) findViewById(R.id.btn_logOut);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();

                startActivity(new Intent(EditProfileActivity.this, LoginActivity.class));
                finish();
            }
        });

        Button btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this, MainActivity.class));
                finish();
            }
        });

        Button btnUpdate = (Button) findViewById(R.id.btn_updateProfile);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
