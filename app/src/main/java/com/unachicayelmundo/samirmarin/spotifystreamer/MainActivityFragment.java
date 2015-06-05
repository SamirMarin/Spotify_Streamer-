package com.unachicayelmundo.samirmarin.spotifystreamer;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ArrayAdapter<String> adapter;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<String> fakeArtist = new ArrayList<String>();
        String artistA = "Blink-182-All the Small things ";
        String artistB = "Passenger-All the Little Things ";
        String artistC = "Franco De vita-Claro que se Perder ";
        String artistD = "Adrian Marin-Esperanza Del Alma ";
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

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        EditText artistSearch = (EditText) rootView.findViewById(R.id.artist_search);

        artistSearch.addTextChangedListener(makeTextWatcher());

        ListView list_artist_view = (ListView) rootView.findViewById(R.id.listview_artist);

        list_artist_view.setAdapter(adapter);

        return rootView;
    }

    // filter for when searching artist name... 
    private TextWatcher makeTextWatcher(){
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };


        return textWatcher;


    }
}
