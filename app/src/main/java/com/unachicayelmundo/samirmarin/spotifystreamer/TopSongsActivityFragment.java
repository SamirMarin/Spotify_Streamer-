package com.unachicayelmundo.samirmarin.spotifystreamer;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class TopSongsActivityFragment extends Fragment {
    private ArrayAdapter<String> adapter;

    public TopSongsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_top_songs, container, false);

        List<String> fakeArtist = new ArrayList<String>();
        String artistA = "Blink-182\n-All the Small things ";
        String artistB = "Passenger\n-All the Little Things ";
        String artistC = "Franco De vita\n-Claro que se Perder ";
        String artistD = "Adrian Marin\n-Esperanza Del Alma ";
        for(int i =0; i < 5; i++){
            fakeArtist.add(artistA + i);
            fakeArtist.add(artistB + i);
            fakeArtist.add(artistC + i);
            fakeArtist.add(artistD + i);
        }
        adapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_artist,
                R.id.list_item_artist_textview,
                fakeArtist
        );

        ListView listTopSongs = (ListView) rootView.findViewById(R.id.top_songs_list);

        listTopSongs.setAdapter(adapter);

        /*listTopSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent topSongIntent = new Intent(getActivity(), TopSongsActivity.class);
                topSongIntent.putExtra(topSongIntent.EXTRA_TEXT, adapter.getItem(position));
                startActivity(topSongIntent);
            }
        });*/

        return rootView;
    }
}
