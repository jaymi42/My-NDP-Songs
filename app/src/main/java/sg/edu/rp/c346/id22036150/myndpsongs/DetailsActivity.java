package sg.edu.rp.c346.id22036150.myndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    ListView lvSongs;
    Button btnBack, btnStars;
    ArrayList<Song> songs;
    ArrayAdapter<Song> aaSongs;
    Spinner dtSpn;
    ArrayAdapter<String> aaSpn;
    ArrayList<Song> strList;
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intentSelected = getIntent();
        btnBack = findViewById(R.id.btnBack);
        btnStars = findViewById(R.id.btnStars);
        lvSongs = findViewById(R.id.lvSongs2);
        dtSpn = findViewById(R.id.spinner);

        strList = new ArrayList<>();
        aaSpn = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);

        ArrayList<Integer> dist = new ArrayList<>();
        DBHelper db = new DBHelper(DetailsActivity.this);
        ArrayList<Song> songList = db.getSong();
        for (Song song : songList) {
            int year = song.getYear();
            if (!dist.contains(year)) {
                dist.add(year);
            }
        }

        for (int year : dist) {
            aaSpn.add(String.valueOf(year));
        }



        dtSpn.setAdapter(aaSpn);


        songs = new ArrayList<Song>();
        adapter = new CustomAdapter(DetailsActivity.this, R.layout.row, songs);
        lvSongs.setAdapter(adapter);
        //aaSongs = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songs);
        //lvSongs.setAdapter(aaSongs);


        DBHelper dbh = new DBHelper(DetailsActivity.this);
        songs.clear();
        songs.addAll(dbh.getSong());
        adapter.notifyDataSetChanged();

        dtSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                songs = db.getSongByYear(Integer.parseInt(parent.getItemAtPosition(position).toString()));
                CustomAdapter list = new CustomAdapter(DetailsActivity.this, R.layout.row, strList);
                lvSongs.setAdapter(list);
                db.close();
                list.clear();
                for (int i = 0; i < songs.size(); i++) {
                    list.add(songs.get(i));
                    list.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song data = songs.get(position);
                Intent intent = new Intent(DetailsActivity.this, UpdateActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(DetailsActivity.this);

        btnStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ArrayList<Song> songsFive = new ArrayList<>();

                for(Song song : songs){
                    if(song.getStar() == 5){
                        songsFive.add(song);
                    }

                    ArrayAdapter<Song> adapterFive = new ArrayAdapter<>(DetailsActivity.this, android.R.layout.simple_list_item_1, songsFive);
                    lvSongs.setAdapter(adapterFive);
                }
            }
        });
    }


}