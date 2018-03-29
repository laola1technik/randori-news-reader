package at.laola1.newsreader.overview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.laola1.newsreader.R;

public class NewsOverviewAdapter extends RecyclerView.Adapter<NewsItemViewHolder>
{
    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_news, parent, false);
        return new NewsItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        NewsItemViewModel itemModel = new NewsItemViewModel();
        itemModel.setTitle("title");
        // Todo: Set ImageUrl
        holder.bind(itemModel);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
