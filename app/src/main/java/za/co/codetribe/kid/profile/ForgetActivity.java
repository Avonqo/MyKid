package za.co.codetribe.kid.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import za.co.codetribe.kid.R;




public class ForgetActivity extends AppCompatActivity {


    private FirebaseAuth auth;
    String enteredEmail;
    EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);


        auth = FirebaseAuth.getInstance();

     email = (EditText) findViewById(R.id.currentEmail);
        Button reset = (Button) findViewById(R.id.btnReset);


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enteredEmail = email.getText().toString();


                if (TextUtils.isEmpty(enteredEmail)) {
                    Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                    return;
                }


                auth.sendPasswordResetEmail(enteredEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgetActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ForgetActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            }
        });


    }

}


//    public void reset(View view)
//    {
//        FirebaseUser user=firebase.getCurrentUser();
//
//        if(user.getEmail().equals(enteredEmail))
//        {
//            Toast.makeText(this," email user found ", Toast.LENGTH_LONG).show();
//        }
//        else
//        {
//            Toast.makeText(this," email not  found ", Toast.LENGTH_LONG).show();
//        }
//    }