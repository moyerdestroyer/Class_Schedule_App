package com.capstone.classschedule.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.capstone.classschedule.CourseActivity;
import com.capstone.classschedule.Model.AssessmentType;
import com.capstone.classschedule.R;


public class AddAssessmentFragment extends DialogFragment {
    private RadioButton oralButton, writtenButton;
    private EditText titleEdittext, noteEdittext;
    private Button saveButton, cancelButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        CourseActivity activity = (CourseActivity) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        assert activity != null;
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_create_assessment, null);
        //getviews
        oralButton = view.findViewById(R.id.dialog_assessment_type_oral_button);
        writtenButton = view.findViewById(R.id.dialog_assessment_type_written_button);
        titleEdittext = view.findViewById(R.id.dialog_assessment_title_edittext);
        noteEdittext = view.findViewById(R.id.dialog_assessment_note_edittext);

        String idText = getTag();
        int id, assessmentType;
        assert idText != null;
        id = idText.equals("Add Course…") ? -1 : Integer.parseInt(idText);
        if(writtenButton.isChecked()){
            assessmentType = AssessmentType.WRITTEN;
        } else {
            assessmentType = AssessmentType.ORAL;
        }

        builder.setView(view).setTitle("Add Assessment").setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.positiveAssessmentClick(id, assessmentType, titleEdittext.getText().toString(), noteEdittext.getText().toString());
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        return builder.create();


    }

}