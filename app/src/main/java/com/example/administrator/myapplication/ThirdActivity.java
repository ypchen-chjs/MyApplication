package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //get the data passed in using getStringExtra()
        Toast.makeText(this,getIntent().getStringExtra("str1"),Toast.LENGTH_SHORT).show();

        //get the data passed in using getIntExtra()
        Toast.makeText(this,Integer.toString(getIntent().getIntExtra("age1",0)),Toast.LENGTH_SHORT).show();

        //get the Bundle object passed in
        Bundle bundle = getIntent().getExtras();

        //get the data using the getString()
        Toast.makeText(this,bundle.getString("str2"),Toast.LENGTH_SHORT).show();

        //get the data using the getInt() method
        Toast.makeText(this,Integer.toString(bundle.getInt("age2")),Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v){
        //use an Intent object to return data
        Intent i = new Intent();
        //use the putExtra() method to return some value
        i.putExtra("age3",45);
        //use the setData() method to return some value
        i.setData(Uri.parse("Something passed back to main activity"));
        //set the result with OK and the Intent object
        setResult(RESULT_OK,i);
        //destroy the current activity
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.third, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
