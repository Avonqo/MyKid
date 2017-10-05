package za.co.codetribe.kid.gallery;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import za.co.codetribe.kid.R;

public class ImageDisplayActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    List<ImagePojo> imgList;
 ListView listView;

    ProgressDialog pd;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);

    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallarylist);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listView=(ListView) findViewById(R.id.listImages);

        imgList =new ArrayList<>();

        pd =new ProgressDialog(this);
        pd.setMessage(" please wait ....");
        pd.show();

        databaseReference= FirebaseDatabase.getInstance().getReference(GallaryActivity.Database_Path);
       databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pd.dismiss();

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Log.i(" AVIWE",dataSnapshot.toString());
                    ImagePojo imagePojo =(ImagePojo) dataSnapshot1.getValue(ImagePojo.class);
                    imgList.add(imagePojo);

                }





               ImageAdapter adapter = new ImageAdapter(ImageDisplayActivity.this,R.layout.activity_gallarylist,imgList);
                listView.setAdapter(adapter);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                pd.dismiss();

            }
        });




    }


}
