package com.unachicayelmundo.samirmarin.spotifystreamer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;


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
        String artistA = "Blink-182 ";
        String artistB = "Passenger ";
        String artistC = "Franco De vita ";
        String artistD = "Adrian Marin ";
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

        // shows the artist details when clicked by the user
        list_artist_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent individualArtistIntent = new Intent(getActivity(), IndividualArtistActivity.class);
                individualArtistIntent.putExtra(individualArtistIntent.EXTRA_TEXT, adapter.getItem(position));
                startActivity(individualArtistIntent);
            }
        });

        return rootView;
    }

    // filter for when searching artist name... 
    private TextWatcher makeTextWatcher(){
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s != null){
                    //adapter.getFilter().filter(s);
                    //updateArtistList(s.toString());
                }

                //updateArtistList(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s != null){

                }
                //updateArtistList(s.toString());

            }

            // shows toast if not artist are found when searching.
            @Override
            public void afterTextChanged(Editable s) {
                //updateArtistList(s.toString());

                if(adapter.isEmpty()){
                    Context context = getActivity();
                    String text = "no artist found";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }

            }
        };


        return textWatcher;


    }

    @Override
    public void onStart() {
        super.onStart();
        updateArtistList("Beyonce");
    }

    private void updateArtistList(String str){
        FetchArtistTask fetch = new FetchArtistTask();
        fetch.execute(str);
    }

    public class FetchArtistTask extends AsyncTask<String, Void, String[]>{
        private String LOGTAG = FetchArtistTask.class.getSimpleName();
        SpotifyService spotifyService;

        @Override
        protected void onPreExecute() {
            SpotifyApi spotifyApi = new SpotifyApi();
            spotifyService = spotifyApi.getService();

        }

        @Override
        protected String[] doInBackground(String... params) {

            if(params.length == 0){
                return null;
            }


            ArtistsPager artistsResult = spotifyService.searchArtists(params[0]);



            String[] artistInfo = new String[100];
            int i = 0;

            for(Artist artist: artistsResult.artists.items){
                String tempString = artist.name + " " + artist.id;
                artistInfo[i] = tempString;
                i++;

            }

            //temp holder
            return artistInfo;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            adapter.clear();
            for(int i =0; i < strings.length; i++){
                adapter.add(strings[i]);
            }
        }
    }
}
