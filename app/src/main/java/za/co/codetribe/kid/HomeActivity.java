package za.co.codetribe.kid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import za.co.codetribe.kid.Notifications.Eventhelper;
import za.co.codetribe.kid.Notifications.EventsActivity;
import za.co.codetribe.kid.aboutUs.AboutActivity;
import za.co.codetribe.kid.activities.ActivitiesActivity;
import za.co.codetribe.kid.gallery.GallaryActivity;
import za.co.codetribe.kid.profile.ProfileActivity;
import za.co.codetribe.kid.profile.ViewProfileActivity;

public class HomeActivity extends AppCompatActivity {
    ImageView events,notification,gallary,activities,aboutUs,inquiries,profile;

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
        profile =(ImageView)findViewById(R.id.imageProfile);


        events.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(HomeActivity.this,Eventhelper.class);
        startActivity(intent);

    }
});
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,EventsActivity.class);
                startActivity(intent);

            }
        });
        gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,GallaryActivity.class);
                startActivity(intent);

            }
        });
        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ActivitiesActivity.class);
                startActivity(intent);

            }
        });
        inquiries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,InquriesActivity.class);
                startActivity(intent);

            }
        });
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,AboutActivity.class);
                startActivity(intent);

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(HomeActivity.this, profile);


                popupMenu.getMenuInflater().inflate(R.menu.navigation,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {


                        if (item.getTitle().equals("view profile"))
                        {
                            Intent intent = new Intent(HomeActivity.this, ViewProfileActivity.class);
                            startActivity(intent);
                        }
                        else if(item.getTitle().equals("edit profile"))
                        {
                            //showUpdateDialog();
                            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        }
//                        Toast.makeText(HomeActivity.this, "click" + item.getTitle(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }
}
