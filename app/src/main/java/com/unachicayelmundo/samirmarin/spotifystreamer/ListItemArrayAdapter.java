package com.unachicayelmundo.samirmarin.spotifystreamer;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.Image;

/**
 * Created by samirmarin on 15-06-19.
 */
public class ListItemArrayAdapter extends ArrayAdapter {
    Context context;
    List <Artist> values;


    public ListItemArrayAdapter(Context context, List values) {
        super(context, R.layout.artist_item_view, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.artist_item_view, parent, false);

        if(values != null){
            TextView textView = (TextView) rowView.findViewById(R.id.list_item_artist_textview_new);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.artist_imageView);
            textView.setText(values.get(position).name);
            int sizeImageList = values.get(position).images.size();
            if(sizeImageList > 0){
                Picasso.with(context).load(values.get(position).images.get(0).url).into(imageView);

           }


        }


        /*try {
            url = new URL(hashTable.get(values[position]).get(0)..url);
            uri = url.toURI();
            imageView.setImageURI(uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/

        return rowView;



    }
    //public List<String> getValues(){
     //   return this.values;
    //}

    public void addToValues(Artist artist){
        values.add(artist);

    }
    public void setHash(HashMap<String, List<Image>> hashTable){


    }
}
