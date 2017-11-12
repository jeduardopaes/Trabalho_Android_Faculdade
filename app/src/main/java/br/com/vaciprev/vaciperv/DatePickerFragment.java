package br.com.vaciprev.vaciperv;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Layout;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Calendar;


public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        EditText data = (EditText) getActivity().findViewById(R.id.DataDaVacina);
        Switch segundaDose = (Switch) getActivity().findViewById(R.id.SegundaDose);
        EditText data2 = (EditText) getActivity().findViewById(R.id.DataSegundaDose);

        //data.setText(day+"/"+month+"/"+year);

        if(segundaDose.isChecked()){
            data2.setText(day+"/"+month+"/"+year);
        }else{
            data.setText(day+"/"+month+"/"+year);
        }
    }


}