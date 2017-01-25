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

public class Labs_ImageAdapter extends RecyclerView.Adapter<Labs_ImageAdapter.ListViewHolder> {

    Context context;
    LayoutInflater inflater;
    ArrayList<String> labsID;
    ArrayList<String> labsName;
    ArrayList<String> labsDescription;
    ArrayList<String> labsDate;
    private ArrayList<Bitmap> bitmaps;
    byte[] labsimage;
    Activity activity;



    public Labs_ImageAdapter(Context context, ArrayList<String> id, ArrayList<String> name, ArrayList<String> description, ArrayList<Bitmap> bitm, ArrayList<String> date) {
        super();
        this.context = context;
        this.labsID = id;
        this.labsName =  name;
        this.labsDescription =  description;
        this.bitmaps=bitm;
        this.labsDate=date;
        // this.listener = context;
        inflater = LayoutInflater.from(context);


    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.labs_listview, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, final int position) {

        holder.textViewid.setText(labsID.get(position));
        holder.tv_name.setText(labsName.get(position));
        holder.textViewdescription.setText(labsDescription.get(position));
        holder.iv_delete.setImageBitmap(bitmaps.get(position));
        holder.textViewdate.setText(labsDate.get(position));
       holder.textViewdescription.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               View alertLayout = inflater.inflate(R.layout.labsdialog, null);
               final TextView textViewtitle = (TextView)alertLayout .findViewById(R.id.textViewtitle);
               final ImageView imageViewheltandtipsimage = (ImageView) alertLayout .findViewById(R.id.imageViewheltandtipsimage);
               final TextView textViewdescription = (TextView) alertLayout .findViewById(R.id.textViewdescription);
               textViewtitle.setText(labsName.get(position));
               imageViewheltandtipsimage.setImageBitmap(bitmaps.get(position));
               textViewdescription.setText(labsDescription.get(position));
               AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
               alertDialogBuilder.setTitle("Lab No:"+labsID.get(position));
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
        return labsID.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView textViewid,tv_name,textViewdescription,textViewdate;
        ImageView iv_delete;

        public ListViewHolder(View itemView) {
            super(itemView);
            textViewid = (TextView) itemView.findViewById(R.id.id);
            tv_name = (TextView) itemView.findViewById(R.id.label);
            textViewdescription= (TextView) itemView.findViewById(R.id.description);
            iv_delete = (ImageView) itemView.findViewById(R.id.imageViewlabs);
            textViewdate= (TextView) itemView.findViewById(R.id.date);

        }
    }
    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
}
