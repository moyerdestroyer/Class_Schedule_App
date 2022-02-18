package com.capstone.classschedule.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.capstone.classschedule.Adapters.DateConverter;
import com.capstone.classschedule.R;

import java.util.Calendar;
import java.util.Objects;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = DateConverter.returnDateAsString(year, month, dayOfMonth);
        assert this.getTag() != null;
        if(this.getTag().equals("startPicker")) {
            EditText start = (EditText) getActivity().findViewById(R.id.fragment_course_start_date_edittext);
            start.setText(date);
        } else {
            EditText end = (EditText) getActivity().findViewById(R.id.fragment_course_end_date_edittext);
            end.setText(date);
        }
    }
}
