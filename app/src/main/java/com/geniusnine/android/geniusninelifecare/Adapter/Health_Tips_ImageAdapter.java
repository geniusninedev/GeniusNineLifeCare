package com.geniusnine.android.geniusninelifecare.Adapter;


import android.app.Activity;
import android.app.Dialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geniusnine.android.geniusninelifecare.R;

import java.util.ArrayList;

/**
 * Created by Dev on 18-01-2017.
 */

public class Health_Tips_ImageAdapter extends RecyclerView.Adapter<Health_Tips_ImageAdapter.ListViewHolder> {

    Context context;
    LayoutInflater inflater;
    ArrayList<String> health_and_tipsID;
    ArrayList<String> health_and_tipsName;
    ArrayList<String> health_and_tipsDescription;
    private ArrayList<Bitmap> bitmaps;
    byte[] health_and_tipsimage;
    Activity activity;



    public Health_Tips_ImageAdapter(Context context, ArrayList<String> id, ArrayList<String> name,ArrayList<String> description, ArrayList<Bitmap> bitm) {
        super();
        this.context = context;
        this.health_and_tipsID = id;
        this.health_and_tipsName =  name;
        this.health_and_tipsDescription =  description;
        this.bitmaps=bitm;
        // this.listener = context;
        inflater = LayoutInflater.from(context);


    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.health_tips_listview, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, final int position) {

        holder.textViewid.setText(health_and_tipsID.get(position));
        holder.tv_name.setText(health_and_tipsName.get(position));
        holder.textViewdescription.setText(health_and_tipsDescription.get(position));
        holder.iv_delete.setImageBitmap(bitmaps.get(position));
       holder.textViewdescription.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               View alertLayout = inflater.inflate(R.layout.dialog, null);
               final TextView textViewtitle = (TextView)alertLayout .findViewById(R.id.textViewtitle);
               final ImageView imageViewheltandtipsimage = (ImageView) alertLayout .findViewById(R.id.imageViewheltandtipsimage);
               final TextView textViewdescription = (TextView) alertLayout .findViewById(R.id.textViewdescription);
               textViewtitle.setText(health_and_tipsName.get(position));
               imageViewheltandtipsimage.setImageBitmap(bitmaps.get(position));
               textViewdescription.setText(health_and_tipsDescription.get(position));
               AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
               alertDialogBuilder.setTitle("Tip No:"+health_and_tipsID.get(position));
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
        return health_and_tipsID.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView textViewid,tv_name,textViewdescription;
        ImageView iv_delete;

        public ListViewHolder(View itemView) {
            super(itemView);
            textViewid = (TextView) itemView.findViewById(R.id.id);
            tv_name = (TextView) itemView.findViewById(R.id.label);
            textViewdescription= (TextView) itemView.findViewById(R.id.description);
            iv_delete = (ImageView) itemView.findViewById(R.id.imageViewhealth_and_tips);

        }
    }
    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
}
