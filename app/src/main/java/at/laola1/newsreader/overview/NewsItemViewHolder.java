package at.laola1.newsreader.overview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import at.laola1.newsreader.R;

public class NewsItemViewHolder extends RecyclerView.ViewHolder {

    private final ImageView imageView;
    private final TextView textView;

    public NewsItemViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.newsImage);
        textView = itemView.findViewById(R.id.newsTitle);
    }

    public void bind(NewsItemViewModel newsItemViewModel) {
        textView.setText(newsItemViewModel.getTitle());
        Picasso.with(itemView.getContext())
                .load(newsItemViewModel.getImageUrl())
                .into(imageView);
    }

}
