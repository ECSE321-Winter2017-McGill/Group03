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

import java.util.List;

import ca.mcgill.ecse321.TAMAS.model.Applicant;
import ca.mcgill.ecse321.TAMAS.model.Application;
import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class ViewStatus_Activity extends AppCompatActivity {

    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_status_);

        Button viewOffer1 = (Button) findViewById(R.id.viewOffer1);
        Button viewOffer2 = (Button) findViewById(R.id.viewOffer2);
        Button viewOffer3 = (Button) findViewById(R.id.viewOffer3);

        viewOffer1.setEnabled(false);
        viewOffer2.setEnabled(false);
        viewOffer3.setEnabled(false);

        TextView course1 = (TextView)findViewById(R.id.course_empty1);
        TextView course2 = (TextView)findViewById(R.id.course_empty2);
        TextView course3 = (TextView)findViewById(R.id.course_empty2);

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.statusdialog);
        dialog.setTitle("Reminder");

       TextView text = (TextView)dialog.findViewById(R.id.text);
        text.setText("Your can only view your applications after the system maintenance period");

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();


    }

    public void backFromStatus(View v){
        Intent back = new Intent(ViewStatus_Activity.this,Dashboard_Activity.class);
        ViewStatus_Activity.this.startActivity(back);
    }
}
