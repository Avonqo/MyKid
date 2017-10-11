package za.co.codetribe.kid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    public void SchoolRegister(View view)
    {
        Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void ParentSignIn(View view)
    {
        Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
