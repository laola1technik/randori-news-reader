package at.laola1.newsreader.overview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import at.laola1.newsreader.R;

public class NewsOverviewAdapter extends RecyclerView.Adapter<NewsItemViewHolder> {

    private List<NewsItemViewModel> newsItems = Collections.emptyList();

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup root, int viewType) {
        Context context = root.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_news, root, false);
        return new NewsItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        holder.bind(this.newsItems.get(position));
    }

    @Override
    public int getItemCount() {
        return this.newsItems.size();
    }

    void setNewsItems(List<NewsItemViewModel> newsItems) {
        this.newsItems = newsItems;
        notifyDataSetChanged();
    }
}
