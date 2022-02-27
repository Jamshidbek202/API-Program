package com.example.apiprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apiprogram.Adapters.AlbumAdapter;
import com.example.apiprogram.Models.AlbumModel;
import com.example.apiprogram.Models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity {

    int userID = 0;
    String author_name = "";
    String url = "https://jsonplaceholder.typicode.com/albums";
    AlbumAdapter adapter;
    ArrayList<AlbumModel> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        userID = getIntent().getIntExtra("userID", -1);
        author_name = getIntent().getStringExtra("author_name");

        albumList = new ArrayList<>();
        adapter = new AlbumAdapter(this);
        RecyclerView album_recycler = findViewById(R.id.albums_recycler);
        album_recycler.setAdapter(adapter);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray albumArray = new JSONArray(response);

                    for(int i = 0; i < albumArray.length(); i++){
                        if (i == userID){
                            JSONObject albumObject = albumArray.getJSONObject(i);
                            int albumID = albumObject.getInt("id");
                            String title = albumObject.getString("title");

                            AlbumModel model = new AlbumModel(userID, albumID, title, author_name);
                            albumList.add(model);
                        }
                    }

                    adapter.setAlbums(albumList);

                } catch (JSONException e) {
                    Toast.makeText(AlbumActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AlbumActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(this).add(request);
    }
}