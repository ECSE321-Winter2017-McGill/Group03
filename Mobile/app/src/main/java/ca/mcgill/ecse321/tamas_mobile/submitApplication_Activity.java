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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.tamas.controller.InvalidInputException;
import ca.mcgill.ecse321.tamas.controller.TamasController;
import ca.mcgill.ecse321.tamas.model.Allocation;
import ca.mcgill.ecse321.tamas.model.Applicant;
import ca.mcgill.ecse321.tamas.model.Instructor;
import ca.mcgill.ecse321.tamas.model.ManagementSystem;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

public class submitApplication_Activity extends AppCompatActivity {

    private String fileName = "";
    private ManagementSystem ms = null;
    private String error="";
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_application_);

        Intent intent = getIntent();
        String title = intent.getStringExtra("key");
        TextView jobDescription = (TextView)findViewById(R.id.job_description);
        jobDescription.setText(title);

        fileName = getFilesDir().getAbsolutePath() + "/data.xml";
        ms = PersistenceXStream.initializeModelManager(fileName);

        refreshData();
    }

    public void refreshData() {

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


        TextView name = (TextView) findViewById(R.id.applicant_name);
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

        name.setText("");
        id.setText("");
        major.setText("");
        experience.setText("");


        if (error.length()>0) {

            final Dialog dialog = new Dialog(context);
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

    public void createApplication(View v){

        error = "";

        TextView course = (TextView)findViewById(R.id.course_postingPage);
        TextView semester = (TextView)findViewById(R.id.semester);
        TextView jobType = (TextView)findViewById(R.id.jobType);
        TextView numberNeeded = (TextView)findViewById(R.id.numberNeeded);
        TextView totalHours = (TextView)findViewById(R.id.totalHours);
        TextView hourlyRates = (TextView)findViewById(R.id.hourlyRates);


        TextView name = (TextView)findViewById(R.id.applicant_name);
        TextView id = (TextView)findViewById(R.id.applicant_id);
        TextView major = (TextView)findViewById(R.id.applicant_major);
        TextView experience = (TextView)findViewById(R.id.applicant_experience);
        TextView errorMessage = (TextView)findViewById(R.id.errorMessageApplicationPage);


        Spinner degree = (Spinner)findViewById(R.id.degree_spinner);
        Spinner year = (Spinner)findViewById(R.id.year_spinner);
        Spinner preference1 = (Spinner)findViewById(R.id.preference1_spinner);
        Spinner preference2 = (Spinner)findViewById(R.id.preference2_spinner);
        Spinner preference3 = (Spinner)findViewById(R.id.preference3_spinner);

        int selectedDegreeInd =  degree.getSelectedItemPosition();
        int selectedYearInd = year.getSelectedItemPosition();
        int selectedPreference1Ind = preference1.getSelectedItemPosition();
        int selectedPreference2Ind = preference2.getSelectedItemPosition();
        int selectedPreference3Ind = preference3.getSelectedItemPosition();

        String selectedPreference1="";
        String selectedPreference2="";
        String selectedPreference3="";

        boolean undergrad = true;
        String this_year=Integer.toString(selectedYearInd);

        if (selectedDegreeInd==1){
            undergrad=false;
        }

        if (selectedPreference1Ind>0) {
            selectedPreference1 = preference1.getSelectedItem().toString();
        }

        if (selectedPreference2Ind>0){
            selectedPreference2 = preference2.getSelectedItem().toString();
        }

        if (selectedPreference3Ind>0){
            selectedPreference3 = preference3.getSelectedItem().toString();
        }



        TamasController controller = new TamasController(ms);

                 try{
                     if (id.getText().toString().equals("")){
                         controller.createApplicant(name.getText().toString(),-1,major.getText().toString(),undergrad,this_year,experience.getText().toString(),selectedPreference1,selectedPreference2,selectedPreference3,0);
                     }
                      controller.createApplicant(name.getText().toString(),Integer.parseInt(id.getText().toString()),major.getText().toString(),undergrad,this_year,experience.getText().toString(),selectedPreference1,selectedPreference2, selectedPreference3,0);

                 }
                  catch (InvalidInputException e){
                      error = e.getMessage();
                  }

        refreshData();

    }

    public void backFromApplication(View v){
        Intent back = new Intent(submitApplication_Activity.this, ViewJobPosting_Activity.class);
        submitApplication_Activity.this.startActivity(back);
    }
}
