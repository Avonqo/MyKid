package za.co.codetribe.kid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView events,notification,gallary,activities,aboutUs,inquiries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        events=(ImageView)findViewById(R.id.imageEvents);
        aboutUs=(ImageView)findViewById(R.id.imageAboutUs);
        activities=(ImageView)findViewById(R.id.imageActivities);
        gallary=(ImageView)findViewById(R.id.imageGallery);
        inquiries=(ImageView)findViewById(R.id.imageInquiries);
        notification=(ImageView)findViewById(R.id.imageNotification);


        events.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this,Eventhelper.class);
        startActivity(intent);

    }
});
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EventsActivity.class);
                startActivity(intent);

            }
        });
        gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GallaryActivity.class);
                startActivity(intent);

            }
        });
        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivitiesActivity.class);
                startActivity(intent);

            }
        });
        inquiries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,InquriesActivity.class);
                startActivity(intent);

            }
        });
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);

            }
        });

    }
}
