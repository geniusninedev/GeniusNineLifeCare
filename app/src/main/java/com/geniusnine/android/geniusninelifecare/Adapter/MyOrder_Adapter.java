package com.geniusnine.android.geniusninelifecare.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.geniusnine.android.geniusninelifecare.R;

import java.util.ArrayList;

/**
 * Created by Dev on 18-01-2017.
 */

public class MyOrder_Adapter extends RecyclerView.Adapter<MyOrder_Adapter.ListViewHolder> {

    Context context;
    LayoutInflater inflater;
    ArrayList<String> MyOrderID;
    ArrayList<String> MyOrderName;
    ArrayList<String> MyOrderDescription;
    ArrayList<String> MyOrderDate;



    public MyOrder_Adapter(Context context, ArrayList<String> id, ArrayList<String> name, ArrayList<String> description, ArrayList<String> date) {
        super();
        this.context = context;
        this.MyOrderID = id;
        this.MyOrderName =  name;
        this.MyOrderDescription =  description;
        this.MyOrderDate=date;
        inflater = LayoutInflater.from(context);


    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.myorder_listview, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, final int position) {

        holder.textViewid.setText(MyOrderID.get(position));
        holder.tv_name.setText(MyOrderName.get(position));
        holder.textViewdescription.setText(MyOrderDescription.get(position));
        holder.textViewdate.setText(MyOrderDate.get(position));
        holder.textViewdescription.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               View alertLayout = inflater.inflate(R.layout.myorder_dialog, null);
               final TextView textViewtitle = (TextView)alertLayout .findViewById(R.id.textViewtitle);
               final TextView textViewdescription = (TextView) alertLayout .findViewById(R.id.textViewdescription);
               textViewtitle.setText(MyOrderName.get(position));
               textViewdescription.setText(MyOrderDescription.get(position));
               AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
               alertDialogBuilder.setTitle("Order No:"+MyOrderID.get(position));
               // this is set the view from XML inside AlertDialog
               alertDialogBuilder.setView(alertLayout);
               alertDialogBuilder.setPositiveButton("Close",
                       new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface arg0, int arg1) {
                           }
                       });
               AlertDialog alertDialog = alertDialogBuilder.create();
               alertDialog.show();

           }
       });
        animate(holder);
    }

    @Override
    public int getItemCount() {
        return MyOrderID.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView textViewid,tv_name,textViewdescription,textViewdate;

        public ListViewHolder(View itemView) {
            super(itemView);
            textViewid = (TextView) itemView.findViewById(R.id.id);
            tv_name = (TextView) itemView.findViewById(R.id.label);
            textViewdescription= (TextView) itemView.findViewById(R.id.description);
            textViewdate= (TextView) itemView.findViewById(R.id.date);

        }
    }
    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
}
