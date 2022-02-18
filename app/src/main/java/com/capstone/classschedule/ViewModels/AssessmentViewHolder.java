package com.capstone.classschedule.ViewModels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.classschedule.Model.Assessment;
import com.capstone.classschedule.Model.AssessmentType;
import com.capstone.classschedule.R;

public class AssessmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ImageView assessmentIcon;
    private final TextView assessmentTitle;
    private final TextView assessmentNote;
    private Assessment thisAssessment;

    public AssessmentViewHolder(@NonNull View itemView) {
        super(itemView);
        assessmentIcon = itemView.findViewById(R.id.assessment_item_type_image);
        assessmentTitle = itemView.findViewById(R.id.assessment_item_title_textview);
        assessmentNote = itemView.findViewById(R.id.assessment_item_note_textview);
    }
    public void bind(Assessment assessment) {
        thisAssessment = assessment;
        if (thisAssessment.getType() == AssessmentType.WRITTEN) {
            assessmentIcon.setImageResource(R.drawable.assessment_written);
        } else {
            assessmentIcon.setImageResource(R.drawable.assessment_oral);
        }
        assessmentTitle.setText(thisAssessment.getTitle());
        assessmentNote.setText(thisAssessment.getDescription());
    }

    public static AssessmentViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_assessment, parent, false);
        return new AssessmentViewHolder(view);
    }

    @Override
    public void onClick(View v) {
        //TODO add assessment selction functions
    }
}
