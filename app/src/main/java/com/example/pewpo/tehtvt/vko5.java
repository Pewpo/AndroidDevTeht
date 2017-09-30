package com.example.pewpo.tehtvt;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class vko5 extends   Activity implements PriceDialogFragment.DialogListener /* TextEntryDialogFragment.TextEntryDialogListener TEHTÄVÄÄN1*/ {
    private static final String DATABASE_TABLE = "Shoplist";
    private static final int DELETE_ID =  0;
    private final String TEXTVIEW_STATEKEY ="TEXTVIEW_STATEKEY";
    SQLiteDatabase db;
    ListView listView;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoplist);

        // find list view
       listView = (ListView)  findViewById(R.id.shoplist);
        // register listView's context menu (to delete row)
        registerForContextMenu(listView);

        // get database instance
        db = (new DatabaseOpenHelper(this)).getWritableDatabase();
      //  cursor = db.rawQuery("SELECT _id, product, price, itemcount FROM shop ORDER BY price DESC", null);
        // get data with using own made queryData method
        queryData();
        Toast.makeText(getBaseContext(), "Yhteensä: " + countWholePrice(), Toast.LENGTH_SHORT).show();
    }
    private float countWholePrice()
    {
        float wholeprice = 0f;
        if (cursor.moveToFirst()) {
            do {
                float count = cursor.getFloat(3); // columnIndex
                float price = cursor.getFloat(2); // columnIndex
                wholeprice += (count*price);
            } while(cursor.moveToNext());
        }
        return wholeprice;
    }
    public void queryData() {
        cursor = db.rawQuery("SELECT _id, product, price, itemcount FROM shop ORDER BY price DESC", null);
        ListAdapter adapter = new SimpleCursorAdapter(
                this, R.layout.shopitems, cursor,
                new String[] {"product", "itemcount","price"},
                new int[] {R.id.product,R.id.count,R.id.price}
                ,0);  // flags

        // show data in listView
        listView.setAdapter(adapter);

    }
    public void buttonClicked2(View view) {
        Toast.makeText(getBaseContext(), "Buttonclickked", Toast.LENGTH_SHORT).show();
        PriceDialogFragment pDialog = new PriceDialogFragment();
        pDialog.show(getFragmentManager(), "Text Dialog");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String name, double count, double price) {
        ContentValues values=new ContentValues(3);
        values.put("product", name);
        values.put("itemcount", count);
        values.put("price", price);
        db.insert("shop", null, values);
        // get data again
        queryData();
        Toast.makeText(getBaseContext(), "Yhteensä: " + countWholePrice(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, DELETE_ID, Menu.NONE, "Delete");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case DELETE_ID:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                String[] args = {String.valueOf(info.id)};
                db.delete("shop", "_id=?", args);
                queryData();
                Toast.makeText(getBaseContext(), "Yhteensä: " + countWholePrice(), Toast.LENGTH_SHORT).show();
                return true;
        }
        Toast.makeText(getBaseContext(), "Yhteensä: " + countWholePrice(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // close cursor and db connection
        cursor.close();
        db.close();
    }
    //TEHTÄVÄ 1
/*        setContentView(R.layout.activity_vko5);

        //Comment bellow to test
        TextView textView = (TextView) findViewById(R.id.textVieww);
        if (savedInstanceState != null ){

            if (savedInstanceState.containsKey(TEXTVIEW_STATEKEY)) {

                String text = savedInstanceState.getString(TEXTVIEW_STATEKEY);

                textView.setText(text);
            }
        }
    }
    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        Toast.makeText(getBaseContext(), "onSaveInstanceState", Toast.LENGTH_SHORT).show();
        // get text view
        TextView textView = (TextView) findViewById(R.id.textVieww);
        // save text view state
        saveInstanceState.putString(TEXTVIEW_STATEKEY, textView.getText().toString());
    }
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String text) {
        Toast.makeText(getBaseContext(), "OnDialogPositiveClick", Toast.LENGTH_SHORT).show();
        TextView textView = (TextView) findViewById(R.id.textVieww);
        textView.setText(text);
    }
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText (getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
    }
    public void buttonClicked(View view) {
        Toast.makeText(getBaseContext(), "Buttonclickked", Toast.LENGTH_SHORT).show();
        TextEntryDialogFragment eDialog = new TextEntryDialogFragment();
        eDialog.show(getFragmentManager(), "Text Dialog");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/
}


