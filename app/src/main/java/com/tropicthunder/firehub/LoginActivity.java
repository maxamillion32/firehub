package com.tropicthunder.firehub;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        session = new SessionManager(this);

        if (session.isLoggedIn()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);

        final Firebase ref = new Firebase("https://firehub-ahkl.firebaseio.com");

        final CircularProgressButton btnLogin = (CircularProgressButton)findViewById(R.id.btnLogin);
        TextView btnRegister = (TextView) findViewById(R.id.btnRegister);
        final EditText txtUsername = (EditText) findViewById(R.id.txtEmail);
        final EditText txtPassword = (EditText) findViewById(R.id.txtPassword);

        final Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                // Authenticated successfully with payload authData
                btnLogin.setProgress(99);
                //Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_SHORT).show();

                session.createLoginSession(authData.getToken(), authData.getUid());
                Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // Authenticated failed with error firebaseError
                Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                btnLogin.setProgress(0);
            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set button to show progress
                btnLogin.setProgress(1);

                //Hide keyboard
                InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                //Data validation
                if (txtUsername.getText().toString().length()!=0 && txtPassword.getText().toString().length()!=0) {
                    ref.authWithPassword(txtUsername.getText().toString(), txtPassword.getText().toString(), authResultHandler);
                } else {
                    Toast.makeText(getApplicationContext(), "Complete all fields!", Toast.LENGTH_SHORT).show();
                    //set button to show progress
                    btnLogin.setProgress(0);
                }

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
