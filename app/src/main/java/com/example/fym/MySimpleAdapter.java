package com.example.fym;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySimpleAdapter extends BaseAdapter {

    private final Activity context;
    private final String[] names;

    static class ViewHolder{
        public TextView text;
        public ImageView image;
    }

    public MySimpleAdapter(Activity context,String[] names){
        this.context=context;
        this.names=names;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView=convertView;

        //reuse views
        if(rowView==null){
            LayoutInflater inflater=context.getLayoutInflater();
            rowView=inflater.inflate(R.layout.activity_score,null);

            //Configure view holder
            ViewHolder viewHolder=new ViewHolder();
            viewHolder.text=(TextView) rowView.findViewById(R.id.tv_id);
            viewHolder.image=(ImageView) rowView.findViewById(R.id.iv_id);
            rowView.setTag(viewHolder);
        }

        //fill data
        ViewHolder holder=(ViewHolder) rowView.getTag();
        String s=names[position];
        holder.text.setText(s);

        if(position==0){
            holder.image.setImageResource(R.drawable.golden_trophy);
        } else if(position==1){
            holder.image.setImageResource(R.drawable.silver_trophy);
        } else if(position==2){
            holder.image.setImageResource(R.drawable.bronze_trophy);
        } else if(position==3){
            holder.image.setImageResource(R.drawable.four_black);
        } else if(position==4){
            holder.image.setImageResource(R.drawable.five_black);
        } else if(position==5){
            holder.image.setImageResource(R.drawable.six_black);
        } else if(position==6){
            holder.image.setImageResource(R.drawable.seven_black);
        } else if(position==7){
            holder.image.setImageResource(R.drawable.eight_black);
        } else if(position==8){
            holder.image.setImageResource(R.drawable.nine_black);
        } else if(position==9) {
            holder.image.setImageResource(R.drawable.ten_black);
        }
        return rowView;
    }
}