package za.co.codetribe.kid.profile.teachers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import za.co.codetribe.kid.HomeActivity;
import za.co.codetribe.kid.R;


public class ViewProfileActivity extends AppCompatActivity {

    Context context;
    TextView name, surname, address, gender, parentName, parentContact, dateofbirth;

    DatabaseReference roofdef, demodef;
    ListView listview;
    List<Teachers> learnersList;
    Button view;
    private FirebaseAuth firebaseAuth;



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = getBaseContext();

        roofdef = FirebaseDatabase.getInstance().getReference("learn");

        demodef = roofdef.child("Leaners");

        final DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("Admin");






        view = (Button) findViewById(R.id.view);

        final ImageView profilePic=(ImageView)findViewById(R.id.imageView);
        name = (TextView) findViewById(R.id.editname);
        surname = (TextView) findViewById(R.id.editsurname);
        address = (TextView) findViewById(R.id.editaddress);
        gender = (TextView) findViewById(R.id.editgender);
        parentName = (TextView) findViewById(R.id.editParentN);
        parentContact = (TextView) findViewById(R.id.editParentC);
        dateofbirth = (TextView) findViewById(R.id.editdate);


        firebaseAuth= FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()==null)
        {

            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }

        FirebaseUser user=firebaseAuth.getCurrentUser();

        if(user == null)
        {
            Toast.makeText(getApplicationContext()," no data ",Toast.LENGTH_LONG).show();
        }
          else {
            if (user != null) {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Leaners").child(user.getUid());
                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.i("results", dataSnapshot.toString());
                        Teachers learners = dataSnapshot.getValue(Teachers.class);
                        name.setText(learners.getName());
                        surname.setText(learners.getSurname());
                        address.setText(learners.getAddress());
                        gender.setText(learners.getGender());
                        parentName.setText(learners.getParentName());
                        parentContact.setText(learners.getParentContants());
                        dateofbirth.setText(learners.getDateofbith());

                        Glide.with(context).load(learners.getUrl()).into(profilePic);
                        //profilePic.setImageURI(Uri.parse(learners.getUrl()));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }





    //name.setText("Leraners name; " + user.);


   // textViewUserEmail.setText("Welcun :   " + user.getEmail());


    }




    public void fetch(DataSnapshot dataSnapshot) {

        learnersList.clear();
        for (DataSnapshot learnSnap : dataSnapshot.getChildren()) {
            Teachers learners = learnSnap.getValue(Teachers.class);
            learnersList.add(learners);
        }

    }

    private void retreive() {
        demodef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               // fetch(dataSnapshot);
                learnersList.clear();

                TeachersAdapter learnersAdapter = new TeachersAdapter(ViewProfileActivity.this,learnersList);
                listview.setAdapter(learnersAdapter);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetch(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




}






