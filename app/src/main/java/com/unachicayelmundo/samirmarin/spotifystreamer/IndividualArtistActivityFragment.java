package com.unachicayelmundo.samirmarin.spotifystreamer;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class IndividualArtistActivityFragment extends Fragment {

    public IndividualArtistActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_individual_artist, container, false);

        Intent intent = getActivity().getIntent();

        if(intent != null && intent.hasExtra(intent.EXTRA_TEXT)){
            String artistDetail = intent.getStringExtra(intent.EXTRA_TEXT);
            ((TextView) rootView.findViewById(R.id.artist_detail)).setText(artistDetail);

        }

        return rootView;
    }
}
