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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Image;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ListItemArrayAdapter adapter;
    private EditText artistSearch;
    private HashMap<String, List<Image>> hashTableArtist;


    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //allows for menu options
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main_activityfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_search){
           String artist = artistSearch.getText().toString();
            updateArtistList(artist);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<Artist> fakeArtist = new ArrayList<Artist>();
        Artist holder = null;
        fakeArtist.add(holder);
        String[] artist = new String[110];
        //fakeArtist.add(new Artist());
        String artistA = "Blink-182 ";
        String artistB = "Passenger ";
        String artistC = "Franco De vita ";
        String artistD = "Adrian Marin ";
        for(int i =0; i < 5; i++){
            //fakeArtist.add(artistA + i);
            //fakeArtist.add(artistB + i);
            //fakeArtist.add(artistC + i);
            //fakeArtist.add(artistD + i);

        }

        fakeArtist.clear();

        adapter = new ListItemArrayAdapter(getActivity(), fakeArtist);
        /*adapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.artist_item_view,
                R.id.list_item_artist_textview_new,
                fakeArtist
        );*/

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        artistSearch = (EditText) rootView.findViewById(R.id.artist_search);

        //search
        //updateArtistList(artistSearch.getText().toString());

        artistSearch.addTextChangedListener(makeTextWatcher());

        ListView list_artist_view = (ListView) rootView.findViewById(R.id.listview_artist);
        //ImageView imageView = (ImageView) rootView.findViewById(R.id.list_item_artist_textview);

        list_artist_view.setAdapter(adapter);

        //View imageView = inflater.inflate(R.layout.artist_item_view, rootView, true);

        for(int i = 0; i < adapter.getCount(); i++) {
           // Picasso.with(getActivity()).load(hashTableArtist.get(adapter.getItem(i)).get(i).url).into(R.id.artist_imageView.);
        }

        // shows the artist details when clicked by the user
        list_artist_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent individualArtistIntent = new Intent(getActivity(), IndividualArtistActivity.class);
                //individualArtistIntent.putExtra(individualArtistIntent.EXTRA_TEXT, adapter.getItem(position));
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

                /*if(adapter.isEmpty()){
                    Context context = getActivity();
                    String text = "no artist found";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }*/

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

    public class FetchArtistTask extends AsyncTask<String, Void, Artist[]>{
        private String LOGTAG = FetchArtistTask.class.getSimpleName();
        SpotifyService spotifyService;

        @Override
        protected void onPreExecute() {
            SpotifyApi spotifyApi = new SpotifyApi();
            spotifyService = spotifyApi.getService();

        }

        @Override
        protected Artist[] doInBackground(String... params) {

            if(params.length == 0){
                return null;
            }


            ArtistsPager artistsResult = spotifyService.searchArtists(params[0]);
            if(artistsResult == null){
                return null;
            }



            String[] artistname = new String[100];
            String[] artistID = new String[100];
            hashTableArtist = new HashMap<>();
            Artist[] artists = new Artist[artistsResult.artists.items.size()];

            int i = 0;

            for(Artist artist: artistsResult.artists.items){
                //String tempString = artist.name;
                //artistname[i] = tempString;
                //artistID[i] = artist.id;
                //hashTableArtist.put(tempString, artist.images);

                artists[i] = artist;
                i++;



            }

            //temp holder
            return artists;
        }

        @Override
        protected void onPostExecute(Artist[] artists) {
            if(artists != null){
                adapter.clear();
                for(int i =0; i < artists.length; i++){
                    adapter.addToValues(artists[i]);
                   // adapter.getValues()[i] = strings[i];
                }
            }

        }
    }
}
