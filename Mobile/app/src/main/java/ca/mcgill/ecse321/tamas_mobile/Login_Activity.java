package ca.mcgill.ecse321.tamas_mobile;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ca.mcgill.ecse321.TAMAS.Web.controller.DDBmanager;
import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
public class Login_Activity extends AppCompatActivity implements AsyncResponse {
    DDBmanager asyncTask =new DDBmanager();
    private String error ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        refreshData();
        // 0-> getDB,1-> updataDB
        // DO NOT PASS IN (... , null ,1 )!  This will kill our database.
        ManagementSystem ms=(ManagementSystem)loadFromXML();
        Parameters p=new Parameters(getApplicationContext(),ms,1);
        asyncTask.delegate = this;
        asyncTask.execute(p);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ManagementSystem ms=(ManagementSystem) loadFromXML();
                Snackbar.make(view,""+ms.numberOfCourses(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        FileOutputStream outputStream;        try {
            outputStream =new FileOutputStream (f);
            outputStream.write(string.getBytes());
            System.out.println(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("sss"+f.exists());
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
        }else{
            return new ManagementSystem();
        }
    }

    public void refreshData(){
        TextView errorMessage = (TextView)findViewById(R.id.logInError);
        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);

        errorMessage.setText(error);
        username.setText("");
        password.setText("");
    }

    public void login(View v){
        final ManagementSystem ms=(ManagementSystem) loadFromXML();

        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.login_button);
        TextView errorMessage = (TextView)findViewById(R.id.logInError);

        error ="";

        if (username.getText()==null || username.getText().toString().length()==0){
            error += "Please enter your username! ";
        }

        boolean found = false;

        if (error.length()==0) {

            for (Applicant anApplicant : ms.getApplicants()) {
                if (username.getText().toString().equals(anApplicant.getName())) {
                    found = true;
                    Intent loginIntent = new Intent(Login_Activity.this, Dashboard_Activity.class);
                    loginIntent.putExtra("username", username.getText().toString());
                    Login_Activity.this.startActivity(loginIntent);
                    break;
                }
            }

            if (found==false){
                error += "Incorrect username";
            }

        }

        refreshData();

    }

    public void switchToRegister(View v){
        Intent registerIntent = new Intent(Login_Activity.this, Register_Activity.class);
        Login_Activity.this.startActivity(registerIntent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
