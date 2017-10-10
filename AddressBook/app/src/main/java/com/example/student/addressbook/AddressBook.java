package com.example.student.addressbook;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class AddressBook extends AppCompatActivity {
    //used as a key in a key-value pair that's passed between activities
    public static final String ROW_ID = "row_id";

    //refer to the ListActivity's built-in ListView so we can deal with it programmatically
    private ListView contactListView;

    //adapter for populating the ListView
    private CursorAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //call super's onCreate
        setContentView(R.layout.activity_address_book);
        contactListView = (ListView) findViewById(R.id.listView); //get the ListView
        contactListView.setOnItemClickListener(viewContactListener);
        contactListView.setBackgroundColor(Color.BLACK);
        //display message on empty list
        TextView emptyText = (TextView) View.inflate(this, R.layout.contact_list_empty_item, null);
        emptyText.setVisibility(View.GONE);

        ((ViewGroup)contactListView.getParent()).addView(emptyText);
        contactListView.setEmptyView(emptyText);

        //map each contact's name to a TextView in the ListView layout
        String[] from = new String[] {"name"};
        int[] to = new int[] {R.id.contactTextView};
        contactAdapter = new SimpleCursorAdapter(AddressBook.this, R.layout.contact_list_item, null, from, to, 0);
        contactListView.setAdapter(contactAdapter); //set contactView's adapter to bind the ListView to the CursorAdapter so the ListView can display the data.
    }

    @Override
    //called each time an Activity returns to the foreground including when it is first created
    protected void onResume() {
        super.onResume(); //call super's onResume method

        //create new GetContactsTask and execute it
        //this is an AsyncTask that gets the complete list of contacts from the db and sets the contactAdapter's Cursor for populating the ListView.
        //AsyncTask method: execute performs the task in a separate thread
        //Every time this happens, a task is created because an AsyncTask can only be executed once
        new GetContactsTask().execute((Object[]) null);
    } //end method onResume

    @Override
    //called when activity is no longer visible to user
    protected void onStop(){
        Cursor cursor = contactAdapter.getCursor(); //get current Cursor
        if(cursor != null){
            cursor.close(); //deactivate it
            contactAdapter.changeCursor(null); //adapter now has no Cursor
            super.onStop();
        }
    } //end method onStop

    //performs database query outside GUI thread
    private class GetContactsTask extends AsyncTask<Object, Object, Cursor>{
        DatabaseConnector databaseConnector = new DatabaseConnector(AddressBook.this);

        //perform the database access
        @Override
        protected Cursor doInBackground(Object... params){
            databaseConnector.open();

            //get a cursor containing call contacts
            return databaseConnector.getAllContacts();
        } //end method doInBackground

        //use the Cursor returned from the doInBackground method
        @Override
        protected void onPostExecute(Cursor result){
            contactAdapter.changeCursor(result); //set the adapter's Cursor
            databaseConnector.close();
        } //end method onPostExecute
    } //end method GetContactsTask

    //create the Activity's menu_address_book from a menu_address_book resource XML file
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_address_book, menu);
        return true;
    } //end method onCreateOptionsMenu

    //handle choice from options menu_address_book
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //create a new Intent to launch the AddEditContact Activity
        Intent addNewContact = new Intent(AddressBook.this, AddEditContact.class);
        startActivity(addNewContact); //start the AddEditContact Activity
        return super.onOptionsItemSelected(item); //call super's method
    } //end method onOptionsItemSelected

    //event listener that responds to the user touching a contact's name in the ListView
    OnItemClickListener viewContactListener = new OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //create an Intent to launch the ViewContact Activity
            Intent viewContact = new Intent(AddressBook.this, ViewContact.class);

            //pass the selected contact's row ID as an extra with the Intent
            viewContact.putExtra(ROW_ID, id);
            startActivity(viewContact); //start the ViewContact Activity
        } //end method onItemClick
    }; //end viewContactListener
}
