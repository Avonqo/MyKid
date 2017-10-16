package za.co.codetribe.kid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import za.co.codetribe.kid.gallery.GallaryActivityParent;
import za.co.codetribe.kid.notifications.Parent_event;
import za.co.codetribe.kid.profile.ProfileActivity;

public class HomeParent extends AppCompatActivity {
    ImageView events, notification, gallary, activities,profile;
    TextView parentg,aboutUs,inquiries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_parent);

//        events=(ImageView)findViewById(R.id.imageEvents);
        aboutUs=(TextView)findViewById(R.id.imageAboutUs);
        activities = (ImageView) findViewById(R.id.imageActivities);
        gallary = (ImageView) findViewById(R.id.imageGallery);
        inquiries=(TextView)findViewById(R.id.imageInquiries);
        notification = (ImageView) findViewById(R.id.imageEvents);
//        parentg=(TextView) findViewById(R.id.parentGallery);
        profile = (ImageView) findViewById(R.id.imageProfile);

//        events.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,Eventhelper.class);
//                startActivity(intent);
//
//            }
//        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeParent.this, Parent_event.class);
                startActivity(intent);

            }
        });
        gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeParent.this, GallaryActivityParent.class);
                startActivity(intent);

            }
        });
        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeParent.this, tableLayout.class);
                startActivity(intent);

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeParent.this, ProfileActivity.class);
                startActivity(intent);

            }
        });

        inquiries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeParent.this,InquriesActivity.class);
                startActivity(intent);

            }
        });
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,AboutActivity.class);
//                startActivity(intent);

                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("https://www.google.co.za/maps/place/The+Innovation+Hub/@-25.7288732,28.2602829,11.5z/data=!4m5!3m4!1s0x1e956044ef7e34af:0x74cb1ddbc5cd8e9e!8m2!3d-25.7487047!4d28.2680154"));
                startActivity(intent1);

            }
        });

    }
//    public void parentg(View view){
//        Intent intent = new Intent(MainActivity.this,GallaryActivityParent.class);
////        startActivity(intent);
//    }
}

