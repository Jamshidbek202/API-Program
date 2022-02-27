package com.example.apiprogram.Adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiprogram.AlbumActivity;
import com.example.apiprogram.MainActivity;
import com.example.apiprogram.Models.UserModel;
import com.example.apiprogram.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewholder> {

    private ArrayList<UserModel> list = new ArrayList<>();
    private Context context;
    TextView street, suite, city, zipcode, companyName, catchPhrase, bs;

    public UsersAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.users_layout, parent, false);
        return new UserViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewholder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.username.setText(list.get(position).getUsername());
        holder.email.setText(list.get(position).getEmail());
        holder.phone.setText(list.get(position).getPhone());
        holder.website.setText(list.get(position).getWebsite());

        holder.btn_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ViewGroup viewGroup = view.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.address_dialog, viewGroup, false);
                builder.setView(dialogView);

                street = dialogView.findViewById(R.id.txt_street);
                suite = dialogView.findViewById(R.id.txt_suite);
                city = dialogView.findViewById(R.id.txt_city);
                zipcode = dialogView.findViewById(R.id.txt_zipcode);

                street.setText(list.get(position).getStreet());
                suite.setText(list.get(position).getSuite());
                city.setText(list.get(position).getCity());
                zipcode.setText(list.get(position).getZipcode());

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        holder.btn_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ViewGroup viewGroup = view.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.company_dialog, viewGroup, false);
                builder.setView(dialogView);

                companyName = dialogView.findViewById(R.id.txt_company_name);
                catchPhrase = dialogView.findViewById(R.id.txt_catchPhrase);
                bs = dialogView.findViewById(R.id.txt_bs);

                companyName.setText(list.get(position).getCompanyName());
                catchPhrase.setText(list.get(position).getCatchPhrase());
                bs.setText(list.get(position).getBs());

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AlbumActivity.class);
                intent.putExtra("userID", list.get(position).getId());
                intent.putExtra("author_name", list.get(position).getName());
                context.startActivity(intent);
            }
        });
    }

    public void setAlbums(ArrayList<UserModel> userList){
        list = userList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class UserViewholder extends RecyclerView.ViewHolder{

        TextView name, username, email, phone, website;
        ImageButton btn_address, btn_company;

        public UserViewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_name);
            username = itemView.findViewById(R.id.txt_username);
            email = itemView.findViewById(R.id.txt_email);
            phone = itemView.findViewById(R.id.txt_phone);
            website = itemView.findViewById(R.id.txt_website);
            btn_address = itemView.findViewById(R.id.btn_address);
            btn_company = itemView.findViewById(R.id.btn_company);
        }
    }
}
