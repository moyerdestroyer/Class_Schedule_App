package com.capstone.classschedule.ViewModels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.classschedule.Model.Course;
import com.capstone.classschedule.R;
import com.capstone.classschedule.ReportActivity;

public class CourseReportViewHolder extends RecyclerView.ViewHolder {
    private final TextView id;
    private final TextView title;
    private final TextView start;
    private final TextView end;
    private final TextView instructor;
    private final TextView created;
    private final TextView updated;
    private final TextView assessments;
    private Course thisCourse;

    public CourseReportViewHolder(@NonNull View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.report_recyclerview_id_textview);
        title = itemView.findViewById(R.id.report_recyclerview_title_textview);
        start = itemView.findViewById(R.id.report_recyclerview_start_textview);
        end = itemView.findViewById(R.id.report_recyclerview_end_textview);
        instructor = itemView.findViewById(R.id.report_recyclerview_instructor_textview);
        created = itemView.findViewById(R.id.report_recyclerview_created_textview);
        updated = itemView.findViewById(R.id.report_recyclerview_updated_textview);
        assessments = itemView.findViewById(R.id.report_recyclerview_assessments);

    }
    public void bind (Course course) {
        thisCourse = course;
        id.setText(String.valueOf(thisCourse.getId()));
        title.setText(thisCourse.getTitle());
        start.setText(thisCourse.getStart());
        end.setText(thisCourse.getEnd());
        instructor.setText(thisCourse.getInstructor());
        created.setText(thisCourse.getCreatedTime());
        updated.setText(thisCourse.getLastModifiedTime());
        assessments.setText(String.valueOf(thisCourse.getNumberOfAssessments()));
    }
    public static CourseReportViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report, parent, false);
        return new CourseReportViewHolder(view);
    }
}
