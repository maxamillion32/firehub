package com.tropicthunder.firehub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final Firebase ref = new Firebase("https://firehub-ahkl.firebaseio.com");

        Button btnRegister = (Button)findViewById(R.id.btnRegister);
        final EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        final EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
        final EditText txtRepeatPassword = (EditText) findViewById(R.id.txtRepeatPassword);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hide keyboard
                InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                final String email = txtEmail.getText().toString();
                final String password = txtPassword.getText().toString();
                String repeatPassword = txtRepeatPassword.getText().toString();

                final Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        // Authenticated successfully with payload authData
                        Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                        finish();
                    }
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        // Authenticated failed with error firebaseError
                        Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                };

                //Data validation
                if (email.length()!= 0 && password.length()!= 0 && repeatPassword.length()!= 0){
                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        Toast.makeText(getApplicationContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    } else if (!password.equals(repeatPassword)){
                        Toast.makeText(getApplicationContext(), "Your passwords do not match!", Toast.LENGTH_SHORT).show();
                    } else {
                        //Valid inputs, try to register
                        ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                            @Override
                            public void onSuccess(Map<String, Object> result) {
                                Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_SHORT).show();
                                ref.authWithPassword(email, password, authResultHandler);
                            }
                            @Override
                            public void onError(FirebaseError firebaseError) {
                                // there was an error
                                Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                System.out.println("Error" + firebaseError.getMessage());
                            }
                        });
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please ensure all fields are filled up!", Toast.LENGTH_SHORT).show();
                }
            }




        });



    }


}
