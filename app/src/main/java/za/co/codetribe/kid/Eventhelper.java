package za.co.codetribe.kid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Codetribe on 2017/09/01.
 */


public class Eventhelper extends AppCompatActivity {

    EditText Ename, Edescription;
    String eName, eDescription;
    Button save;
    Boolean val = true;


    FirebaseDatabase firebaseData;
    DatabaseReference roofdef, demodef;
    ListView listview;
    List<Event> eventList;


    //ProfileActivityhelpers helper;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listview = (ListView) findViewById(R.id.listView);


        save = (Button) findViewById(R.id.saveData);

        eventList = new ArrayList<>();

        Ename = (EditText) findViewById(R.id.eventName);
        Edescription = (EditText) findViewById(R.id.eventDescription);

        roofdef = FirebaseDatabase.getInstance().getReference();

        demodef = roofdef.child("Events");


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
            }
        });

    }

    private void addEvent() {
        Event event = new Event();
        eName = Ename.getText().toString().trim();
        eDescription = Edescription.getText().toString().trim();


        if (!TextUtils.isEmpty(eName)) {
            event.setEventName(eName);


        } else {
            Toast.makeText(Eventhelper.this, "Event not saved ", Toast.LENGTH_LONG).show();
        }
        if (!TextUtils.isEmpty(eDescription)) {
            event.setEventDiscription(eDescription);


        } else {
            Toast.makeText(Eventhelper.this, "no desc", Toast.LENGTH_LONG).show();
        }
        Event eve = new Event(eName, eDescription);
        demodef.push().setValue(eve);

        Ename.setText(" ");
        Edescription.setText(" ");
        try {


        } catch (Exception e) {
            val = false;
        } finally {
            if (val) {
                Toast.makeText(Eventhelper.this, "Event saved ", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Eventhelper.this, "Event not saved ", Toast.LENGTH_LONG).show();
            }
        }

    }
    //fetch

    @Override
    protected void onStart() {
        super.onStart();
        demodef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                eventList.clear();

                Log.i(" f=====================", dataSnapshot.toString());

                for (DataSnapshot eventsShot : dataSnapshot.getChildren()) {
                    Log.i(" AVIWE", eventsShot.toString());
                    Event event = eventsShot.getValue(Event.class);
                    eventList.add(event);

                }

                EventAdapter adapter = new EventAdapter(Eventhelper.this, eventList);
                listview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void showUpdateDIALOG(String id, String eventName, String eventDescription) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText Ename = (EditText) dialogView.findViewById(R.id.eventName);
        final EditText Edescription = (EditText) dialogView.findViewById(R.id.eventDescription);
        final Button update = (Button) dialogView.findViewById(R.id.update);

        dialogBuilder.setTitle("Update Event" + id);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


    }


}






