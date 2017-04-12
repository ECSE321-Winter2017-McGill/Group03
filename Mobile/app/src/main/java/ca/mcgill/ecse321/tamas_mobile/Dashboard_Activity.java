package ca.mcgill.ecse321.tamas_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dashboard_Activity extends AppCompatActivity {

    private String username="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_);

        // Get the username of the current user
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        TextView welcomeMessage = (TextView) findViewById(R.id.welcome_message);
        welcomeMessage.setText("Hello, "+username+"!");
    }


    // This method is called when log out button is clicked
    public void logOut(View v){

        Intent logOutIntent = new Intent(Dashboard_Activity.this, Login_Activity.class);
        Dashboard_Activity.this.startActivity(logOutIntent);

    }

    // This method is called when the "click here to view job postings" textview is clicked
    public void viewJobPosting(View v) {

        Intent viewJobPostingIntent = new Intent(Dashboard_Activity.this, ViewJobPosting_Activity.class);
        viewJobPostingIntent.putExtra("username",username);
        Dashboard_Activity.this.startActivity(viewJobPostingIntent);

    }


    // This method is called when the "click here to view your applications" textview is clicked
    public void viewApplication(View v){

        Intent viewApplicationIntent = new Intent(Dashboard_Activity.this, ViewStatus_Activity.class);
        viewApplicationIntent.putExtra("username",username);
        Dashboard_Activity.this.startActivity(viewApplicationIntent);

    }

}
