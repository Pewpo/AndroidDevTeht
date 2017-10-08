package com.example.pewpo.tehtvt;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private JSONArray JsonString;
    public ArrayList<Courses>  AllCourses= new ArrayList<>();
   // private String JsonString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        AddData();
        LatLng sydney = new LatLng(62.2416223, 25.7597309);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    public  void NewMapView()
    {
        for (Courses course : AllCourses) {
            BitmapDescriptor color  = null;
            int lat = (int) course.getLat();
            switch (course.type)
            {
                case "Kulta":
                    color = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW);
                    break;
                case "Kulta/Etu":
                    color = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET);
                    break;
                case "Etu":
                    color = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                    break;
                default:
                    color = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE);
                    break;
            }

            mMap.addMarker(new MarkerOptions().position(new LatLng(course.getLat(), course.getLgn()))
                    .title(course.getCourse().toUpperCase())
                    .snippet(course.getAddress()+"\n"+course.getPhone()+"\n"+course.getEmail()+"\n"+course.getWeb())
                    .icon(color));
           
        }
    }
    public void AddData() {
        String url = "http://ptm.fi/materials/golfcourses/golf_courses.json";
        FetchDataTask task = new FetchDataTask();
        task.execute(url);
    }

    class FetchDataTask extends AsyncTask<String, Void, JSONObject> {
            @Override
            protected JSONObject doInBackground(String... urls) {
                HttpURLConnection urlConnection = null;
                JSONObject json = null;
                try {
                    URL url = new URL(urls[0]);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    json = new JSONObject(stringBuilder.toString());
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) urlConnection.disconnect();
                }
                return json;
            }

            protected void onPostExecute(JSONObject json) {
                try {
                    JsonString = json.getJSONArray("courses");
                    for (int i=0;i < JsonString.length();i++) {
                        JSONObject hs = JsonString.getJSONObject(i);
                        Courses course = new Courses(hs.getString("type"), hs.getDouble("lat"), hs.getDouble("lng"), hs.getString("course"), hs.getString("address"),
                                                     hs.getString("phone"), hs.getString("email"));
                        AllCourses.add(course);
                    }
                } catch (JSONException e) {
                    Log.e("JSON", "Error getting data.");
                }
                NewMapView();
            }
    }

    protected class Courses
    {
        String type = "";
        double lat = 0;
        double lgn = 0;
        String course= "";
        String address = "";
        String phone = "";
        String email = "";
        String web = "";
        public Courses(String type, double lat, double lgn, String course, String address, String phone, String email) {
            this.type = type;
            this.lat = lat;
            this.lgn = lgn;
            this.course = course;
            this.address = address;
            this.phone = phone;
            this.email = email;
        }

        public String getType() {
            return type;
        }

        public double getLat() {
            return lat;
        }

        public double getLgn() {
            return lgn;
        }

        public String getCourse() {
            return course;
        }

        public String getAddress() {
            return address;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getWeb() {
            return web;
        }
    }
}
