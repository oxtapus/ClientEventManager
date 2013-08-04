package com.werox.clienteventmanager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class AddEvent extends Activity {

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    Spinner startDateSpinner = (Spinner) findViewById(R.id.edit_start_date);
	    startDateSpinner.setOnTouchListener(spinnerDateOnTouch);
	    
	    Spinner startTimeSpinner = (Spinner) findViewById(R.id.edit_start_time);
	    startTimeSpinner.setOnTouchListener(spinnerTimeOnTouch);
	    
	    setContentView(R.layout.activity_add_event);
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private View.OnTouchListener spinnerDateOnTouch = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
            	showDatePickerDialog(v);
            }
            return false;
        }
    };
    
    private View.OnTouchListener spinnerTimeOnTouch = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
            	showTimePickerDialog(v);
            }
            return false;
        }
    };
        
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
    
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }
}
