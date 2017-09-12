package za.co.codetribe.kid;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

import static android.R.attr.start;
import static java.sql.Types.DOUBLE;
import static za.co.codetribe.kid.R.drawable.gallery;
import static za.co.codetribe.kid.R.id.uploadImage;

public class GallaryActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_gallary);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        imageName = (EditText) findViewById(R.id.txtImageName);
        imageBView = (ImageButton) findViewById(R.id.imgbImage);
        btnUploadImage = (Button) findViewById(uploadImage);

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

        final String name = imageName.getText().toString().trim();

        if (!TextUtils.isEmpty(name)){

            StorageReference filePath = storageReference.child("imagess").child(imageUri.getLastPathSegment());
            filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") Uri uriImage = taskSnapshot.getDownloadUrl();


                    ImagePojo image = new ImagePojo();
                    image.setName(name);
                    image.setUrl(uriImage.toString());
                    databaseReference.child("image").push().setValue(image);


                }
            });

        }

    }

    public void show(View view)
    {
        Intent intent =new Intent(GallaryActivity.this,ImageDisplayActivity.class);
        startActivity(intent);
    }

}
