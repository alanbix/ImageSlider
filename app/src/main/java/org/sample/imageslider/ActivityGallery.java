package org.sample.imageslider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Random;

public class ActivityGallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        RecyclerView recyclerView = findViewById(R.id.rv_images);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        Random random = new Random();

        final String[] images = new String[10];
        for(int i = 0; i < images.length; i++)
            images[i] = "https://picsum.photos/600?image=" + random.nextInt(1000 + 1);

        final IRecyclerViewClickListener listener = new IRecyclerViewClickListener()
        {
            @Override
            public void onClick(View view, int position)
            {
                Intent intent = new Intent(getApplicationContext(), ActivityFullSizeImage.class);
                intent.putExtra("IMAGES", images);
                intent.putExtra("POSITION", position);
                getApplicationContext().startActivity(intent);
            }
        };

        GalleryImageAdapter adapter = new GalleryImageAdapter(this, images, listener);
        recyclerView.setAdapter(adapter);
    }
}
