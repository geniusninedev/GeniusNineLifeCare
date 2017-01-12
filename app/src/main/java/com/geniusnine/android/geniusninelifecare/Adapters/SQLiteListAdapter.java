package com.geniusnine.android.geniusninelifecare.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.R;

import java.util.ArrayList;

public class SQLiteListAdapter extends BaseAdapter implements Filterable {
    private SQLiteDatabase db;
    DBHelper dbHelper;

    private Cursor c;
    Context context;

    ArrayList<String> userID;
    ArrayList<String> UserName;
    ArrayList<String> Description;
    private static FriendFilter friendFilter;
    

    public SQLiteListAdapter(
    		Context context2,
    		ArrayList<String> id,
    		ArrayList<String> name,
            ArrayList<String> description
    		//ArrayList<String> subject
    		) 
    {
        	
    	this.context = context2;
        this.userID = id;
        this.UserName = name;
        this.Description=description;
       // this.User_PhoneNumber = phone;
       // this.UserSubject = subject ;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return userID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }


    public View getView(int position, View child, ViewGroup parent) {
    	
        final Holder holder;
        
        LayoutInflater layoutInflater;
        
        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.activity_listview, null);

            holder = new Holder();

            holder.textviewid = (TextView) child.findViewById(R.id.id);
            holder.textviewname = (TextView) child.findViewById(R.id.label);
            holder.textviewdescription = (TextView) child.findViewById(R.id.textViewdescription);
            // final String textid=holder.textviewid.toString().trim();
           // holder.edit = (Button) child.findViewById(R.id.buttonedit);
            //holder.delete = (Button) child.findViewById(R.id.buttondelete);

           /*holder.edit.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setMessage("Are you sure you want edit this friend?");
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                           // Toast.makeText(context, "Id", Toast.LENGTH_LONG).show();
                            String id = holder.textviewid.getText().toString().trim();
                            Intent i1=new Intent(context,edit.class);
                            i1.putExtra("id",id);
                            context.startActivity(i1);


                        }
                    });
                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });


                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
           });

            holder.delete.setOnClickListener(
                    new View.OnClickListener() {
                                                 public void onClick(View v) {


                                                     AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                                     alertDialogBuilder.setMessage("Are you sure you want delete this person?");
                                                     alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                         @Override
                                                         public void onClick(DialogInterface arg0, int arg1) {
                                                             Toast.makeText(context, "Id", Toast.LENGTH_LONG).show();
                                                             String id = holder.textviewid.getText().toString().trim();
                                                             Toast.makeText(context, "Id" + id, Toast.LENGTH_LONG).show();
                                                             String sql = "DELETE FROM addbuddies WHERE id="+id+"";
                                                             db.execSQL(sql);

                                                             Toast.makeText(context, "Record Deleted", Toast.LENGTH_LONG).show();
                                                             c = db.rawQuery(SELECT_SQL, null);
                                                         }
                                                     }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                         @Override
                                                         public void onClick(DialogInterface arg0, int arg1) {

                                                         }
                                                     });


                                                     AlertDialog alertDialog = alertDialogBuilder.create();
                                                     alertDialog.show();

                                                 }
                                             });
*/
                    child.setTag(holder);


        } else {
        	
        	holder = (Holder) child.getTag();
        }
        holder.textviewid.setText(userID.get(position));
        holder.textviewname.setText(UserName.get(position));
        holder.textviewdescription.setText(Description.get(position));



        return child;
    }

    @Override
    public Filter getFilter() {
        if (friendFilter == null)
            friendFilter = new FriendFilter();

        return friendFilter;
    }


    public class Holder {
        TextView textviewid;
        TextView textviewname;
        TextView textviewdescription;

    }

    private class FriendFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list

                results.values = userID;
                results.values = UserName ;
                results.values=Description;
                results.count = userID.size();
                results.count = UserName.size();
                results.count=Description.size();
            }

            else {
                // We perform filtering operation
                ArrayList<String> nPlanetList = new ArrayList<String>();
                for (String p : userID) {
                    Log.v("String", p);
                    if (p.toLowerCase().contains(constraint.toString().toLowerCase()))
                        nPlanetList.add(p);
                    Log.e("nPlanetList", nPlanetList.toString());
                }

                results.values = nPlanetList;
                results.count = nPlanetList.size();

            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results)
        {
            userID = (ArrayList<String>) results.values;
            notifyDataSetChanged();
            UserName = (ArrayList<String>) results.values;
            notifyDataSetChanged();
            Description = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }


    }

}

