package com.udacityandroid.justjava;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int numberOfCoffee=0;
    public void submitOrder(View view) {

        if(numberOfCoffee==0)
        {
            Context context = getApplicationContext();
            CharSequence text = "Please add coffee,thanks!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            EditText edit = (EditText) findViewById(R.id.name);
            String subject = "Just Java order summary for " + edit.getText().toString();
            CheckBox ChoclateCheckbox = (CheckBox) findViewById(R.id.chocalate);
            CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whippedCream);
            String message = createOrderSummary(numberOfCoffee, whippedCreamCheckbox.isChecked(), ChoclateCheckbox.isChecked());
            Intent intent = new Intent(Intent.ACTION_SENDTO);


            intent.setData(Uri.parse("mailto:"));

            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }

    public void increment(View view){
        displayQuantity(++numberOfCoffee);

    }

    public void decrement(View view){
        if(numberOfCoffee>0)
            --numberOfCoffee;
        displayQuantity(numberOfCoffee);

    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.value);

        quantityTextView.setText("" +number );
    }

    private String createOrderSummary(int number,boolean whippedCream,boolean chocalate) {
           int finalPrize=number*5;
           if(whippedCream)
               finalPrize=finalPrize+2;
           if(chocalate)
               finalPrize=finalPrize+3;
        EditText edit = (EditText)findViewById(R.id.name);

        String priceMessage = "Name:"+edit.getText().toString();
        priceMessage += "\nQuantity: " + number;
        if(whippedCream)
            priceMessage +="\nwhippedCream added";
        if(chocalate)
            priceMessage +="\nChoclate added";
        priceMessage += "\nTotal: $" + finalPrize;
        priceMessage += "\nThank you!";
        return priceMessage;
            }


}
