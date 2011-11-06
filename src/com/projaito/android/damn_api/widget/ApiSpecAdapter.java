package com.projaito.android.damn_api.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Pair;
import org.json.*;
import java.util.List;
import java.util.ArrayList;

import com.projaito.android.damn_api.R;

public class ApiSpecAdapter extends AmazingAdapter {
  private static final String TAG = "ApiSpecAdapter";

  private Context mContext = null;
  private JSONObject mSpec = null;
  private List<Pair<String, List<JSONObject>>> data = null;

  public ApiSpecAdapter(Context context, JSONObject spec) {
    mContext = context;
    mSpec = spec;

    data = new ArrayList<Pair<String, List<JSONObject>>>();
    try {
      JSONArray apiGroups = mSpec.getJSONArray("api_groups");
      for(int i=0; i < apiGroups.length(); i++) {
        JSONObject apiGroup = apiGroups.getJSONObject(i);
        JSONArray apis = apiGroup.getJSONArray("apis");
        List apiJSONs = new ArrayList<JSONObject>();
        for(int j=0; j < apis.length(); j++) {
          JSONObject api = apis.getJSONObject(j);
          apiJSONs.add(api);
        }
        data.add(new Pair<String, List<JSONObject>>(apiGroup.getString("name"), apiJSONs));
      }
    } catch(JSONException e) {
      e.printStackTrace();
    }
  }

  @Override public int getCount() {
    int res = 0;
    for (int i = 0; i < data.size(); i++) {
      res += data.get(i).second.size();
    }
    return res;
  }

  @Override public JSONObject getItem(int position) {
    int c = 0;
    for (int i = 0; i < data.size(); i++) {
      if (position >= c && position < c + data.get(i).second.size()) {
        return data.get(i).second.get(position - c);
      }
      c += data.get(i).second.size();
    }
    return null;
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override protected void bindSectionHeader(View view, int position, boolean displaySectionHeader) {
    if(displaySectionHeader) {
      TextView header = (TextView)view.findViewById(R.id.header);
      header.setVisibility(View.VISIBLE);
      header.setText(getSections()[getSectionForPosition(position)]);
    } else {
      view.findViewById(R.id.header).setVisibility(View.GONE);
    }
  }

  @Override public View getAmazingView(int position, View convertView, ViewGroup parent) {
    View res = convertView;
    if (res == null) {
      res = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
    }

    String apiName = null;
    try {
      apiName = getItem(position).getString("name");
    } catch(JSONException e) {
      e.printStackTrace();
    }

    TextView name = (TextView)res.findViewById(R.id.name);
    name.setText(apiName);
    return res;
  }

  @Override public void configurePinnedHeader(View header, int position, int alpha) {
    ((TextView)header).setText(getSections()[getSectionForPosition(position)]);
  }

  @Override public int getPositionForSection(int section) {
    if (section < 0) section = 0;
    if (section >= data.size()) section = data.size() - 1;
    int c = 0;
    for (int i = 0; i < data.size(); i++) {
      if (section == i) {
        return c;
      }
      c += data.get(i).second.size();
    }
    return 0;
  }

  @Override public int getSectionForPosition(int position) {
    int c = 0;
    for (int i = 0; i < data.size(); i++) {
      if (position >= c && position < c + data.get(i).second.size()) {
        return i;
      }
      c += data.get(i).second.size();
    }
    return -1;
  }

  @Override public String[] getSections() {
    String[] res = new String[data.size()];
    for (int i = 0; i < data.size(); i++) {
      res[i] = data.get(i).first;
    }
    return res;
  }

  @Override protected void onNextPageRequested(int page) {
  }
}
