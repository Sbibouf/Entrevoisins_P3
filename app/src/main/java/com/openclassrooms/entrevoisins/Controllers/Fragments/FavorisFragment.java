package com.openclassrooms.entrevoisins.Controllers.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.Adapters.MyFavNeighbourRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.Adapters.MyNeighbourRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.Controllers.Activities.activity_detail_neighbour;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.ItemClickSupport;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavorisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavorisFragment extends Fragment {
    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighboursFav;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
    private MyFavNeighbourRecyclerViewAdapter adapter;


    /**
     * Create and return a new instance
     *
     * @return @{@link FavorisFragment}
     */
    public static FavorisFragment newInstance() {
        FavorisFragment fragment = new FavorisFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favoris_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        initList();
        configureOnClickRecyclerView();
        return view;
    }

    /**
     * Init the List of fav neighbours
     */
    private void initList() {
        mNeighboursFav = mApiService.getFavNeighbours();
        adapter = new MyFavNeighbourRecyclerViewAdapter(mNeighboursFav);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_neighbour)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Neighbour user = adapter.getUser(position);
                        Intent intent = new Intent(getActivity(), activity_detail_neighbour.class);
                        intent.putExtra("name", user.getName());
                        intent.putExtra("phone", user.getPhoneNumber());
                        intent.putExtra("address", user.getAddress());
                        intent.putExtra("about", user.getAboutMe());
                        intent.putExtra("avatar", user.getAvatarUrl());
                        startActivity(intent);

                    }
                });
    }


    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }
}