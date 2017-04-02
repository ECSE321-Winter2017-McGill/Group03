package ca.mcgill.ecse321.tamas_mobile;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class ViewJobPosting_Activity extends AppCompatActivity {

    private ManagementSystem ms = new ManagementSystem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job_posting_);
    }




    public void backToHome(View v){
        Intent backIntent = new Intent(ViewJobPosting_Activity.this, Dashboard_Activity.class);
        ViewJobPosting_Activity.this.startActivity(backIntent);
    }

    public void viewJobPosting1(View v){
        Intent viewJob1 = new Intent(ViewJobPosting_Activity.this, JobPosting1.class);
        ViewJobPosting_Activity.this.startActivity(viewJob1);
    }

    public void viewJobPosting2(View v){
        Intent viewJob2 = new Intent(ViewJobPosting_Activity.this, JobPosting2.class);
        ViewJobPosting_Activity.this.startActivity(viewJob2);
    }
}
