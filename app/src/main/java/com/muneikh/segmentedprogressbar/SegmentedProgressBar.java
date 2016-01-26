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
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SegmentedProgressBar extends View {

    private static final String TAG = "SegmentedProgressBar";

    private static final int TOTAL_LIMIT = 15000; // ms
    private static final int FPS_IN_MILLI = 17; // 16.66 ~ 60fps
    private static final int DIVIDER_WIDTH = 2;

    private Paint paint = new Paint();
    private Paint dividerPaint = new Paint();

    private int dividerCount = 0;
    private List<Float> dividerPositions;

    private int progressBarWidth;
    private float percentCompleted;

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

        paint.setColor(Color.RED);
        dividerPaint.setColor(Color.BLACK);

        Shader mShader = new LinearGradient(0, 0, w, h, new int[]{
                getResources().getColor(R.color.blue), getResources().getColor(R.color.green), getResources().getColor(R.color.yellow)},
                null, Shader.TileMode.REPEAT);  // CLAMP MIRROR REPEAT
        paint.setShader(mShader);
    }

    private void init() {
        dividerPositions = new ArrayList<>();
        countDownTimerWithPause = new CountDownTimerWithPause(TOTAL_LIMIT, FPS_IN_MILLI, true) {
            @Override
            public void onTick(long millisUntilFinished) {
                long timePassed = TOTAL_LIMIT - millisUntilFinished;
                updateProgress(timePassed);
            }

            @Override
            public void onFinish() {
                updateProgress(TOTAL_LIMIT);
            }
        }.create();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, percentCompleted, getHeight(), paint);

        if (dividerCount > 0) {
            for (int i = 0; i < dividerCount; i++) {
                float leftPosition = dividerPositions.get(i);
                canvas.drawRect(leftPosition, 0, leftPosition + DIVIDER_WIDTH, getHeight(), dividerPaint);
            }
        }
    }

    protected void pause() {
        countDownTimerWithPause.pause();
        updateDivider();
    }

    protected void resume() {
        countDownTimerWithPause.resume();
    }

    protected void cancel() {
        countDownTimerWithPause.cancel();
    }

    private void updateProgress(long millisPassed) {
        percentCompleted = progressBarWidth * (float) millisPassed / TOTAL_LIMIT;
        //Log.d(TAG, "updateProgress: percentageCompleted : " + percentCompleted);
        invalidate();
    }

    private void updateDivider() {
        dividerCount += 1;
        dividerPositions.add(percentCompleted);
        invalidate();
    }
}
