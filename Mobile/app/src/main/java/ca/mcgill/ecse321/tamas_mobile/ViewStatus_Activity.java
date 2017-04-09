package ca.mcgill.ecse321.tamas_mobile;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thoughtworks.xstream.XStream;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.TAMAS.Web.controller.DDBmanager;
import ca.mcgill.ecse321.TAMAS.controller.InvalidInputException;
import ca.mcgill.ecse321.TAMAS.controller.TamasController;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class ViewStatus_Activity extends AppCompatActivity implements AsyncResponse{

    final Context context = this;
    private String username="";
    private String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("error message： "+error);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_status_);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        refreshData();
    }

    private void refreshData(){
        //Accept buttons
        List<Button> acceptButtons = new ArrayList<Button>();
        Button accept1 = (Button) findViewById(R.id.accept1);
        Button accept2 = (Button) findViewById(R.id.accept2);
        Button accept3 = (Button) findViewById(R.id.accept3);
        acceptButtons.add(accept1);
        acceptButtons.add(accept2);
        acceptButtons.add(accept3);

        //Reject buttons
        List<Button> rejectButtons = new ArrayList<Button>();
        Button reject1 = (Button) findViewById(R.id.reject1);
        Button reject2 = (Button) findViewById(R.id.reject2);
        Button reject3 = (Button) findViewById(R.id.reject3);
        rejectButtons.add(reject1);
        rejectButtons.add(reject2);
        rejectButtons.add(reject3);

        accept1.setEnabled(false);
        accept2.setEnabled(false);
        accept3.setEnabled(false);

        reject1.setEnabled(false);
        reject2.setEnabled(false);
        reject3.setEnabled(false);

        //Courses
        List<TextView> courses = new ArrayList<TextView>();
        TextView course1 = (TextView)findViewById(R.id.course_empty1);
        TextView course2 = (TextView)findViewById(R.id.course_empty2);
        TextView course3 = (TextView)findViewById(R.id.course_empty3);
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);

        //Titles
        List<TextView> titles = new ArrayList<TextView>();
        TextView title1 = (TextView)findViewById(R.id.job_empty1);
        TextView title2 = (TextView)findViewById(R.id.job_empty2);
        TextView title3 = (TextView)findViewById(R.id.job_empty3);
        titles.add(title1);
        titles.add(title2);
        titles.add(title3);

        //Status
        List<TextView> status = new ArrayList<TextView>();
        TextView status1 = (TextView)findViewById(R.id.status_empty1);
        TextView status2 = (TextView)findViewById(R.id.status_empty2);
        TextView status3 = (TextView)findViewById(R.id.status_empty3);
        status.add(status1);
        status.add(status2);
        status.add(status3);


        DDBmanager asyncTask = new DDBmanager();
        Parameters p=new Parameters(getApplicationContext(),null,0);
        asyncTask.delegate = this;
        asyncTask.execute(p);

        ManagementSystem ms = (ManagementSystem)loadFromXML();

        //Find the applicant and get his/her applications
        List<Application> allApplications = null;
        for (Applicant anApplicant: ms.getApplicants()){
            if (anApplicant.getName().equals(username)){
                allApplications = anApplicant.getApplications();
                break;
            }
        }

        //Display the applicant's applications and set buttons according to the status
        if (allApplications!=null){
            for (int i=0; i<allApplications.size();i++){
                courses.get(i).setText(allApplications.get(i).getJobPosting().getCourse().getCourseCode());
                titles.get(i).setText(allApplications.get(i).getJobPosting().getJobTitle());
                status.get(i).setText(allApplications.get(i).getStatusFullName());

                if (allApplications.get(i).getStatusFullName().equals("SELECTED")){
                    acceptButtons.get(i).setEnabled(true);
                    rejectButtons.get(i).setEnabled(true);
                }
            }
        }

        if (error.trim().length()>0){
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.statusdialog);
            dialog.setTitle("Error");
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

    public void acceptOffer(View v){

        error = "";

        //Reminder
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(R.layout.statusdialog);
//        dialog.setTitle("Reminder");
//        TextView text = (TextView)dialog.findViewById(R.id.text);
//        text.setText("Please make sure that you click on the right button. You will not be able to modify your decision later. ");
//        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();


        //Load the database
        DDBmanager asyncTask = new DDBmanager();
        Parameters p=new Parameters(getApplicationContext(),null,0);
        asyncTask.delegate = this;
        asyncTask.execute(p);

        ManagementSystem ms = (ManagementSystem)loadFromXML();
        TamasController tc = new TamasController(ms);

        List<Application> allApplications = null;
        for (Applicant anApplicant : ms.getApplicants()) {
            if (anApplicant.getName().equals(username)) {
                allApplications = anApplicant.getApplications();
                break;
            }
        }

        //Update the database
        if (allApplications!=null) {

            try {
                switch (v.getId()) {

                    case R.id.accept1:
                        tc.acceptOffer(allApplications.get(0));
                        break;
                    case R.id.accept2:
                        tc.acceptOffer(allApplications.get(1));
                        break;
                    case R.id.accept3:
                        tc.acceptOffer(allApplications.get(2));
                        break;
                }
            } catch (InvalidInputException e) {
                error += e.getMessage();
            }
        }


        if (error.trim().length()<=0) {
            if (ms != null) {
                Parameters p2 = new Parameters(getApplicationContext(), ms, 1);
                asyncTask = new DDBmanager();
                asyncTask.delegate = this;
                asyncTask.execute(p2);
            }


            //Success message
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.statusdialog);
            dialog.setTitle("Reminder");
            TextView text = (TextView)dialog.findViewById(R.id.text);
            text.setText("You have successfully accepted this offer. Refresh the page to view the updated status");
            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }

        refreshData();

    }

    public void declineOffer(View v) {

        error = "";

        //Reminder
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.statusdialog);
        dialog.setTitle("Reminder");
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText("Please make sure that you click the right button. You will not be able to modify your decision later. ");
        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();


        //Load the database
        DDBmanager asyncTask = new DDBmanager();
        Parameters p = new Parameters(getApplicationContext(), null, 0);
        asyncTask.delegate = this;
        asyncTask.execute(p);

        ManagementSystem ms = (ManagementSystem) loadFromXML();
        TamasController tc = new TamasController(ms);

        List<Application> allApplications = null;
        for (Applicant anApplicant : ms.getApplicants()) {
            if (anApplicant.getName().equals(username)) {
                allApplications = anApplicant.getApplications();
                break;
            }
        }

        //Update the database
        if (allApplications!=null) {

            try {
                switch (v.getId()) {
                    case R.id.reject1:
                        tc.declineOffer(allApplications.get(0));
                        break;
                    case R.id.reject2:
                        tc.declineOffer(allApplications.get(1));
                        break;
                    case R.id.reject3:
                        tc.declineOffer(allApplications.get(2));
                        break;
                }
            } catch (InvalidInputException e) {
                error += e.getMessage();
            }
        }


        if (ms!=null) {
            Parameters p2 = new Parameters(getApplicationContext(), ms, 1);
            asyncTask = new DDBmanager();
            asyncTask.delegate = this;
            asyncTask.execute(p2);
        }


        //Success message
        text.setText("You have successfully declined this offer");
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        refreshData();
    }



    public void backFromStatus(View v){
        Intent back = new Intent(ViewStatus_Activity.this,Dashboard_Activity.class);
        back.putExtra("username",username);
        ViewStatus_Activity.this.startActivity(back);
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
            System.out.println(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("sss"+f.exists());
    }

}
