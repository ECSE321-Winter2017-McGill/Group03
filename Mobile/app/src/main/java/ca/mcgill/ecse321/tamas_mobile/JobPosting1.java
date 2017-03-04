package ca.mcgill.ecse321.tamas_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class JobPosting1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_posting1);
    }

    public void applyForJob1(View v){
        TextView jobPosting1 = (TextView)findViewById(R.id.jobPostingTitle1);
        String title = jobPosting1.getText().toString();
        Intent applyNowIntent = new Intent(JobPosting1.this, submitApplication_Activity.class);
        applyNowIntent.putExtra("key",title);
        JobPosting1.this.startActivity(applyNowIntent);
    }
    public void backFromJobPosting1(View v){
        Intent back = new Intent(JobPosting1.this, ViewJobPosting_Activity.class);
        JobPosting1.this.startActivity(back);
    }
}
