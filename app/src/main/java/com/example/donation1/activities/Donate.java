package com.example.donation1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.donation1.R;
import com.example.donation1.activities.Report;
import com.example.donation1.models.Donation;


public class Donate extends Base {
    private Button donateButton;
    private RadioGroup paymentMethod;
    private ProgressBar progressBar;
    private NumberPicker amountPicker;
    private int totalDonated;
    private EditText amountText;
    private TextView amountTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        donateButton = (Button) findViewById(R.id.donateButton);
//        donateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("Donate", "Really got the button donate!!");
//            }
//        });
        if (donateButton != null) {
            Log.i("Donate", "Really got the button donate");
        }
        paymentMethod = (RadioGroup) findViewById(R.id.paymentMethod);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        amountPicker = (NumberPicker) findViewById(R.id.amountPicker);
        amountText = (EditText) findViewById(R.id.paymentAmount);
        amountTotal = (TextView) findViewById(R.id.amountTotal);


        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(1000);
        progressBar.setMax(10000);
        amountTotal.setText("Total so Far: $0");

        progressBar.setProgress(app.totalDonated);
        amountTotal.setText("Total so Far: $" + app.totalDonated);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_donate, menu);
        return true; }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.menuReport:
                startActivity(new Intent(this, Report.class));
                break;
            case R.id.menReset:
                reset();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void donateButtonPressed(View view) {
        String method = paymentMethod.getCheckedRadioButtonId() == R.id.PayPal ? "PayPal" : "Direct";
        int donatedAmount = amountPicker.getValue();
        if (donatedAmount == 0)
        {
            String text = amountText.getText().toString();
            if (!text.equals(""))
            donatedAmount = Integer.parseInt(text);
        }
        if (donatedAmount > 0) {
            app.newDonation(new Donation(donatedAmount, method));
            progressBar.setProgress(app.totalDonated);
            String totalDonatedStr = "Total so Far: $" + app.totalDonated;
            amountTotal.setText(totalDonatedStr);
            }
        }

    public void reset() {
        app.dbManager.reset();
        app.totalDonated = 0;
        progressBar.setProgress(0);
        amountTotal.setText("Total so Far: $" + app.totalDonated);
    }
    }

