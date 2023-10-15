package edu.huflit.shopDT.MenuFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.huflit.shopDT.Phones;
import edu.huflit.shopDT.R;
import edu.huflit.shopDT.Adapter.ShopAdapter;

public class FavoriteFragment extends Fragment {
    ShopAdapter shopAdapter;
    ArrayList<Phones> phoneList;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.favorite_ac, container, false);
        final RecyclerView recyclerView =  view.findViewById(R.id.rvFavorite);

//        phoneList = videoDBHelper.getFAV();
        shopAdapter = new ShopAdapter(phoneList,getActivity());
        recyclerView.setAdapter(shopAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (getContext(),LinearLayoutManager.VERTICAL,false));

        shopAdapter.notifyDataSetChanged();
//        FloatingActionButton delete = view.findViewById(R.id.deleteFAV);
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AlertDialog.Builder(getContext())
//                        .setTitle("Delete Favorite list?")
//                        .setMessage("Are you sure you want to delete favorites list?")
//                        // A null listener allows the button to dismiss the dialog and take no further action.
//                        .setNegativeButton(android.R.string.no, null)
//                        // Specifying a listener allows you to take an action before dismissing the dialog.
//                        // The dialog is automatically dismissed when a dialog button is clicked.
//                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
////                                videoDBHelper.deleteFAV();
//
////                                phoneList = videoDBHelper.getFAV();
//                                shopAdapter = new ShopAdapter1(phoneList,getActivity());
//                                recyclerView.setAdapter(shopAdapter);
//                                recyclerView.setLayoutManager(new LinearLayoutManager
//                                        (getContext(),LinearLayoutManager.VERTICAL,false));
//                            }
//                        })
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .show();
//            }
//        });
        return view;
    }
}