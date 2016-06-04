package com.tropicthunder.firehub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.Firebase;

public class CreateClassActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String txtCategory;
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        txtCategory = parent.getItemAtPosition(pos).toString();
        System.out.println(parent.getItemAtPosition(pos));
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SessionManager sessionManager = new SessionManager(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        final EditText title = (EditText)findViewById(R.id.txt_title);
        final EditText description = (EditText)findViewById(R.id.txt_description);
        final EditText venue = (EditText)findViewById(R.id.txt_venue);
        final EditText time = (EditText)findViewById(R.id.txt_time);
        final EditText date = (EditText)findViewById(R.id.txt_date);
        final Spinner category = (Spinner)findViewById(R.id.spn_category);
        category.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        category.setAdapter(adapter);

        Button register = (Button)findViewById(R.id.btn_register);
        Button back = (Button)findViewById(R.id.btn_back);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (title.getText().equals("")){
                    return;
                }

                if (description.getText().equals("")){
                    return;
                }

                if (venue.getText().equals("")){
                    return;
                }

                if (time.getText().equals("")){
                    return;
                }

                if (date.getText().equals("")){
                    return;
                }

                PostDetails postDetails = new PostDetails(title.getText().toString(), txtCategory, "A Name", "0", "http://www.essaycourses.co.uk/assets/uploads/files/books%20image.jpg",
                                                          "https://www.drupal.org/files/profile_default.png", sessionManager.getUid(),
                                                          description.getText().toString(), venue.getText().toString(), time.getText().toString(),
                                                          date.getText().toString(), null);

                Firebase ref = new Firebase("https://firehub-ahkl.firebaseio.com/data/posts");
                ref.push().setValue(postDetails);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
