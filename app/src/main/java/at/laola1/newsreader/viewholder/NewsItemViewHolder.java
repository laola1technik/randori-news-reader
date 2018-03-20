package at.laola1.newsreader.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import at.laola1.newsreader.R;
import at.laola1.newsreader.viewmodel.NewsItemViewModel;

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
        // Todo: Set Image.
    }

}