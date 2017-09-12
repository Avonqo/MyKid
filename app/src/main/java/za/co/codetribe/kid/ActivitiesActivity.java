package za.co.codetribe.kid;

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
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static za.co.codetribe.kid.R.id.saveData;
import static za.co.codetribe.kid.R.id.uploadImage;









    public class ActivitiesActivity extends AppCompatActivity {

        private static final int ACTION_CODE = 1;
        private StorageReference storageReference;
        DatabaseReference databaseReference;

        ImageButton imageBView;
        EditText imageName;
        Button btnUploadImage;
        Uri imageUri;

        public static final String Storage_Path ="image/";
        public static final String Database_Path ="image";

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



            imageName = (EditText) findViewById(R.id.activityComment);
            imageBView = (ImageButton) findViewById(R.id.imgbImage2);
            btnUploadImage = (Button) findViewById(saveData);

            databaseReference = FirebaseDatabase.getInstance().getReference();

            storageReference = FirebaseStorage.getInstance().getReference();

            imageBView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    galleryIntent.setType("image/*");
                    startActivityForResult(galleryIntent, ACTION_CODE);
                }
            });

            btnUploadImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),"you are clicked",Toast.LENGTH_LONG).show();
                    uploadImage();
                }
            });

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == ACTION_CODE && resultCode == RESULT_OK){
                imageUri = data.getData();
                imageBView.setImageURI(imageUri);
            }
        }

        public void uploadImage(){

            final String comment = imageName.getText().toString().trim();

            if (!TextUtils.isEmpty(comment)){

                StorageReference filePath = storageReference.child("pictures").child(imageUri.getLastPathSegment());
                filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        @SuppressWarnings("VisibleForTests") Uri uriImage = taskSnapshot.getDownloadUrl();


                        Activity1 activity1 = new Activity1();
                        activity1.setComment(comment);
                        activity1.setUrl1(uriImage.toString());
                        databaseReference.child("picture").push().setValue(activity1);



                    }
                });

            }

        }


    }







