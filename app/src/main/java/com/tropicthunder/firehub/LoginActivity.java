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

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);

        final Firebase ref = new Firebase("https://firehub-ahkl.firebaseio.com");

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        TextView btnRegister = (TextView) findViewById(R.id.btnRegister);
        final EditText txtUsername = (EditText) findViewById(R.id.txtEmail);
        final EditText txtPassword = (EditText) findViewById(R.id.txtPassword);

        final Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                // Authenticated successfully with payload authData
                Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // Authenticated failed with error firebaseError
                Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hide keyboard
                InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                //Data validation
                if (txtUsername.getText().toString().length()!=0 && txtPassword.getText().toString().length()!=0) {
                    ref.authWithPassword(txtUsername.getText().toString(), txtPassword.getText().toString(), authResultHandler);
                } else {
                    Toast.makeText(getApplicationContext(), "Complete all fields!", Toast.LENGTH_SHORT).show();
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
