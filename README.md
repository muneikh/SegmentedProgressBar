Segmented Progress Bar
============

A simple segmented progress bar that allows to add dividers to display chunked loading.

[Imgur](http://i.imgur.com/igrDGHb.gif?1)


```java

class ExampleActivity extends Activity {

private SegmentedProgressBar segmentedProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        segmentedProgressBar = (SegmentedProgressBar) findViewById(R.id.segmentedProgressBar);

        // Allows to trigger a smooth progress transition of progress bar in the given time.
        segmentedProgressBar.enableAutoProgressView(30000);
        
        // Allows to add a gradient fill for the progress bar 
        segmentedProgressBar.setShader(new int[]{getResources().getColor(R.color.blue), getResources().getColor(R.color.green), getResources().getColor(R.color.yellow)});
        
        // OR
        
        // Set the progress bar color 
        segmentedProgressBar.setProgressColor(Color.RED);
        
        // Set the divider colors
        segmentedProgressBar.setDividerColor(Color.BLACK);
        
        // Enable divider functionality
        segmentedProgressBar.setDividerEnabled(true);
        
        // Set the width of the divider
        segmentedProgressBar.setDividerWidth(2);
        
        // Resume and Pause the segmneted progress bar 
        segmentedProgressBar.resume();
        segmentedProgressBar.pause();
    }
}

<com.muneikh.SegmentedProgressBar
        android:id="@+id/segmentedProgressBar"
        android:layout_width="match_parent"
        android:layout_height="10dp" />
        
        ![alt tag](http://i.stack.imgur.com/nZwWU.png)
        
```

License
-------

    Copyright 2016 Muneeb Sheikh

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



 [1]: http://square.github.com/dagger/
 [2]: https://search.maven.org/remote_content?g=com.jakewharton&a=butterknife&v=LATEST
 [3]: http://jakewharton.github.com/butterknife/
 [snap]: https://oss.sonatype.org/content/repositories/snapshots/
