package com.example.colorscheme;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MyAsyncTask extends AsyncTask<String, Void, String> {
    //public AsyncResponse delegate; //callback interface
    //public MyAsyncTask(AsyncResponse asyncResponse) {
        //delegate = asyncResponse; //Assigns all back interface via constructor
    //}

    //private Context context;
    //ColorActivity colorActivity = new ColorActivity();

    String artist = "";
    String song = "";
    String startAPICall(final String song1, final String artist1) throws IOException {
        artist = artist1;
        song = song1;
        String stringURL = "https://api.lyrics.ovh/v1/" + artist1 + "/" + song1 + "/json";

        // Connect to the URL using java's native library
        URL url = new URL(stringURL);
        URLConnection request = url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert input stream to a json element
        JsonObject obj = root.getAsJsonObject(); //May be an array or an object.
        return obj.get("lyrics").getAsString(); //grabbing lyrics
    }

    public String getLyrics() {
        try {
            return startAPICall(song, artist);
        } catch (IOException e) {
            e.printStackTrace();
            //return "Song and corresponding artist does not exist.\nPlease double check song title and artist name and try again";
            throw new RuntimeException(e);
        }
    }

    public interface AsyncResponse {
        void processFinish(String output);
    }

    protected void onPreExecute() {
        //Setup precondition to execute some task
    }

    protected String doInBackground(String... params) {
        //Do some task
        //publishProgress (1);
        return getLyrics();
    }

//    protected void onProgressUpdate(Integer... values) {
//        //Update the progress of current task
//    }

    //protected void onPostExecute(String s) {
        //Show the result obtained from doInBackground
        //TextView output = ((AppCompatActivity) context).findViewById(R.id.message);
//        String message = colorActivity.getSong() + " by " + colorActivity.getArtist() + "\n" + getLyrics();
//        output.setText(message);
       // delegate.processFinish(s.toString());

    //}


}
