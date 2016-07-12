package com.muneikh.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.muneikh.SegmentedProgressBar;

public class ProgressActivity extends AppCompatActivity {

    private SegmentedProgressBar segmentedProgressBar, segmentedProgressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        segmentedProgressBar = (SegmentedProgressBar) findViewById(R.id.progress_bar);
        segmentedProgressBar1 = (SegmentedProgressBar) findViewById(R.id.progress_bar1);

        setupSegmentedProgressBar(segmentedProgressBar);
        setupSegmentedProgressBar(segmentedProgressBar1);
    }

    private void setupSegmentedProgressBar(SegmentedProgressBar segmentedProgressBar) {
        segmentedProgressBar.enableAutoProgressView(30000);
        segmentedProgressBar.setDividerColor(Color.BLACK);
        segmentedProgressBar.setDividerEnabled(true);
        segmentedProgressBar.setDividerWidth(10);
        segmentedProgressBar.setCornerRadius(getResources().getDimension(R.dimen.progress_bar_corner_radius));
        segmentedProgressBar.setShader(new int[]{Color.CYAN, Color.GREEN, Color.YELLOW});
    }

    @Override
    protected void onResume() {
        super.onResume();
        segmentedProgressBar.resume();
        segmentedProgressBar1.resume();
    }

    public void addDivider(View view) {
        segmentedProgressBar.addDivider();
        segmentedProgressBar1.addDivider();
    }

    public void pause(View view) {
        segmentedProgressBar.pause();
        segmentedProgressBar1.pause();
    }

    public void resume(View view) {
        segmentedProgressBar.resume();
        segmentedProgressBar1.resume();
    }

    public void reset(View view) {
        segmentedProgressBar.reset();
        segmentedProgressBar1.reset();
    }

}
