package com.example.pewpo.tehtvt;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.app.Notification.VISIBILITY_PUBLIC;
import static android.widget.Toast.LENGTH_SHORT;

public class vko3 extends AppCompatActivity {
    private int notification_id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vko3);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
  /*  public void changeArenaBackground()
    {
        LayoutInflater inflater = getLayoutInflater();
        // inflate layout from XML (toast.xml)
        View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toast));
        ImageView image = (ImageView) layout.findViewById(R.id.toast);
        image.setImageResource(R.drawable.arena);

    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String winner = "";
        // get layout from Activity
        LayoutInflater inflater = getLayoutInflater();
        // inflate layout from XML (toast.xml)
        View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toast));
        ImageView image1 = (ImageView) layout.findViewById(R.id.imageView2);
        ImageView image2 = (ImageView) layout.findViewById(R.id.imageView3);
        TextView text = (TextView) layout.findViewById(R.id.textView4);
        text.setText(R.string.vs);

        switch(item.getItemId())
        {
            case R.id.action_joker_vs_batman:
                image1.setImageResource(R.drawable.batman);
                image2.setImageResource(R.drawable.joker);
                winner = getWinner("batman","joker");
                break;
            case R.id.action_superman_vs_batman:
                image1.setImageResource(R.drawable.batman);
                image2.setImageResource(R.drawable.superman);
                winner = getWinner("superman","batman");
                break;
            case R.id.action_joker_vs_superman:
                image1.setImageResource(R.drawable.joker);
                image2.setImageResource(R.drawable.superman);
                winner = getWinner("joker","superman");
                break;
        }

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0,100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

        //Kerrotaan voittaja notificaation avulla
        createNotification(VISIBILITY_PUBLIC, winner);

        return true;
    }

    public void createNotification(int visibility, String winner)
    {
        // create a new notification
        Notification notification  = new Notification.Builder(this)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle("Game Notification")
                .setContentText("Winner is : " + winner)
                .setSmallIcon(R.drawable.android)
                .setAutoCancel(true)
                .setVisibility(visibility).build();

        // connect notification manager
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // make a new notification with a new unique id
        notification_id++;
        notificationManager.notify(notification_id, notification);
    }
    private String getWinner(String player1, String player2)
    {
        String winner;
        Random r = new Random();
        int i = r.nextInt(2);
        if(i == 0)
            winner = player1;
        else
            winner = player2;
        return winner;
    }


}
