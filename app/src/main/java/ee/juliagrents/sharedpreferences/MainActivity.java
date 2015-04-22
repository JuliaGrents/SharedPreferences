package ee.juliagrents.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private int currentRate;
    private int highestRate;
    private TextView currentRateView;
    private TextView highestRateView;

    public static final String PREFS_NAME = "SharedPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        currentRate = storedData.getInt("currentRate", 0);
        highestRate = storedData.getInt("highestRate", 0);


        currentRateView = (TextView) findViewById(R.id.currentRateView);
        highestRateView = (TextView) findViewById(R.id.highestRateView);

        currentRateView.setText("Current Rate: " + currentRate);
        highestRateView.setText("Highest Rate: " + highestRate);


    }

    public void addCurrentRate(View view) {

        currentRate++;
        currentRateView.setText("Current Rate:" + currentRate);
        if (highestRate < currentRate)
        {
            highestRate=currentRate;
        }
        highestRateView.setText("Highest Rate: " + highestRate);
    }


    public void clearCurrentRate(View view) {
        currentRate=0;
        currentRateView.setText("Current Rate:" + currentRate);
        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor storedDataEditor  = storedData.edit();
        storedDataEditor.putInt("currentRate", currentRate);
        storedDataEditor.putInt("highestRate", highestRate);
        storedData.commit();

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor storedDataEditor  = storedData.edit();
        storedDataEditor.putInt("currentRate", currentRate);
        storedDataEditor.putInt("highestRate", highestRate);
        storedData.commit();

    }



}
