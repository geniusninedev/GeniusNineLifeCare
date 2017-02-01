package com.geniusnine.android.geniusninelifecare.Adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.geniusnine.android.geniusninelifecare.Activitys.DoctorListActivity;
import com.geniusnine.android.geniusninelifecare.R;
import java.util.ArrayList;

/**
 * Created by Dev on 18-01-2017.
 */

public class ImageAdapter  extends RecyclerView.Adapter<ImageAdapter.ListViewHolder> {

    Context context;
    LayoutInflater inflater;
    ArrayList<String> categoryID;
    ArrayList<String> categoryName;
    private ArrayList<Bitmap> bitmaps;
    byte[] categoryimage;



    public ImageAdapter( Context context2, ArrayList<String> id, ArrayList<String> name, ArrayList<Bitmap> bitm) {
        super();
        this.context = context2;
        this.categoryID = id;
        this.categoryName =  name;
        this.bitmaps=bitm;
        // this.listener = context;
        inflater = LayoutInflater.from(context);


    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.activity_gridview, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {


        holder.tv_name.setText(categoryName.get(position));
        holder.textViewid.setText(categoryID.get(position));
        holder.iv_delete.setImageBitmap(bitmaps.get(position));

        final String stringCategory=holder.tv_name.getText().toString().trim();
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i1=new Intent(context, DoctorListActivity.class);
                i1.putExtra("CategoryName",stringCategory);
                context.startActivity(i1);
            }
        });
        animate(holder);
    }

    @Override
    public int getItemCount() {
        return categoryID.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,textViewid;
        ImageView iv_delete;

        public ListViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.label);
            textViewid = (TextView) itemView.findViewById(R.id.id);
            iv_delete = (ImageView) itemView.findViewById(R.id.imageViewcategory);

        }
    }
    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
}
