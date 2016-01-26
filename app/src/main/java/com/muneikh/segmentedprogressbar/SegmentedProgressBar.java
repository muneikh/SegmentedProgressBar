/*
 * Copyright (C) 2016 Muneeb Sheikh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.muneikh.segmentedprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SegmentedProgressBar extends View {

    private static final int FPS_IN_MILLI = 17; // 16.66 ~ 60fps

    private Paint progressPaint = new Paint();
    private Paint dividerPaint = new Paint();

    private float percentCompleted;
    private int progressBarWidth;
    private long maxTimeInMillis;
    private int dividerCount = 0;
    private int dividerWidth = 2;

    private List<Float> dividerPositions;
    private CountDownTimerWithPause countDownTimerWithPause;

    public SegmentedProgressBar(Context context) {
        super(context);
        init();
    }

    public SegmentedProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SegmentedProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        progressBarWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, percentCompleted, getHeight(), progressPaint);

        if (dividerCount > 0) {
            for (int i = 0; i < dividerCount; i++) {
                float leftPosition = dividerPositions.get(i);
                canvas.drawRect(leftPosition, 0, leftPosition + dividerWidth, getHeight(), dividerPaint);
            }
        }
    }

    private void init() {
        dividerPositions = new ArrayList<>();
    }

    private void updateProgress(long millisPassed) {
        percentCompleted = progressBarWidth * (float) millisPassed / maxTimeInMillis;
        invalidate();
    }

    private void updateDivider() {
        dividerCount += 1;
        dividerPositions.add(percentCompleted);
        invalidate();
    }

    public void pause() {
        countDownTimerWithPause.pause();
        updateDivider();
    }

    public void resume() {
        countDownTimerWithPause.resume();
    }

    public void cancel() {
        countDownTimerWithPause.cancel();
    }

    /**
     * Apply the shader for the for the progress bar.
     * @param colors
     */
    public void setShader(int[] colors) {
        Shader shader = new LinearGradient(0, 0, progressBarWidth, getHeight(), colors, null, Shader.TileMode.MIRROR);
        progressPaint.setShader(shader);
    }

    /**
     * Set the color for the progress bar
     * @param color
     */
    public void setProgressColor(int color) {
        progressPaint.setColor(color);
    }

    /**
     * Set the color for the divider bar
     * @param color
     */
    public void setDividerColor(int color) {
        dividerPaint.setColor(color);
    }

    /**
     * set the width of the divider
     * @param width
     */
    public void setDividerWidth(int width) {
        if (width < 0) {
            throw new RuntimeException("Divider width can not be negative");
        }

        dividerWidth = width;
    }

    /**
     * The progress bar will be auto progressing within the given time limit towards completion
     * where the it can be paused and resumed.
     *
     * @param timeInMillis
     */
    public void setupAutoProgress(long timeInMillis) {
        if (timeInMillis < 0) {
            throw new RuntimeException("Time can not be in negative");
        }

        this.maxTimeInMillis = timeInMillis;

        countDownTimerWithPause = new CountDownTimerWithPause(maxTimeInMillis, FPS_IN_MILLI, false) {
            @Override
            public void onTick(long millisUntilFinished) {
                long timePassed = SegmentedProgressBar.this.maxTimeInMillis - millisUntilFinished;
                updateProgress(timePassed);
            }

            @Override
            public void onFinish() {
                updateProgress(SegmentedProgressBar.this.maxTimeInMillis);
            }
        }.create();
    }
}
