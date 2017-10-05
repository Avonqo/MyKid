package za.co.codetribe.kid.profile;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import za.co.codetribe.kid.R;

/**
 * Created by Codetribe on 2017/08/30.
 */

public class LearnersAdapter extends ArrayAdapter<Learners> {



    private Activity context;
    private List<Learners> learnersList;

    public LearnersAdapter(Activity context, List<Learners> learnersList) {
        super(context, R.layout.learnerslist,learnersList);
        this.context = context;
        this.learnersList=learnersList;
    }



    @Override
    public int getCount()
    {
        return learnersList.size();
    }


    @Override
    public Learners getItem(int position)
    {
        return learnersList.get(position);
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }




    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView ==null)
        {
            convertView=  LayoutInflater.from(context).inflate(R.layout.learnerslist,parent,false);

        }


        TextView name=(TextView)convertView.findViewById(R.id.name);
//        TextView surname=(TextView)convertView.findViewById(R.id.surname);

       final Learners learners=(Learners)this.getItem(position);

        name.setText(learners.getName());
//        surname.setText(learners.getSurname());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,learners.getName(),Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }
}