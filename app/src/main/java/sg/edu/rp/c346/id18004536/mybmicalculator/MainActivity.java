package sg.edu.rp.c346.id18004536.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etHeight;

    Button calc,reset;

    TextView currDate, calcBMI, infomation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.EditTextWeight);
        etHeight = findViewById(R.id.EditTextHeight);
        calc = findViewById(R.id.calculate);
        reset = findViewById(R.id.resetdata);
        currDate = findViewById(R.id.date);
        calcBMI = findViewById(R.id.calculatedBMI);
        infomation = findViewById(R.id.showInfo);


        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer strWeight = Integer.parseInt(etWeight.getText().toString());
                Integer strHeight = Integer.parseInt(etHeight.getText().toString());
                String DateNow = new SimpleDateFormat("yyyy-MM-dd h:mm a", Locale.getDefault()).format(new Date());
                float multiply = strHeight*strHeight;
                float calculation = (strWeight / multiply)*10000;
                String simplifiedcalc = String.format("%.2f",calculation);


                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);


                SharedPreferences.Editor prefEdit = prefs.edit();
                
                if(calculation < 18.5){
                    prefEdit.putString("Date", DateNow);
                    prefEdit.putFloat("calculation", calculation);
                    prefEdit.putString("Info", "You are underweight");


                    currDate.setText("Last Calculated Date: "+ DateNow);
                    calcBMI.setText("Last Calculated BMI: " +simplifiedcalc);
                    infomation.setText("You are underweight");


                }
                else if(calculation < 24.9){
                    prefEdit.putString("Date", DateNow);
                    prefEdit.putFloat("calculation", calculation);
                    prefEdit.putString("Info", "Your BMI is normal");
                    prefEdit.commit();


                    currDate.setText("Last Calculated Date: "+ DateNow);
                    calcBMI.setText("Last Calculated BMI: " +simplifiedcalc);
                    infomation.setText("Your BMI is normal");

                    prefEdit.commit();
                }
                else if(calculation > 25 && calculation < 30) {
                    prefEdit.putString("Date", DateNow);
                    prefEdit.putFloat("calculation", calculation);
                    prefEdit.putString("Info", "You are overweight");



                    currDate.setText("Last Calculated Date: " + DateNow);
                    calcBMI.setText("Last Calculated BMI: " + simplifiedcalc);
                    infomation.setText("You are overweight");

                    prefEdit.commit();
                }
                else if(calculation >= 30){
                prefEdit.putString("Date", DateNow);
                prefEdit.putFloat("calculation", calculation);
                prefEdit.putString("Info", "You are obese");



                currDate.setText("Last Calculated Date: "+ DateNow);
                calcBMI.setText("Last Calculated BMI: " +simplifiedcalc);
                infomation.setText("You are obese");

                    prefEdit.commit();
                }

            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                SharedPreferences.Editor prefEdit = prefs.edit();

                String defaultDate = "";
                float defaultCalc = 0;

                prefEdit.putString("Date", defaultDate);
                prefEdit.putFloat("calculation", defaultCalc);
                prefEdit.putString("Info", "");


                String shDate = prefs.getString("Date", "");
                float shCalc = prefs.getFloat("calculation", 0);
                String shInfo = prefs.getString("Info", "");

                currDate.setText("Last Calculated Date: "+defaultDate);
                calcBMI.setText("Last Calculated BMI: " +defaultCalc);
                infomation.setText("");

                prefEdit.commit();
            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String shDate = prefs.getString("Date", "");
        float shCalc = prefs.getFloat("calculation", 0);
        String shInfo = prefs.getString("Info", "");

        currDate.setText("Last Calculated Date: "+shDate);
        calcBMI.setText("Last Calculated BMI: " +shCalc);
        infomation.setText(shInfo);


    }
}

