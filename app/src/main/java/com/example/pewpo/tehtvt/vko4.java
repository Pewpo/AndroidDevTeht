package com.example.pewpo.tehtvt;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
//------------------ TEHTÄVÄÄ 1 && 2-----------------
//public class vko4 extends AppCompatActivity {
    public class vko4 extends YouTubeBaseActivity {
        //-------------------------------------- TEHTÄVÄÄN 2 ------------------------------------------
/*
    // list view
    private ListView listview;
    // path to mp3-files
     private String mediaPath;
    // List of Strings to hold mp3 filenames
    private List<String> songs = new ArrayList<String>();
    //Mediaplayer for playing music
    private MediaPlayer mediaPlayer = new MediaPlayer();
    //use AsyncTask to load filenames
    private LoadSongsTask task;
*/
    //--------------------------------------- TEHTÄVÄÄN 1 -----------------------------------------
    // image view object
 /*  public ImageView imageView;
    // text view object
    private TextView textView;
    // progress bar object
    private ProgressBar progressBar;
    // example image's path (change to your own if needed...)
    private final String PATH = "http://student.labranet.jamk.fi/~H9577/Androidkurssi/teht4/";
    // example image names (change to your own if needed...)
    private String[] images = {"vuoristo.jpg","kaupunki.png","luola.png"};
    // which image is now visible
    private int imageIndex;
    // async task to load a new image
    private DownloadImageTask task;
    // swipe down and up values
    private float x1,x2;*/
 //-------------------------------------- TEHTÄVÄ 3 -----------------------------------------------

    YouTubePlayerView mYoutubePlayerView;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_vko4);
        //setContentView(R.layout.activity_vko4_1);
        setContentView(R.layout.activity_vko4_youtube);


        //---------------------------- TEHTÄVÄÄN 1 -------------------------------------
      /*  // get views
        imageView = (ImageView)findViewById(R.id.imageView5);
        textView = (TextView) findViewById(R.id.textView7);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // start showing images
        imageIndex = 0;
        showImage();*/

        //---------------------------- TEHTÄVÄÄN 2 ---------------------------------------

       /* listview = (ListView) findViewById(R.id.ListViewVko4);
        mediaPath = Environment.getExternalStorageDirectory().getPath() + "/Download/";
        Toast.makeText(getBaseContext(), mediaPath.toString(), Toast.LENGTH_LONG).show();
        // item listener
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void
            onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try
                {
                    mediaPlayer.reset();
                    // in recursive version
                    mediaPlayer.setDataSource(songs.get(position));
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }
                catch (IOException e) {
                    Toast.makeText(getBaseContext(), "Cannot startaudio!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    // mp3 files recursively from sdcard (takes time to
    // make a list if a lot of songs in sdcard)
        task = new LoadSongsTask();
        task.execute();*/
       //--------------------- TEHTÄVÄÄN 4 (youtube) -----------------------------------------------

        btnPlay = (Button) findViewById(R.id.btnPlay);
        mYoutubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlay);

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

              //  youTubePlayer.loadPlaylist("PLXaADe2XOypidJ2QIOAf3DcVL9TVQ3D_y");
                youTubePlayer.loadVideo("lk7E4oswslM");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        btnPlay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mYoutubePlayerView.initialize(YoutubeConfig.getApiKey(), mOnInitializedListener);
            }
        });
    }
 //------------------------------------------- TEHTÄVÄÄN 1 ---------------------------------------------
    /*
    public void showImage() {
        // create a new AsyncTask object
        task = new DownloadImageTask();
        // start asynctask
        task.execute(PATH + images[imageIndex]);


    }


    // asynctask class
    private class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {

        // this is done in UI thread, nothing this time
        @Override
        protected void onPreExecute() {
            // show loading progress bar
            progressBar.setVisibility(View.VISIBLE);

        }

        // this is background thread, load image and pass it to onPostExecute
        @Override
        protected Bitmap doInBackground(String... urls) {
            URL imageUrl;
            Bitmap bitmap = null;

            try {
                imageUrl = new URL(urls[0]);
                InputStream in = imageUrl.openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("<<LOADIMAGE>>", e.getMessage());
            }
            return bitmap;
        }

        // this is done in UI thread
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
            textView.setText("Image " + (imageIndex + 1) + "/" + images.length);
            // hide loading progress bar
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
    // method gets called when user performs any touch event on screen
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                if (x1 < x2) { // left to right -> previous
                    imageIndex--;
                    if (imageIndex < 0) imageIndex = images.length-1;
                } else { // right to left -> next
                    imageIndex++;
                    if (imageIndex > (images.length-1)) imageIndex = 0;
                }
                showImage();
                break;
        }
        return false;
    }
*/
    //-------------------------------------- TEHTÄVÄÄN 2 -------------------------------------------
/*
    // Use AsyncTask to read all mp3 file names
    private class LoadSongsTask extends AsyncTask<Void, String, Void> {
        private List<String> loadedSongs = new ArrayList<String>();
        protected  void onPreExecute() {
            Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
        }
        protected Void doInBackground(Void... url) {
            updateSongListRecursive(new File(mediaPath));
            return null;
        }
        public void updateSongListRecursive(File path) {
            if (path.isDirectory()) {
                for (int i = 0; i < path.listFiles().length; i++) {
                    File file = path.listFiles()[i];
                    updateSongListRecursive(file);
                }
            }
            else
            {
                String name = path.getAbsolutePath();
                publishProgress(name);
                if (name.endsWith(".mp3")) {
                    loadedSongs.add(name);
                }
            }
        }
        protected void onPostExecute(Void args) {
            ArrayAdapter<String> songList = new ArrayAdapter<String>(vko4.this, android.R.layout.simple_list_item_1, loadedSongs);
            listview.setAdapter(songList);
            songs = loadedSongs;

            Toast.makeText(getApplicationContext(), "Songs=" + songs.size(), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying()) mediaPlayer.reset();
    }*/
}
