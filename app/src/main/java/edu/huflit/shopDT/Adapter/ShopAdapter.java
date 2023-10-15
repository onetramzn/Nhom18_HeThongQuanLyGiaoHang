package edu.huflit.shopDT.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.huflit.shopDT.PhoneDetail_Activity;
import edu.huflit.shopDT.Phones;
import edu.huflit.shopDT.R;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.PhonelistViewHolder> {

    private ArrayList<Phones> phoneList;
    Context context;
    public static class PhonelistViewHolder extends RecyclerView.ViewHolder{
        TextView mName, mPrice , mDe, mvideoID;
        ImageView mIMGView,favoriteIV;
        public PhonelistViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tvName);
            mPrice = itemView.findViewById(R.id.tvPrice);
            mDe = itemView.findViewById(R.id.tvDe);
            mIMGView = itemView.findViewById(R.id.imgItemView);
//            mvideoID = itemView.findViewById(R.id.videoID);
            favoriteIV = itemView.findViewById(R.id.favoriteIV);
        }
    }
    public ShopAdapter(ArrayList<Phones> phoneList, Context context) {
        this.phoneList = phoneList;
        this.context = context;
    }
    @NonNull
    @Override
    public PhonelistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listView = inflater.inflate(R.layout.phone_list_item_layout, parent, false);
        PhonelistViewHolder viewHolder = new PhonelistViewHolder(listView) ;
        return viewHolder;
    }
    public void onBindViewHolder(@NonNull PhonelistViewHolder holder, int position) {
        Phones item = phoneList.get(position);
        Glide.with(this.context)
                        .load(item.getImgURL())
                                .into(holder.mIMGView);
        holder.mName.setText(item.getName());
        holder.mPrice.setText(item.getPrice());
        holder.mDe.setText(item.getDe());
        //khai báo thêm vào đây --------

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PhoneDetail_Activity.class);
                i.putExtra("Price@#", phoneList.get(position).getPrice());
                i.putExtra("NAME@#", phoneList.get(position).getName());
                i.putExtra("Details#@", phoneList.get(position).getDe());
//                i.putExtra("Favorite#@",phoneList.get(position).getFavorite());
                i.putExtra("imgURL#@",phoneList.get(position).getImgURL());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return phoneList.size();
    }

}
