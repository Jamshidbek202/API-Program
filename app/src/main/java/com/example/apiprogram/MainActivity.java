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
import com.example.apiprogram.Adapters.UsersAdapter;
import com.example.apiprogram.Models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url = "https://jsonplaceholder.typicode.com/users";
    ArrayList<UserModel> userModelList;
    UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userModelList = new ArrayList<>();
        adapter = new UsersAdapter(this);
        RecyclerView user_recycler = findViewById(R.id.users_recyclerview);
        user_recycler.setAdapter(adapter);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray userArray = new JSONArray(response);

                    for(int i = 0; i < userArray.length(); i++){
                        JSONObject userObject = userArray.getJSONObject(i);
                        int id = userObject.getInt("id");
                        String name = userObject.getString("name");
                        String username = userObject.getString("username");
                        String email = userObject.getString("email");
                        String phone = userObject.getString("phone");
                        String website = userObject.getString("website");

                        JSONObject addressObject = userObject.getJSONObject("address");
                        String street = addressObject.getString("street");
                        String suite = addressObject.getString("suite");
                        String city = addressObject.getString("city");
                        String zipcode = addressObject.getString("zipcode");
                        JSONObject geoObject = addressObject.getJSONObject("geo");
                        String lat = geoObject.getString("lat");
                        String lng = geoObject.getString("lng");

                        JSONObject companyObject = userObject.getJSONObject("company");
                        String companyName = companyObject.getString("name");
                        String catchPhrase = companyObject.getString("catchPhrase");
                        String bs = companyObject.getString("bs");

                        UserModel model = new UserModel(id, name, username, email, street, suite, city, zipcode, lat, lng, phone, website, companyName, catchPhrase, bs);
                        userModelList.add(model);
                    }

                    adapter.setAlbums(userModelList);

                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(this).add(request);
    }
}