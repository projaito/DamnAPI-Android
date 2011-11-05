package com.projaito.android.damn_api;

import android.app.Activity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.net.MalformedURLException;
import org.apache.commons.io.IOUtils;

import com.projaito.android.damn_api.widget.*;

public class MainActivity extends Activity {
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    // try {
    //   URL url = new URL("https://raw.github.com/gist/1338795/faa1f0056de928ce372b8db59c335523ef507a86/twitter_api.json");
    //   new DownloadSpecTask().execute(url);
    // } catch(MalformedURLException e) {
    //   e.printStackTrace();
    // }

    AmazingListView list = (AmazingListView)findViewById(R.id.list);
    list.setPinnedHeaderView(LayoutInflater.from(this).inflate(R.layout.list_header, list, false));
    list.setAdapter(new ApiGroupAdapter(this));
  }

  // private class DownloadSpecTask extends AsyncTask<URL, Void, String> {
  //   protected String doInBackground(URL... urls) {
  //     URL url = urls[0];
  //     HttpsURLConnection urlConnection = null;
  //     String result = null;
  //     try {
  //       urlConnection = (HttpsURLConnection) url.openConnection();
  //       InputStream in = new BufferedInputStream(urlConnection.getInputStream());
  //       result = IOUtils.toString(in, urlConnection.getContentEncoding());
  //     } catch(IOException e) {
  //       e.printStackTrace();
  //     } finally {
  //       urlConnection.disconnect();
  //     }
  //     return result;
  //   }

  //   protected void onPostExecute(String result) {
  //     if(result == null) {
  //       result = "Error";
  //     }
  //   }
  // }
}
