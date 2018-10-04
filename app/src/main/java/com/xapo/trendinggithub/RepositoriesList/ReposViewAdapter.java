package com.xapo.trendinggithub.RepositoriesList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xapo.trendinggithub.R;
import com.xapo.trendinggithub.data.model.Repository;

import java.util.List;



public class RepositoriesViewAdapter extends RecyclerView.Adapter<RepositoriesViewAdapter.ItemHolder> {

    public interface ItemClickListener {
        void onItemClick(Repository repository);
    }

    private ItemClickListener listener;
    private List<Repository> repositoryList;

    public RepositoriesViewAdapter(ItemClickListener listener, List<Repository> repositoryList) {
        this.listener = listener;
        this.repositoryList = repositoryList;
        notifyDataSetChanged();
    }

    public void addRepos(List<Repository> repositoryList) {
        this.repositoryList.addAll(repositoryList);
        notifyDataSetChanged();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(repositoryList.get(holder.getAdapterPosition()));
                }
            }
        });
        String name = repositoryList.get(position).getName();
        String author = repositoryList.get(position).getAuthor();
        String stars = String.valueOf(repositoryList.get(position).getCurrentPeriodStars());
        holder.author.setText(author);
        holder.name.setText(name);
        holder.stars.setText(stars);

    }

    @Override
    public int getItemCount() {
        return repositoryList != null ? repositoryList.size() : 0;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView author;
        private TextView name;
        private TextView stars;

        public ItemHolder(View view) {
            super(view);
            this.view = view;
            this.name = (TextView) view.findViewById(R.id.textViewName);
            this.author = (TextView) view.findViewById(R.id.textViewAuthor);
            this.stars = (TextView) view.findViewById(R.id.textViewStars);
        }
    }
}
