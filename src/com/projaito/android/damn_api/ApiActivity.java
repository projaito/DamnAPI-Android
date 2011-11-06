package com.projaito.android.damn_api;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import org.json.*;

public class ApiActivity extends Activity {
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.api);

    Intent intent = getIntent();
    String apiJSON = intent.getStringExtra("apiJSON");
    try {
      JSONObject json = new JSONObject(apiJSON);
      ((TextView)findViewById(R.id.text)).setText(json.toString(4));
    } catch(JSONException e) {
      e.printStackTrace();
    }
  }
}
