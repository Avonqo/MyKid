package za.co.codetribe.kid.profile;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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



public class ProfileActivity extends AppCompatActivity {

    EditText name, surname, address, gender, parentName, parentContact, dateofbirth, email, password;
    String nam, surnam, addres, gende, parentNam, parentContac, dateofbirt, emai, passwor;
    Button save;
    Button view;

    DatabaseReference roofdef, demodef;
    ListView listview;
    List<Learners> learnersList;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        roofdef = FirebaseDatabase.getInstance().getReference();

        demodef = roofdef.child("Leaners");

        firebaseAuth =FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()==null)
        {

            Intent intent=new Intent(ProfileActivity.this,HomeActivity.class);
            startActivity(intent);
        }

        FirebaseUser user=firebaseAuth.getCurrentUser();


        save = (Button) findViewById(R.id.saveData);
        view= (Button) findViewById(R.id.view);

        name = (EditText) findViewById(R.id.editname);
        surname = (EditText) findViewById(R.id.editsurname);
        email = (EditText) findViewById(R.id.editEmail);
        password = (EditText) findViewById(R.id.editPassword);
        address = (EditText) findViewById(R.id.editaddress);
        gender = (EditText) findViewById(R.id.editgender);
        parentName = (EditText) findViewById(R.id.editParentN);
        parentContact = (EditText) findViewById(R.id.editParentC);
        dateofbirth = (EditText) findViewById(R.id.editdate);


        if(user!= null) {
            DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Leaners").child(user.getUid());
            database.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.i("results" ,dataSnapshot.toString());
                    Learners learners=dataSnapshot.getValue(Learners.class);
                    name.setText(learners.getName());
                    surname.setText(learners.getSurname());
                    address.setText(learners.getAddress());
                    gender.setText(learners.getGender());
                    parentName.setText(learners.getParentName());
                    parentContact.setText(learners.getParentContants());
                    dateofbirth.setText(learners.getDateofbith());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addLearners();
            }


        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // retreive();

               Intent intent = new Intent(ProfileActivity.this,ViewProfileActivity.class);
                startActivity(intent);
            }
        });






    }



//to save extra info using current user id

    private void addLearners()
    {
        nam = name.getText().toString().trim();
        surnam = surname.getText().toString().trim();
        addres = address.getText().toString();
        gende = gender.getText().toString();
        parentNam = parentName.getText().toString();
        parentContac = parentContact.getText().toString();
        dateofbirt = dateofbirth.getText().toString();

        Learners learners = new Learners(nam,surnam,addres,gende,parentNam,parentContac,dateofbirt);


        FirebaseUser user= firebaseAuth.getCurrentUser();
        demodef.child(user.getUid()).setValue(learners);

            Toast.makeText(ProfileActivity.this, "data saved ", Toast.LENGTH_LONG).show();


    }


    //fetch


    public void fetch(DataSnapshot dataSnapshot) {

        learnersList.clear();
        for (DataSnapshot learnSnap : dataSnapshot.getChildren()) {
            Learners learners = learnSnap.getValue(Learners.class);
            learnersList.add(learners);
        }

    }

    private void retreive() {
        demodef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               learnersList.clear();

                LearnersAdapter learnersAdapter = new LearnersAdapter(ProfileActivity.this,learnersList);
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
    //display
    public void display()
    {
        Dialog d= new Dialog(this);
        d.setTitle("saved to firebase");
        d.setContentView(R.layout.learnerslist);

        name = (EditText)findViewById(R.id.editname);
        surname = (EditText) findViewById(R.id.editsurname);



    }



}




