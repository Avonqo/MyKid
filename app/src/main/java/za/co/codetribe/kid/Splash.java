package za.co.codetribe.kid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
Button Parent,School;
    ImageView aboutUs,inquiries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Parent = (Button)findViewById(R.id.button2);
        School = (Button)findViewById(R.id.button3);
        aboutUs=(ImageView)findViewById(R.id.imageAboutUs);
        inquiries=(ImageView)findViewById(R.id.imageInquiries);

        inquiries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Splash.this,InquriesActivity.class);
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

  public void parent(View view){
      Intent i = new Intent(Splash.this,HomeParent.class);
      startActivity(i);
  }

    public void school(View view){
        Intent i = new Intent(Splash.this,MainActivity.class);
        startActivity(i);
    }

}
