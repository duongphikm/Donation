package com.example.donation1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.donation1.R;

import com.example.donation1.models.Donation;

public class Report extends Base implements AdapterView.OnItemClickListener {
    ListView listView;
    DonationAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        listView = (ListView) findViewById(R.id.reportList);
        adapter = new DonationAdapter(this,  app.dbManager.getAll());
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_donate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuDonate:startActivity(new Intent(this, Donate.class));
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "You Selected Row [ " + position + "]\n" +
                "For Donation Data [ " + adapter.donations.get(position) + "]\n " +
                "With ID of [" + id + "]", Toast.LENGTH_LONG).show();

    }
}