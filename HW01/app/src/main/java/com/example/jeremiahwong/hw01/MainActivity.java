package com.example.jeremiahwong.hw01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Restore the state of the TextView
        TextView txtInfo = (TextView) findViewById(R.id.txtInformation);
        if((savedInstanceState != null) && savedInstanceState.get("tvInfo") != null){
            txtInfo.setText(savedInstanceState.get("tvInfo").toString());
        }

        //Load the edittext field with a lucky number
        Random rn = new Random();
        EditText et = (EditText) findViewById(R.id.editText);
        et.setText("Today's Lucky Number is " + Integer.toString(rn.nextInt(51)));
    }

    //TODO Get today's date
    public void getCurrentDate (View view) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormatted = new SimpleDateFormat(" MM / dd / yyyy");
        String strDate = "Date: " + dateFormatted.format(calendar.getTime());
        display(strDate);
    }
    //TODO Get the current Time
    public void getCurrentTime (View view) {
        //HINT: use the Calendar class and format the instance for time ("hh:mm a")
        // Then call the display function to display the information to the text view
        Calendar time = Calendar.getInstance();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
        String strTime = "Time: " + dateFormatter.format(time.getTime());
        display(strTime);
    }
    //TODO Get the current Temperature
    public void getCurrentTemp (View view){
        //HINT: for now, just set the temp string to a reasonable value
        // (We will get the actual temperature using an API later )
        // Then call the display function to display the information to the text view
        String strTemp = "Current Temp: 75";
        display(strTemp);
    }

    //TODO Display information to the TextView
    public void display(String str) {
        TextView info = (TextView) findViewById(R.id.txtInformation);
        info.setText("\nToday's Information \n\n " + str);
    }


    //********************** Housekeeping Functions **********************
    //TODO Save the state of the TextView
    @Override
    protected void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
        TextView txtInfo = (TextView) findViewById(R.id.txtInformation);
        state.putString("tvInfo", txtInfo.getText().toString());
    }
}
