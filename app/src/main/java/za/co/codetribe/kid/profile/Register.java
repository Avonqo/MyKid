package za.co.codetribe.kid.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import za.co.codetribe.kid.HomeActivity;
import za.co.codetribe.kid.R;

public class Register extends AppCompatActivity {




    Button registerButton;
    TextView text;
    EditText edtemail;
    EditText edtpassword;

    private FirebaseAuth firebase;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);

        progressDialog=new ProgressDialog(this);

            registerButton =(Button)findViewById(R.id.btnRegister);
        edtemail=(EditText)findViewById(R.id.edtEmail);
        edtpassword=(EditText)findViewById(R.id.edtPassword);
        text=(TextView) findViewById(R.id.txtSign);

        firebase=FirebaseAuth.getInstance();





    }

    public void save(View view) {
        registerUser();
        Intent intent = new Intent(Register.this, HomeActivity.class);
        startActivity(intent);
    }

    public void registerUser()
    {
        String email=edtemail.getText().toString().trim();
        String password=edtemail.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
          Toast.makeText( getApplicationContext(),"Please enter your email  ", Toast.LENGTH_LONG).show();

            return;
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText( getApplicationContext(),"Please enter your password  ", Toast.LENGTH_LONG).show();
            return;
        }
//        progressDialog.setMessage("Registering user......");
//        progressDialog.show();

        firebase.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful())
                {
                    Toast.makeText( getApplicationContext(),"User successful registered ", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText( getApplicationContext(),"User not successful registered...please try again ", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
