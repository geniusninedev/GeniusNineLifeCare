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

public class History_ListAdapter extends RecyclerView.Adapter<History_ListAdapter.ListViewHolder> {

    Context context;
    LayoutInflater inflater;
    ArrayList<String> appointmentID;
    ArrayList<String> appointmentregistrationdate;
    ArrayList<String> appointmentdate;
    ArrayList<String> appointmentcauses;
    ArrayList<String> appointmentstatus;
    ArrayList<String> appointmentstatuspercent;





    public History_ListAdapter(Context context2, ArrayList<String> appointmentID, ArrayList<String> appointmentregistrationdate,ArrayList<String> appointmentdate,ArrayList<String> appointmentcauses,ArrayList<String> appointmentstatus,ArrayList<String> appointmentstatuspercent) {
        super();
        this.context = context2;
        this.appointmentID = appointmentID;
        this.appointmentregistrationdate =  appointmentregistrationdate;
        this.appointmentdate =  appointmentdate;
        this.appointmentcauses =  appointmentcauses;
        this.appointmentstatus =  appointmentstatus;
        this.appointmentstatuspercent =  appointmentstatuspercent;

        //  this.listener = context;
        inflater = LayoutInflater.from(context);


    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.history_list, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {


        holder.textView_appointmentid.setText(appointmentID.get(position));
        holder.textView_appointmentregistrationdate.setText(appointmentregistrationdate.get(position));
        holder.textView_appointmentdate.setText(appointmentdate.get(position));
        holder.textView_appointmentcauses.setText(appointmentcauses.get(position));
        holder.textView_appointmentstatus.setText(appointmentstatus.get(position));
        holder.textView_appointmentstatuspercent.setText(appointmentstatuspercent.get(position));



      /*  holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // listener.nameToChnge(dataList.get((Integer) v.getTag()).name);

            }
        });*/
        animate(holder);
    }

    @Override
    public int getItemCount() {
        return appointmentID.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView textView_appointmentid,textView_appointmentregistrationdate,textView_appointmentdate,textView_appointmentcauses,textView_appointmentstatus,textView_appointmentstatuspercent;


        public ListViewHolder(View itemView) {
            super(itemView);

            textView_appointmentid = (TextView) itemView.findViewById(R.id.historyappointmentid);
            textView_appointmentregistrationdate = (TextView) itemView.findViewById(R.id.history_bookappointmentregistrationdate);
            textView_appointmentdate = (TextView) itemView.findViewById(R.id.history_bookappointmentdate);
            textView_appointmentcauses = (TextView) itemView.findViewById(R.id.history_bookappointmentcauses);
            textView_appointmentstatus = (TextView) itemView.findViewById(R.id.history_bookappointmentstatus);
            textView_appointmentstatuspercent = (TextView) itemView.findViewById(R.id.history_bookappointmentstatuspercent);


        }
    }
    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
}
