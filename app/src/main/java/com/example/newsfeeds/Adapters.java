package com.example.newsfeeds;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsfeeds.NewsClasses.Articles;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapters extends RecyclerView.Adapter<Adapters.viewHolder> {

    Context context;
    List<Articles> list;

    public Adapters(Context context, List<Articles> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_news_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Articles n = list.get(position);
        holder.title.setText(n.getTitle());
        holder.name.setText(n.getSource().getName());
        holder.dateTime.setText(n.getPublishedAt());

        String imageUri = n.getUrlToImage();
        Picasso.get().load(imageUri).placeholder(R.drawable.loading).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = n.getUrl().toString();
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(context, Uri.parse(url));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView name, dateTime , title;
        ImageView image;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            name = itemView.findViewById(R.id.name);
            dateTime = itemView.findViewById(R.id.dateTime);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}
