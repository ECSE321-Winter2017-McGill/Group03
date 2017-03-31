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
import ca.mcgill.ecse321.TAMAS.model.JobPosting;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;
import ca.mcgill.ecse321.TAMAS.persistence.DBmanager;
import ca.mcgill.ecse321.TAMAS.persistence.PersistenceXStream;

public class Login_Activity extends AppCompatActivity implements AsyncResponse {
    DDBmanager asyncTask =new DDBmanager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        refreshData();
        // 0-> getDB,1-> updataDB
        // DO NOT PASS IN (... , null ,1 )!  This will kill our database.
        Parameters p=new Parameters(getApplicationContext(),null,0);
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
        FileOutputStream outputStream;
        try {
            outputStream =new FileOutputStream (f);
            outputStream.write(string.getBytes());
            System.out.println(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("sss"+f.exists());
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

    public void refreshData(){
        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);

        username.setText("");
        password.setText("");
    }

    public void login(View v){
        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.login_button);
        TextView errorMessage = (TextView)findViewById(R.id.logInError);

        errorMessage.setText("");

        if ((username.getText().toString().equals("student")) && (password.getText().toString().equals("password"))){

            Intent applyIntent = new Intent(Login_Activity.this, Dashboard_Activity.class);
            applyIntent.putExtra("username",username.getText().toString());
            Login_Activity.this.startActivity(applyIntent);
        }

        else{
            errorMessage.setText("Wrong username or/and password");
            refreshData();
        }

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
