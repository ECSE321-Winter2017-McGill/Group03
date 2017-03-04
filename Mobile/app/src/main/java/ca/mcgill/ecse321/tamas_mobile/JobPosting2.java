package ca.mcgill.ecse321.tamas_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class JobPosting2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_posting2);
    }

    public void applyForJob2(View v){
        TextView jobPosting1 = (TextView)findViewById(R.id.jobPostingTitle2);
        String title = jobPosting1.getText().toString();
        Intent applyNowIntent = new Intent(JobPosting2.this, submitApplication_Activity.class);
        applyNowIntent.putExtra("key",title);
        JobPosting2.this.startActivity(applyNowIntent);
    }

    public void backFromJobPosting2(View v){
        Intent back = new Intent(JobPosting2.this, ViewJobPosting_Activity.class);
        JobPosting2.this.startActivity(back);
    }
}
