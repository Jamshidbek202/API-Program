package com.example.apiprogram.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiprogram.Models.AlbumModel;
import com.example.apiprogram.Models.UserModel;
import com.example.apiprogram.R;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private ArrayList<AlbumModel> list = new ArrayList<>();
    private Context context;

    public AlbumAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.albums_item, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.author_name.setText(list.get(position).getAuthor_name());
    }

    public void setAlbums(ArrayList<AlbumModel> albumList){
        list = albumList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder{

        TextView title, author_name;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txt_album_name);
            author_name = itemView.findViewById(R.id.txt_author_name);

        }
    }
}
