package com.projaito.android.damn_api.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projaito.android.damn_api.R;

public class ApiGroupAdapter extends AmazingAdapter {
  private static final String TAG = "ApiGroupAdapter";

  private Context mContext = null;

  public ApiGroupAdapter(Context context) {
    mContext = context;
  }

  @Override public int getCount() {
    return 4;
    // int res = 0;
    // for (int i = 0; i < all.size(); i++) {
    //   res += all.get(i).second.size();
    // }
    // return res;
  }

  @Override public String getItem(int position) {
    return "Test";
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override protected void bindSectionHeader(View view, int position, boolean displaySectionHeader) {
    if(displaySectionHeader) {
      TextView header = (TextView)view.findViewById(R.id.header);
      header.setVisibility(View.VISIBLE);
      header.setText("Header");
      // getSections()[getSectionForPosition(position)]);
    } else {
      view.findViewById(R.id.header).setVisibility(View.GONE);
    }
  }

  @Override public View getAmazingView(int position, View convertView, ViewGroup parent) {
    View res = convertView;
    if (res == null) {
      res = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
    }

    TextView name = (TextView)res.findViewById(R.id.name);
    name.setText("Test");
    return res;
  }

  @Override public void configurePinnedHeader(View header, int position, int alpha) {
    TextView lSectionHeader = (TextView)header;
    lSectionHeader.setText("Header"); // getSections()[getSectionForPosition(position)]);
    // lSectionHeader.setBackgroundColor(alpha << 24 | (0xbbffbb));
    // lSectionHeader.setTextColor(alpha << 24 | (0x000000));
  }

  @Override public int getPositionForSection(int section) {
    if (section < 0) section = 0;
    if (section >= 2) section = 2 - 1;
    int c = 0;
    for (int i = 0; i < 2; i++) {
      if (section == i) {
        return c;
      }
      c += 2;
    }
    return 0;
    // if (section < 0) section = 0;
    // if (section >= all.size()) section = all.size() - 1;
    // int c = 0;
    // for (int i = 0; i < all.size(); i++) {
    //   if (section == i) {
    //     return c;
    //   }
    //   c += all.get(i).second.size();
    // }
    // return 0;
  }

  @Override public int getSectionForPosition(int position) {
    int c = 0;
    for (int i = 0; i < 2; i++) {
      if (position >= c && position < c + 2) {
        return i;
      }
      c += 2;
    }
    return -1;
    // int c = 0;
    // for (int i = 0; i < all.size(); i++) {
    //   if (position >= c && position < c + all.get(i).second.size()) {
    //     return i;
    //   }
    //   c += all.get(i).second.size();
    // }
    // return -1;
  }

  @Override public String[] getSections() {
    String[] res = new String[2];
    res[0] = "Header1";
    res[1] = "Header2";
    return res;
    // String[] res = new String[all.size()];
    // for (int i = 0; i < all.size(); i++) {
    //   res[i] = all.get(i).first;
    // }
    // return res;
  }

  @Override protected void onNextPageRequested(int page) {
  }
}
