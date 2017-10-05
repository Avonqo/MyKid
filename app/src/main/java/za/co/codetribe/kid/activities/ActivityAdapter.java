package za.co.codetribe.kid.activities;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import za.co.codetribe.kid.R;

import static za.co.codetribe.kid.R.layout.activity_activity;


public class ActivityAdapter extends ArrayAdapter<Activity1> {



        private Activity context;
        private List<Activity1> activityList;
        int resource;

        public ActivityAdapter(Activity context, int resource, List<Activity1> activityList) {
            super(context, activity_activity,activityList);
            this.context = context;
            this.activityList=activityList;
            this.resource=resource;
        }



    @Override
        public int getCount()
        {
            return activityList.size();
        }


        @Override
        public Activity1 getItem(int position)
        {
            return activityList.get(position);
        }
        @Override
        public long getItemId(int position)
        {
            return position;
        }




        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

//            if (convertView ==null)
//            {
                //convertView=  LayoutInflater.from(context).inflate(R.layout.activity_customeventlist,parent,false);
               // convertView=  LayoutInflater.from(context).inflate(resource,parent,false);


//            LayoutInflater layoutInflater=context.getLayoutInflater();
//            View v=layoutInflater.inflate(resource,null);
            convertView=  LayoutInflater.from(context).inflate(R.layout.imageitems,parent,false);


            ImageView images=(ImageView)convertView.findViewById(R.id.images);
            EditText connemt=(EditText) convertView.findViewById(R.id.activityComment);

            final Activity1 activity1=this.getItem(position);


            Glide.with(context).load(activityList.get(position).getUrl1()).into(images);
//            Glide.with(context).load(activityList.get(position).getUrl2()).into(images);
            connemt.setText(activity1.getComment());


            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,activity1.getComment(),Toast.LENGTH_LONG).show();
                }
            });

            return convertView;
        }
    }


