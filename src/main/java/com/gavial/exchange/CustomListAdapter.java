package com.gavial.exchange;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gavial.exchange.model.Currency;

import java.util.List;

public class CustomListAdapter  extends BaseAdapter {

        private final List<Currency> listData;
        private final LayoutInflater layoutInflater;
        private Context context;

        public CustomListAdapter(Context aContext,  List<Currency> listData) {
            this.context = aContext;
            this.listData = listData;
            layoutInflater = LayoutInflater.from(aContext);
        }

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public Object getItem(int position) {
            return listData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("SetTextI18n")
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
                holder = new ViewHolder();
                holder.countryNameView = (TextView) convertView.findViewById(R.id.textView_countryName);
                holder.populationView = (TextView) convertView.findViewById(R.id.textView_population);
                holder.populationView2 = (TextView) convertView.findViewById(R.id.textView_population2);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Currency currency = this.listData.get(position);
            holder.countryNameView.setText(currency.getSaleRate().substring(0,5));
            holder.populationView.setText(currency.getPurchaseRate().substring(0,5));
            holder.populationView2.setText(currency.getCurrency());

            return convertView;
        }

        static class ViewHolder {
            TextView countryNameView;
            TextView populationView;
            TextView populationView2;
        }

    }
