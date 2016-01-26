package com.muneikh.segmentedprogressbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SegmentedProgressBar segmentedProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        segmentedProgressBar = (SegmentedProgressBar) findViewById(R.id.segmentedProgressBar);

        //segmentedProgressBar.setShader(new int[]{getResources().getColor(R.color.blue), getResources().getColor(R.color.green), getResources().getColor(R.color.yellow)});

        segmentedProgressBar.setProgressColor(Color.RED);
        segmentedProgressBar.setDividerColor(Color.BLACK);

        segmentedProgressBar.setDividerWidth(2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void pause(View view) {
        segmentedProgressBar.pause();
    }

    public void resume(View view) {
        segmentedProgressBar.resume();
    }
}
