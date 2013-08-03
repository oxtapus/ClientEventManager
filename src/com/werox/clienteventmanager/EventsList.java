package com.werox.clienteventmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EventsList extends Activity {
	
    public final static String EXTRA_MESSAGE = "com.werox.clienteventmanager.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.events_list, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
        	case R.id.action_new_event:
	            addNewEvent();
	            return true;
            case R.id.action_search_events:
                openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

	private void addNewEvent() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, AddEvent.class);
	}

	private void openSearch() {
		// TODO Auto-generated method stub
		
	}
    
    private void openSettings() {
		// TODO Auto-generated method stub
		
	}

	/** Called when the user clicks the Send button */
    public void sendMessage(View view) {
    	Intent intent = new Intent(this, AddEvent.class);
    	
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	
        startActivity(intent);
    }
}
