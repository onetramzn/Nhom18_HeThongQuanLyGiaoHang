package edu.huflit.shopDT.MenuFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;

import edu.huflit.shopDT.Phones;
import edu.huflit.shopDT.R;
import edu.huflit.shopDT.Adapter.ShopAdapter;

public class SearchFragment extends Fragment {
    private ArrayList<Phones> phoneList;
    SearchView searchView;
    private ShopAdapter shopAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.search_ac, container, false);
        final RecyclerView recyclerView =  view.findViewById(R.id.SearchRycyclerView);
        searchView = view.findViewById(R.id.searchView);
//        videoDBHelper = new VideoDBHelper(getActivity());
//        phoneList = videoDBHelper.getVideos();
        shopAdapter = new ShopAdapter(phoneList,getActivity());
        recyclerView.setAdapter(shopAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (getContext(),LinearLayoutManager.VERTICAL,false));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                videoDBHelper = new VideoDBHelper(getActivity());
//                phoneList = videoDBHelper.search(searchView.getQuery().toString());
                shopAdapter = new ShopAdapter(phoneList,getActivity());
                recyclerView.setAdapter(shopAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager
                        (getContext(),LinearLayoutManager.VERTICAL,false));
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
//                videoDBHelper = new VideoDBHelper(getActivity());
//                phoneList = videoDBHelper.search(searchView.getQuery().toString());
                shopAdapter = new ShopAdapter(phoneList,getActivity());
                recyclerView.setAdapter(shopAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager
                        (getContext(),LinearLayoutManager.VERTICAL,false));
                return false;
            }
        });
        return view;

    }
}