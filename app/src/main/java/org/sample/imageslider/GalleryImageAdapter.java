package org.sample.imageslider;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class GalleryImageAdapter extends RecyclerView.Adapter<GalleryImageAdapter.ImageViewHolder>
{
    private String[] urlList;
    private Context context;
    private IRecyclerViewClickListener clickListener;

    GalleryImageAdapter(Context context, String[] urlList, IRecyclerViewClickListener clickListener)
    {
        this.urlList = urlList;
        this.context = context;
        this.clickListener = clickListener;

    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView iv_image;
        ProgressBar pb_progressBar;

        ImageViewHolder(View v, IRecyclerViewClickListener clickListener)
        {
            super(v);
            iv_image = v.findViewById(R.id.iv_photo);
            pb_progressBar = v.findViewById(R.id.pb_progress_bar);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            clickListener.onClick(view, getAdapterPosition());
        }
    }//end of PedidosViewHolder class

    @Override
    public int getItemCount() { return urlList.length; }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.gallery_image_item, viewGroup, false);
        return new ImageViewHolder(v, clickListener);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder viewHolder, int i)
    {
        String currentImage = urlList[i];
        ImageView imageView = viewHolder.iv_image;
        final ProgressBar progressBar = viewHolder.pb_progressBar;

        Glide.with(context)
                .load(currentImage)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imageView);

    }
}
