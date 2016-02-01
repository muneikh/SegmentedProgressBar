package com.muneikh.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.muneikh.SegmentedProgressBar;

public class MainActivity extends AppCompatActivity {

    private SegmentedProgressBar segmentedProgressBarAuto, segmentedProgressBarManual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        segmentedProgressBarAuto = (SegmentedProgressBar) findViewById(R.id.spbAuto);
        segmentedProgressBarManual = (SegmentedProgressBar) findViewById(R.id.spbManual);

        // Auto publish progress based on time
        segmentedProgressBarAuto.enableAutoProgressView(30000);
        segmentedProgressBarAuto.setDividerColor(Color.BLACK);
        segmentedProgressBarAuto.setDividerEnabled(true);
        segmentedProgressBarAuto.setDividerWidth(5);
        segmentedProgressBarAuto.setShader(new int[]{Color.CYAN, Color.GREEN, Color.YELLOW});

        // Manually publish progress for the progress bar
        segmentedProgressBarManual.setProgressColor(Color.RED);
        segmentedProgressBarManual.publishProgress(0.5f);
    }

    @Override
    protected void onResume() {
        super.onResume();
        segmentedProgressBarAuto.resume();
    }

    public void pause(View view) {
        segmentedProgressBarAuto.pause();
    }

    public void resume(View view) {
        segmentedProgressBarAuto.resume();
    }

    public void reset(View view) {
        segmentedProgressBarAuto.reset();
    }

    public void addDivider(View view) {
        segmentedProgressBarAuto.addDivider();
    }

}
