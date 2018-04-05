package org.sample.imageslider;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class FullSizeImageAdapter extends PagerAdapter
{

    private Context context;
    private String[] images;
    private LayoutInflater layoutInflater;

    FullSizeImageAdapter(Context context, String[] images)
    {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() { return images.length; }

    @Override
    public boolean isViewFromObject(View view, Object object) { return view == object; }

    @Override
    public Object instantiateItem(ViewGroup container, final int position)
    {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.full_size_image_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Glide.with(context)
                .load(images[position])
                .apply(new RequestOptions()
                        .centerInside())
                .into(imageView);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}