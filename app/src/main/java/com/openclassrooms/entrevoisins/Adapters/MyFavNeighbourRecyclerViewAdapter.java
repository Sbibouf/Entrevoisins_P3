package com.openclassrooms.entrevoisins.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyFavNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mFavNeighbours;

    public MyFavNeighbourRecyclerViewAdapter(List<Neighbour> favNeighbours) {
        mFavNeighbours = favNeighbours;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favoris, parent, false);
        return new MyFavNeighbourRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Neighbour neighbour = mFavNeighbours.get(position);
        holder.mNeighbourNameFav.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatarFav.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatarFav);

    }

    @Override
    public int getItemCount() {
        return mFavNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar_favoris)
        public ImageView mNeighbourAvatarFav;
        @BindView(R.id.item_list_name_favoris)
        public TextView mNeighbourNameFav;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public Neighbour getUser(int position){
        return this.mFavNeighbours.get(position);
    }




}
