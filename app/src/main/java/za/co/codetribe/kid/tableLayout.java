package za.co.codetribe.kid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;


public class tableLayout extends AppCompatActivity {

    TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layout1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tableLayout = (TableLayout)findViewById(R.id.table);
    }

}
