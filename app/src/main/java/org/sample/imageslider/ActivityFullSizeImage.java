package org.sample.imageslider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

/**
 * Created by alanbix on 5/04/18.
 */

public class ActivityFullSizeImage extends Activity
{
    String[] images = null;
    int position;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full_size);

        if(savedInstanceState == null)
        {
            Intent intent = getIntent();
            images = intent.getStringArrayExtra("IMAGES");
            position = intent.getIntExtra("POSITION",0);
        }

        ViewPager viewPager = findViewById(R.id.viewPager);

        FullSizeImageAdapter imageAdapter = new FullSizeImageAdapter(this, images);
        viewPager.setAdapter(imageAdapter);
        viewPager.setCurrentItem(position, true);
    }
}
