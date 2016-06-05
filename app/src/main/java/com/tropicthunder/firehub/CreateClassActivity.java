package com.tropicthunder.firehub;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.Firebase;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class CreateClassActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener {

    String txtCategory;
    Button btnTimePicker, btnDatePicker;

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
        btnTimePicker = (Button) findViewById(R.id.btn_pickTime);
        btnDatePicker = (Button) findViewById(R.id.btn_pickDate);

        // Show a timepicker when the timeButton is clicked
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        CreateClassActivity.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        false
                );
                tpd.setThemeDark(false);
                tpd.vibrate(true);
                tpd.dismissOnPause(false);
                tpd.enableSeconds(false);

                tpd.setAccentColor(Color.parseColor("#546e7a"));

                tpd.setTitle("Select class time");

                tpd.setTimeInterval(1, 30, 60);

                tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        //Log.d("TimePicker", "Dialog was cancelled");
                    }
                });
                tpd.show(getFragmentManager(), "Timepickerdialog");
            }
        });

        // Show a datepicker when the dateButton is clicked
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        CreateClassActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setThemeDark(false);
                dpd.vibrate(true);
                dpd.dismissOnPause(true);
                dpd.showYearPickerFirst(false);
                dpd.setAccentColor(Color.parseColor("#546e7a"));
                dpd.setTitle("Select class date:");

//                    Calendar[] dates = new Calendar[13];
//                    for(int i = -6; i <= 6; i++) {
//                        Calendar date = Calendar.getInstance();
//                        date.add(Calendar.MONTH, i);
//                        dates[i+6] = date;
//                    }
//                    dpd.setSelectableDays(dates);

//                if (highlightDates.isChecked()) {
//                    Calendar[] dates = new Calendar[13];
//                    for(int i = -6; i <= 6; i++) {
//                        Calendar date = Calendar.getInstance();
//                        date.add(Calendar.WEEK_OF_YEAR, i);
//                        dates[i+6] = date;
//                    }
//                    dpd.setHighlightedDays(dates);
//                }

                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

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

                if (btnTimePicker.getText().equals("")){
                    return;
                }

                if (btnDatePicker.getText().equals("")){
                    return;
                }

                PostDetails postDetails = new PostDetails(title.getText().toString(), txtCategory, "A Name", "0", "http://www.essaycourses.co.uk/assets/uploads/files/books%20image.jpg",
                                                          "https://www.drupal.org/files/profile_default.png", sessionManager.getUid(),
                                                          description.getText().toString(), venue.getText().toString(), btnTimePicker.getText().toString(),
                                                          btnDatePicker.getText().toString(), null);

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

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String secondString = second < 10 ? "0"+second : ""+second;
        String time = hourString+""+minuteString+" hrs";
        btnTimePicker.setText(time);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        btnDatePicker.setText(date);
    }


}
