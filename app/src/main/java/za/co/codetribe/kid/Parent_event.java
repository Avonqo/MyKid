package za.co.codetribe.kid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Parent_event extends AppCompatActivity {
    ListView listview;
    List<Event> eventList;
    String desc;
    DatabaseReference roofdef, demodef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_p);

        listview = (ListView) findViewById(R.id.listView);

        eventList = new ArrayList<>();
        roofdef = FirebaseDatabase.getInstance().getReference();

        demodef = roofdef.child("Events");
    }

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

                ParentsEvent adapter = new ParentsEvent(Parent_event.this, eventList);
                listview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
