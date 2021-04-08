package com.sigma.roomdatabase;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    private List<MovieList> movieLists;
    private List<MovieList> lists;
    private Context context;


    public MyAdapter(Context context, List<MovieList> movieLists) {
        this.movieLists = movieLists;
        this.context = context;
        this.lists = new ArrayList<>(movieLists);
    }

    public MyAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sample_layout_u, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MovieList list = movieLists.get(position);
        Glide.with(context).load(list.getMoviePic()).into(holder.image);
        holder.title.setText(list.getMoveTitle());
        holder.date.setText(list.getReleaseDate());
        holder.rating.setText(list.getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                menu.add(position, 101, 0, "Edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        Intent intent = new Intent(context, UpdateActivity.class);
                        intent.putExtra("serial", list.getSerial());
                        intent.putExtra("title", list.getMoveTitle());
                        intent.putExtra("poster", list.getMoviePic());
                        intent.putExtra("date", list.getReleaseDate());
                        intent.putExtra("rating", list.getRating());

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);

                        return true;
                    }
                });

                menu.add(position, 101, 1, "Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        BaseActivity.myDB.myDAO().dataDelete(list);
                        showNotification("Successfully Deleted", list.getMoveTitle()+" is deleted");

                        return false;
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return movieLists.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title, date, rating;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.moviePhoto_Id);
            title = itemView.findViewById(R.id.title_Id);
            date = itemView.findViewById(R.id.date_Id);
            rating = itemView.findViewById(R.id.rating_Id);

        }

    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<MovieList> filteredMovies = new ArrayList<>();

            if (constraint.toString().isEmpty()) {
                filteredMovies.addAll(lists);
            } else {
                for (MovieList movie : lists) {
                    if (movie.getMoveTitle().toLowerCase().contains(constraint.toString().toLowerCase())
                    || movie.getReleaseDate().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredMovies.add(movie);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredMovies;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            movieLists.clear();
            movieLists.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    private void showNotification(String NotTitle, String Text) {


        // Notification Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "ID");
        builder.setContentTitle(NotTitle);
        builder.setContentText(Text);
        builder.setSmallIcon(R.drawable.ic_warning_24);
        builder.setAutoCancel(true);


        // Get Ready Intent
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);



        //Show Notification
        long notIdLong = System.currentTimeMillis();
        int notId = (int) notIdLong;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("ID", "name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
            notificationManager.notify(notId, builder.build());
        }else {

            notificationManager.notify(notId, builder.build());
        }



    }


}
