package com.geniusnine.android.geniusninelifecare.Adapter;


import android.content.Context;
import android.graphics.Bitmap;
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

public class Health_Tips_ImageAdapter extends RecyclerView.Adapter<Health_Tips_ImageAdapter.ListViewHolder> {

    Context context;
    LayoutInflater inflater;
    ArrayList<String> health_and_tipsID;
    ArrayList<String> health_and_tipsName;
    ArrayList<String> health_and_tipsDescription;
    private ArrayList<Bitmap> bitmaps;
    byte[] health_and_tipsimage;



    public Health_Tips_ImageAdapter(Context context2, ArrayList<String> id, ArrayList<String> name,ArrayList<String> description, ArrayList<Bitmap> bitm) {
        super();
        this.context = context2;
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
    public void onBindViewHolder(ListViewHolder holder, int position) {

        holder.textViewid.setText(health_and_tipsID.get(position));
        holder.tv_name.setText(health_and_tipsName.get(position));
        holder.textViewdescription.setText(health_and_tipsDescription.get(position));
        holder.iv_delete.setImageBitmap(bitmaps.get(position));

        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // listener.nameToChnge(dataList.get((Integer) v.getTag()).name);

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
