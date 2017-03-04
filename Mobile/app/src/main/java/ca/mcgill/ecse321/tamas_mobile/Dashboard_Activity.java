package ca.mcgill.ecse321.tamas_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dashboard_Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

    }



    public void logOut(View v){

        Intent logOutIntent = new Intent(Dashboard_Activity.this, Login_Activity.class);
        Dashboard_Activity.this.startActivity(logOutIntent);

    }

    public void viewJobPosting(View v) {

        Intent viewJobPostingIntent = new Intent(Dashboard_Activity.this, ViewJobPosting_Activity.class);
        Dashboard_Activity.this.startActivity(viewJobPostingIntent);

    }

    //Can be done next time

    public void viewApplication(View v){

        Intent viewApplicationIntent = new Intent(Dashboard_Activity.this, ViewStatus_Activity.class);
        Dashboard_Activity.this.startActivity(viewApplicationIntent);

    }

}
