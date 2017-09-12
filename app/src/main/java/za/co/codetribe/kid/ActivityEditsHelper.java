package za.co.codetribe.kid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Comment;

import static android.R.attr.name;
import static za.co.codetribe.kid.R.id.uploadImage;

public class ActivityEditsHelper extends AppCompatActivity {

    private static final int ACTION_CODE = 1;
    private StorageReference storageReference;
    DatabaseReference databaseReference;


    ImageButton imageBView1;
    ImageButton imageBView2;
    EditText comment;
    Button addActivity;
    Uri imageUri1;
    Uri imageUri2;


    public static final String Storage_Path ="images/";
    public static final String Database_Path ="images";

    public static final int Request_code =1234;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        comment=(EditText)findViewById(R.id.activityComment);
        imageBView1 = (ImageButton) findViewById(R.id.imgbImage1);
        imageBView2 = (ImageButton) findViewById(R.id.imgbImage2);
      addActivity = (Button) findViewById(R.id.saveData);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        storageReference = FirebaseStorage.getInstance().getReference();
        
        imageBView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityIntent = new Intent(Intent.ACTION_GET_CONTENT);
                activityIntent.setType("images/*");
                startActivityForResult(activityIntent, ACTION_CODE);
            }
        });

        imageBView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityIntent = new Intent(Intent.ACTION_GET_CONTENT);
                activityIntent.setType("images/*");
                startActivityForResult(activityIntent, ACTION_CODE);
            }
        });

        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTION_CODE && resultCode == RESULT_OK){
            imageUri1 = data.getData();
            imageBView1.setImageURI(imageUri1);

        }
        else if (requestCode== ACTION_CODE && requestCode ==RESULT_OK)
        {
            imageUri1 = data.getData();
            imageBView2.setImageURI(imageUri1);
        }
    }


    public void uploadImage(){

        final String activityComment = comment.getText().toString().trim();

        if (!TextUtils.isEmpty(activityComment)){

            StorageReference filePath = storageReference.child("activities").child(imageUri1.getLastPathSegment());
            filePath.putFile(imageUri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") Uri uriImage = taskSnapshot.getDownloadUrl();


                    Activity1 activity1 = new Activity1();

                    activity1.setComment(activityComment);
                    activity1.setUrl1(uriImage.toString());


//                    activity1.setUrl2(uriImage.toString());


                    databaseReference.child("activity").push().setValue(activity1);


                }
            });

        }
        else       if (!TextUtils.isEmpty(activityComment)){

            StorageReference filePath = storageReference.child("activities").child(imageUri1.getLastPathSegment());
            filePath.putFile(imageUri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") Uri uriImage = taskSnapshot.getDownloadUrl();


                    Activity1 activity1 = new Activity1();
//
//                    activity1.setComment(activityComment);
//                    activity1.setUrl1(uriImage.toString());


                    activity1.setUrl2(uriImage.toString());


                    databaseReference.child("activity").push().setValue(activity1);


                }
            });

        }

    }



}
