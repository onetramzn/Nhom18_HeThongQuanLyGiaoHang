package edu.huflit.shopDT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

import edu.huflit.shopDT.Activity.HomeActivity;

public class PhoneDetail_Activity extends AppCompatActivity {
    TextView  mvideoname, mtvdetails, mprice;
    ImageView back,mimg;
    ImageButton favorite;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplay);


        //ánh xạ chi tiết của video


        mvideoname = findViewById(R.id.phone_NAME);
        mtvdetails = findViewById(R.id.tvDetails);
        mprice = findViewById(R.id.phone_Price);
        back = findViewById(R.id.backIV);
        favorite = findViewById(R.id.favoriteIV);
        mimg = findViewById(R.id.img);



        // nhận intent từ VideolistActivity và gán vào ID
        mvideoname.setText(getIntent().getStringExtra("NAME@#"));
        mtvdetails.setText(getIntent().getStringExtra("Details#@"));
        mprice.setText(getIntent().getStringExtra("Price@#"));
        int fav = getIntent().getIntExtra("Favorite#@",0);
        String imgURL = getIntent().getStringExtra("imgURL#@");

        Glide.with(this)
                .load(imgURL)
                .into(mimg);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneDetail_Activity.this,HomeActivity.class);
                startActivity(intent);
            }
        });


//        //Dùng thư viện youtubeplayerView để chạy video từ YTID đã được intent và gán ở trên
//        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
//        getLifecycle().addObserver(youTubePlayerView);
//        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                String videoId = ID;
//                youTubePlayer.loadVideo(videoId, 0);
//            }
//        });



        if(fav==1){
            favorite.setBackground(getDrawable(R.drawable.baseline_favorite_24));
        }
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fav==1){
                    favorite.setBackground(getDrawable(R.drawable.baseline_favorite_border_24));
//                    videoDBHelper.unfavorite(vid);
                }
                else if(fav==0){
                    favorite.setBackground(getDrawable(R.drawable.baseline_favorite_24));
//                    videoDBHelper.favorite(vid);
                }
            }
        });
    }
}