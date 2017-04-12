package ca.mcgill.ecse321.tamas_mobile;

import android.content.Context;

import ca.mcgill.ecse321.TAMAS.model.ManagementSystem;

public class Parameters {
    public Context c;
    public ManagementSystem ms;
    public int option;
    public Parameters(Context c, ManagementSystem ms, int option){
        this.c=c;
        this.ms=ms;
        this.option=option;
    }
}
