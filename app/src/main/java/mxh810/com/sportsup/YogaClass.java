 package mxh810.com.sportsup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

 public class YogaClass extends AppCompatActivity {

     private static final String TAG = "YogaClass";

     YouTubePlayerView
             mYouTubePlayerView1,
             mYouTubePlayerView2,
             mYouTubePlayerView3,
             mYouTubePlayerView4,
             mYouTubePlayerView5,
             mYouTubePlayerView6;
     Button
             btnPlay1,
             btnPlay2,
             btnPlay3,
             btnPlay4,
             btnPlay5,
             btnPlay6;

     YouTubePlayer.OnInitializedListener mOnInitializedListener;
     YouTubePlayer.OnInitializedListener mOnInitializedListener2;
     String videoStr = "";

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_yoga_class);


//
//         btnPlay1 = (Button) findViewById(R.id.play_button_1);
//         mYouTubePlayerView1 = (YouTubePlayerView) findViewById(R.id.youtube_player_view_1);
//         btnPlay2 = (Button) findViewById(R.id.play_button_2);
//         mYouTubePlayerView2 = (YouTubePlayerView) findViewById(R.id.youtube_player_view_2);
//         btnPlay3 = (Button) findViewById(R.id.play_button_3);
//         mYouTubePlayerView3 = (YouTubePlayerView) findViewById(R.id.youtube_player_view_3);
//
//         Log.d(TAG, "onCreat: Starting...");

//         btnPlay1.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
////                 videoStr = "lDbJdJWX82M";
//                 Log.d(TAG,"onClick: Initializing.");
//                 mYouTubePlayerView1.initialize(YouTubeConfig.getApiKey(),mOnInitializedListener);
//             }
//         });

//         mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
//             @Override
//             public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                 if(youTubePlayer == null) return;
//                 if(b) {
//                     youTubePlayer.play();
//                 } else {
//                     Log.d(TAG, "onClick: Done initializing.");
//                     youTubePlayer.cueVideo(videoStr);
//                 }
//             }
//
//             @Override
//             public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                 Log.d(TAG, "onClick: Failed to initialize.");
//             }
//         };

//         btnPlay2.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//                 videoStr = "lDbJdJWX82M";
//                 Log.d(TAG,"onClick: Initializing.");
//                 mYouTubePlayerView2.initialize(YouTubeConfig.getApiKey(),mOnInitializedListener2);
//             }
//         });
//
//         mOnInitializedListener2 = new YouTubePlayer.OnInitializedListener() {
//             @Override
//             public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                 if(youTubePlayer == null) return;
//                 if(b) {
//                     youTubePlayer.play();
//                 } else {
//                     Log.d(TAG, "onClick: Done initializing.");
//                     youTubePlayer.loadVideo(videoStr);
//                 }
//             }
//
//             @Override
//             public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                 Log.d(TAG, "onClick: Failed to initialize.");
//             }
//         };


//
//         mOnInitializedListener2 = new YouTubePlayer.OnInitializedListener() {
//             @Override
//             public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                 Log.d(TAG, "onClick: Done initializing.");
//                 youTubePlayer.loadVideo("KEYSO-Tc2Go");
//                 youTubePlayer.play();
//             }
//
//             @Override
//             public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                 Log.d(TAG, "onClick: Failed to initialize.");
//             }
//         };
//
//         btnPlay2.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//                 videoStr = "KEYSO-Tc2Go";
//                 Log.d(TAG,"onClick: Initializing.");
//                 mYouTubePlayerView2.initialize(YouTubeConfig.getApiKey(),mOnInitializedListener);
//             }
//         });

//         btnPlay3.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//                 videoStr = "TrgNZn0xejo";
//                 Log.d(TAG,"onClick: Initializing.");
//                 mYouTubePlayerView3.initialize(YouTubeConfig.getApiKey(),mOnInitializedListener);
//             }
//         });

     }

//     public void onClick(View view) {
//         switch(view.getId()) {
//             case R.id.play_button_2:
//                 videoStr = "TrgNZn0xejo";
//                 Log.d(TAG,"onClick: Initializing.");
//                 mYouTubePlayerView1.initialize(YouTubeConfig.getApiKey(),mOnInitializedListener);
//                 break;
//             case R.id.play_button_3:
//                 videoStr = "KEYSO-Tc2Go";
//                 Log.d(TAG,"onClick: Initializing.");
//                 mYouTubePlayerView1.initialize(YouTubeConfig.getApiKey(),mOnInitializedListener);
//                 break;
//             case R.id.play_button_1:
//                 Log.d(TAG,"onClick: Initializing.");
//                 mYouTubePlayerView1.initialize(YouTubeConfig.getApiKey(),mOnInitializedListener);
//                 break;
//         }
//     }
 }