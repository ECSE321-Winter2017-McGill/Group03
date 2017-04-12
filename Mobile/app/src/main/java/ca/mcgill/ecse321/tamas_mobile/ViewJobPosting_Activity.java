package ca.mcgill.ecse321.tamas_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import ca.mcgill.ecse321.TAMAS.Web.controller.DDBmanager;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class ViewJobPosting_Activity extends AppCompatActivity implements AsyncResponse {


    private String username ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job_posting_);

        // Get the username of the current user
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        DDBmanager asyncTask = new DDBmanager();
        Parameters p=new Parameters(getApplicationContext(),null,0);
        asyncTask.delegate = this;
        asyncTask.execute(p);

        ManagementSystem ms = (ManagementSystem) loadFromXML();

        // Clear all the elements that are previously displayed
        TableLayout table = (TableLayout)findViewById(R.id.viewJobPostingTable);
        table.removeAllViews();

        // Check the number and content of the latest job postings in the system and create UI elements accordingly
        for (int i=0;i<ms.getJobPostings().size();i++) {
            final JobPosting thisPosting = ms.getJobPostings().get(i);

            TableRow newRow = new TableRow(this);
            table.addView(newRow);

            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            newRow.setLayoutParams(lp);

            TextView jobPostingTitle = new TextView(this);
            newRow.addView(jobPostingTitle);
            jobPostingTitle.setText(thisPosting.getCourse().getCourseCode() + " - " + thisPosting.getJobTitle());
            jobPostingTitle.setTextColor(Color.BLACK);
            jobPostingTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            jobPostingTitle.setOnClickListener(new View.OnClickListener() {
                // This method is called if a certain job posting title is clicked
                // The next page shows the content of the selected job posting
                @Override
                public void onClick(View v) {
                    Intent viewJobIntent = new Intent(ViewJobPosting_Activity.this, ViewSpecificJobPosting_Activity.class);
                    viewJobIntent.putExtra("username", username);
                    viewJobIntent.putExtra("title", thisPosting.getJobTitle());
                    viewJobIntent.putExtra("course", thisPosting.getCourse().getCourseCode());
                    ViewJobPosting_Activity.this.startActivity(viewJobIntent);
                }
            });

        }
    }

    // This method is called if the back button is clicked
    public void backToHome(View v){
        Intent backIntent = new Intent(ViewJobPosting_Activity.this, Dashboard_Activity.class);
        backIntent.putExtra("username",username);
        ViewJobPosting_Activity.this.startActivity(backIntent);
    }

    public Object loadFromXML() {
        XStream xstream = new XStream();
        xstream.setMode(XStream.ID_REFERENCES);
        xstream.alias("jobInfo", JobPosting.class);
        String filePath = getFilesDir().getPath().toString() + "/data.xml";
        File f=new File(filePath);
        if(f.exists()) {
            try {
                FileReader fileReader = new FileReader(f); // load our xml
                return xstream.fromXML(fileReader);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else{
            return new ManagementSystem();
        }
    }

    @Override
    public void processFinish(String output){
        writeFile(output);
    }
    public void writeFile(String data) {
        String filePath = getFilesDir().getPath().toString() + "/data.xml";
        File f=new File(filePath);
        if (!f.exists()) {
            try {
                f.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        String string = data;
        FileOutputStream outputStream;
        try {
            outputStream =new FileOutputStream (f);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
