package ca.mcgill.ecse321.tamas_mobile;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.thoughtworks.xstream.XStream;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.TAMAS.Web.controller.DDBmanager;
import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;
import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.Allocation;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Instructor;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

public class submitApplication_Activity extends AppCompatActivity {

    private DDBmanager asyncTask = new DDBmanager();
    private ManagementSystem ms;
    private String error = "";
    private String username = "";
    private String title = "";
    private String course = "";
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_application_);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        title = intent.getStringExtra("title");
        course = intent.getStringExtra("course");

        TextView jobDescription = (TextView) findViewById(R.id.job_description);
        TextView name = (TextView) findViewById(R.id.applicant_name);

        jobDescription.setText(course + " - " + title);
        name.setText(username);


        refreshData();
    }

    private void refreshData() {
        Spinner degree = (Spinner) findViewById(R.id.degree_spinner);
        ArrayAdapter<String> degreeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        Spinner year = (Spinner) findViewById(R.id.year_spinner);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        Spinner preference1 = (Spinner) findViewById(R.id.preference1_spinner);
        Spinner preference2 = (Spinner) findViewById(R.id.preference2_spinner);
        Spinner preference3 = (Spinner) findViewById(R.id.preference3_spinner);
        ArrayAdapter<String> preferenceAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ArrayAdapter<String> preferenceAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ArrayAdapter<String> preferenceAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);


        TextView id = (TextView) findViewById(R.id.applicant_id);
        TextView major = (TextView) findViewById(R.id.applicant_major);
        TextView experience = (TextView) findViewById(R.id.applicant_experience);


        degreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        degreeAdapter.add("Undergraduate");
        degreeAdapter.add("Graduate");
        degree.setAdapter(degreeAdapter);


        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearAdapter.add("0");
        yearAdapter.add("1");
        yearAdapter.add("2");
        yearAdapter.add("3");
        year.setAdapter(yearAdapter);

        preferenceAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        preferenceAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        preferenceAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        preferenceAdapter1.add("Preference 1. Must select.");
        preferenceAdapter2.add("Preference 2. Select if already applied for 1 job.");
        preferenceAdapter3.add("Preference 3. Select if already applied for 2 jobs.");

        ArrayList<String> courses = new ArrayList<String>();
        courses.add("ECSE 321");
        courses.add("ECSE 223");
        courses.add("ECSE 222");

        for (String course : courses) {
            preferenceAdapter1.add(course);
            preferenceAdapter2.add(course);
            preferenceAdapter3.add(course);
        }

        preference1.setAdapter(preferenceAdapter1);
        preference2.setAdapter(preferenceAdapter2);
        preference3.setAdapter(preferenceAdapter3);

        id.setText("");
        major.setText("");
        experience.setText("");


        if (error.trim().length() > 0) {

            final Dialog dialog = new Dialog(context);
//            dialog.setContentView( R.layout.dialog_submit );
//            TextView edit_model = (TextView) dialog.findViewById( R.id.edit_model );
//            edit_model.setText( android.os.Build.DEVICE );
//            dialog.setTitle( "Enter Details" );
//            dialog.show( );

            dialog.setContentView(R.layout.statusdialog);
            dialog.setTitle("Error message");

            TextView text = (TextView) dialog.findViewById(R.id.text);
            text.setText(error);

            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }

    public void createApplication(View v) {

        ms = (ManagementSystem) loadFromXML();
        error = "";

        TextView id = (TextView) findViewById(R.id.applicant_id);
        TextView major = (TextView) findViewById(R.id.applicant_major);
        TextView experience = (TextView) findViewById(R.id.applicant_experience);

        if (id.getText().toString()==null ||id.getText().toString().trim().length()==0){
            error += "Id number cannot be empty! ";
        }

        if (major.getText().toString()==null ||major.getText().toString().trim().length()==0){
            error += "Major cannot be empty! ";
        }

        if (experience.getText().toString()==null ||experience.getText().toString().trim().length()==0){
            error += "Experience cannot be empty! ";
        }

        Spinner degree = (Spinner) findViewById(R.id.degree_spinner);
        Spinner year = (Spinner) findViewById(R.id.year_spinner);
        Spinner preference1 = (Spinner) findViewById(R.id.preference1_spinner);
        Spinner preference2 = (Spinner) findViewById(R.id.preference2_spinner);
        Spinner preference3 = (Spinner) findViewById(R.id.preference3_spinner);

        int selectedDegreeInd = degree.getSelectedItemPosition();
        int selectedYearInd = year.getSelectedItemPosition();
        int selectedPreference1Ind = preference1.getSelectedItemPosition();
        int selectedPreference2Ind = preference2.getSelectedItemPosition();
        int selectedPreference3Ind = preference3.getSelectedItemPosition();

        String selectedPreference1 = "";
        String selectedPreference2 = "";
        String selectedPreference3 = "";

        //Year
        String this_year = Integer.toString(selectedYearInd);

        //U/G
        boolean isUndergrad = true;

        if (selectedDegreeInd == 1) {
            isUndergrad = false;
        }

        if (selectedPreference1Ind > 0) {
            selectedPreference1 = preference1.getSelectedItem().toString();
        }
        else{
            error += "You must select your first preference! ";
        }

        if (selectedPreference2Ind > 0) {
            selectedPreference2 = preference2.getSelectedItem().toString();
        }

        if (selectedPreference3Ind > 0) {
            selectedPreference3 = preference3.getSelectedItem().toString();
        }


        if (error.trim().length()==0) {
            TamasController tc = new TamasController(ms);

            JobPosting thisPosting = null;
            for (JobPosting aPosting : ms.getJobPostings()) {
                if (aPosting.getJobTitle().equals(title) && aPosting.getCourse().getCourseCode().equals(course)) {
                    thisPosting = aPosting;
                    break;
                }
            }

            if (thisPosting == null) {
                error += "This job posting does not exist! ";
            } else {
                try {
                    tc.createApplication(thisPosting, username,          //name
                            Integer.parseInt(id.getText().toString()),   //id
                            major.getText().toString(),                  //major
                            isUndergrad,                                 //U/G
                            this_year,                                   //year
                            experience.getText().toString(),             //exp
                            selectedPreference1,                         //pre1
                            selectedPreference2,                         //pre2
                            selectedPreference3,                         //pre3
                            0                                            //apt hr
                    );
                } catch (InvalidInputException e) {
                    error = e.getMessage();
                }
            }
        }

    refreshData();

    }

    public void backFromApplication(View v){
        Intent back = new Intent(submitApplication_Activity.this, ViewJobPosting_Activity.class);
        back.putExtra("username",username);
        back.putExtra("course",course);
        back.putExtra("title",title);
        submitApplication_Activity.this.startActivity(back);
    }

    public Object loadFromXML() {
        XStream xstream = new XStream();
        xstream.setMode(XStream.ID_REFERENCES);
        xstream.alias("jobInfo", JobPosting.class);
        String filePath = getFilesDir().getPath().toString() + "/data.xml";
        File f=new File(filePath);
        try {
            FileReader fileReader = new FileReader(f); // load our xml
            return xstream.fromXML(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

