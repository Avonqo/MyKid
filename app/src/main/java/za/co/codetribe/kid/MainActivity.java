package za.co.codetribe.kid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import za.co.codetribe.kid.profile.admin.ForgetActivity;
import za.co.codetribe.kid.profile.admin.Register;


public class MainActivity extends AppCompatActivity {

    TextView text;
    EditText edtemail;
    EditText edtpassword;

    private FirebaseAuth firebase;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        progressDialog = new ProgressDialog(this);

        edtemail = (EditText) findViewById(R.id.edtEmail);
        edtpassword = (EditText) findViewById(R.id.edtPassword);
        text = (TextView) findViewById(R.id.txtSign);

        firebase = FirebaseAuth.getInstance();
        if(firebase.getCurrentUser()==null)
        {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }

    }

    public void login(View view) {
        loginUser();
    }

    public void loginUser() {
        String email = edtemail.getText().toString().trim();
        String password = edtemail.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter your email  ", Toast.LENGTH_LONG).show();

            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter your password  ", Toast.LENGTH_LONG).show();
            return;
        }
//        progressDialog.setMessage("Registering user......");
//        progressDialog.show();

        firebase.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "User not successful registered...please try again ", Toast.LENGTH_LONG).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error " + e.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }


    public void register(View view) {
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
    }

    public void passwordForget(View view) {
        Intent intent = new Intent(this, ForgetActivity.class);
        startActivity(intent);
    }
    public void loginAdmin(View view) {
        Intent intent = new Intent(this, ForgetActivity.class);
        startActivity(intent);
    }
}
