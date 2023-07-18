package sg.edu.rp.c346.id22036150.myndpsongs;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter(@NonNull Context context, int resource, ArrayList<Song> objects) {

        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        songList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
       TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
       TextView tvSingers = rowView.findViewById(R.id.textViewSingers);
       TextView tvStars = rowView.findViewById(R.id.textViewStars);
       TextView tvYear = rowView.findViewById(R.id.textViewYear);

        // Obtain the Android Version information based on the position
        Song currentSong = songList.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentSong.getTitle());
        tvTitle.setTextColor(Color.parseColor("#0000FF")); // Blue

        tvSingers.setText(currentSong.getSingers());
        tvSingers.setTextColor(Color.parseColor("#00FFFF")); // Light Blue

        tvStars.setText(currentSong.toString());
        tvStars.setTextColor(Color.parseColor("#FF0000")); // Red

        tvYear.setText("" + currentSong.getYear() + "  ");
        tvYear.setTextColor(Color.parseColor("#808080")); // Gray

        return rowView;
    }



}
