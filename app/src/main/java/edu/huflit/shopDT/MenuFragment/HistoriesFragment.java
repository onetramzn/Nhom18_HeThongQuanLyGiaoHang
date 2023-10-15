package edu.huflit.shopDT.MenuFragment;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import edu.huflit.shopDT.Phones;
import edu.huflit.shopDT.R;
import edu.huflit.shopDT.Adapter.ShopAdapter;

public class HistoriesFragment extends Fragment {
    ShopAdapter shopAdapter;
    ArrayList<Phones> phoneList;

    Switch swToggleDark;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.histories_ac, container, false);
        //History
        final RecyclerView recyclerView =  view.findViewById(R.id.rvViewed);
//        videoDBHelper = new VideoDBHelper(getActivity());
//        phoneList = videoDBHelper.getViewed();
        shopAdapter = new ShopAdapter(phoneList,getActivity());
        recyclerView.setAdapter(shopAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (getContext(),LinearLayoutManager.VERTICAL,false));

        //delete history
        ImageView deleteHistory = view.findViewById(R.id.deleteHistory);
        deleteHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete History?")
                        .setMessage("Are you sure you want to delete history?")
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                videoDBHelper.deleteViewed();
//                                shopAdapter.notifyDataSetChanged();
                                final RecyclerView recyclerView =  view.findViewById(R.id.rvViewed);
//                                videoDBHelper = new VideoDBHelper(getActivity());
//                                phoneList = videoDBHelper.getViewed();
                                shopAdapter = new ShopAdapter(phoneList,getActivity());
                                recyclerView.setAdapter(shopAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager
                                        (getContext(),LinearLayoutManager.VERTICAL,false));
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        TextView textView1 = view.findViewById(R.id.textView);
        textView1.setPaintFlags(textView1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        return view;


    }

    @Override
    public void onStart() {
//         Saving state of our app
//         using SharedPreferences

        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}