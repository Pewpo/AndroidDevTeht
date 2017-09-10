package com.example.pewpo.tehtvt;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class vko2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TEHTÄVÄT Kommentoi muut tehtävät ja poista haluttu tehtävä pois kommenteista testataksesi
        //-----------  Basic UI controls -----------------------*/

        //Basic UI Controls -1
        //setContentView(R.layout.activity_vko2);


        //Basic UI Controls -2
        /*setContentView(R.layout.activity_vko2_teht2);
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.Name);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new String[]{"Pasi", "Juha", "Kari", "Jouni", "Esa", "Hannu"});
        actv.setAdapter(aa);*/

        /*------------- Launch a Map with Implicit Intent ------------------*/
       // setContentView(R.layout.activity_vko2_teht3);

        /*--------------  ListView with custom layout ---------------*/

        setContentView(R.layout.activity_vko2_teht4);


        ListView listview = (ListView) findViewById(R.id.listView);
        String[] phones = new String[]{
                "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu",
                "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu"
        };
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < phones.length; ++i) {
            list.add(phones[i]);
        }

        // add data to ArrayAdapter (default Android ListView style/layout)
       // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        // add data to ArrayAdapter (own custom layout)
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.rowlayout, R.id.textView, list);

        // create custom adapter
        PhoneArrayAdapter adapter = new PhoneArrayAdapter(this, list);
        listview.setAdapter(adapter);

        // item listener
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // get list row data (now String as a phone name)
                String phone = list.get(position);
                // create an explicit intent
                Intent intent = new Intent(vko2.this,DetailActivity.class);
                // add data to intent
                intent.putExtra("phone",phone);
                // start a new activity
                startActivity(intent);
            }
        });

    }

    public void selectButtonClicked(View view) {
        // findradiogroup
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        int id = rg.getCheckedRadioButtonId();
        // find button
        RadioButton rb = (RadioButton) findViewById(id);
        // get radiobutton text
        String text = (String) rb.getText();
        // toast message to screen
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
    public void LoginButtonClicked(View view) {
        AutoCompleteTextView name = (AutoCompleteTextView) findViewById(R.id.Name);
        EditText pass = (EditText) findViewById(R.id.Password);
        String text = name.getText() + " " + pass.getText();
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
    public void showMap(View view) {
    // get lat and lng values
        EditText editText1 = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        String numberOne = editText1.getText().toString();
        String numberTwo = editText2.getText().toString();
        double lat = Double.parseDouble(numberOne);
        double lng = Double.parseDouble(numberTwo);
    // show map
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:" +lat+ "," +lng));
        startActivity(intent);
    }
}
