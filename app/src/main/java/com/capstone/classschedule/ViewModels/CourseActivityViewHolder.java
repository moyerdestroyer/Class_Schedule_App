package com.capstone.classschedule.ViewModels;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.classschedule.CourseActivity;
import com.capstone.classschedule.Model.Course;
import com.capstone.classschedule.R;

public class CourseActivityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView courseTitle;
    private final TextView courseDates;
    private final TextView courseInstructor;
    private Course thisCourse;

    public CourseActivityViewHolder(@NonNull View itemView) {
        super(itemView);
        courseTitle = itemView.findViewById(R.id.item_course_title_textview);
        courseDates = itemView.findViewById(R.id.item_course_date_textview);
        courseInstructor = itemView.findViewById(R.id.item_course_instructor_textview);
    }

    public void bind(Course course) {
        thisCourse = course;
        courseTitle.setText(thisCourse.getTitle());
        courseDates.setText(String.format("%s - %s", thisCourse.getStart(), thisCourse.getEnd()));
        courseInstructor.setText(thisCourse.getInstructor());
        itemView.setOnClickListener(this);
    }
    public static CourseActivityViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new CourseActivityViewHolder(view);
    }

    @Override
    public void onClick(View v) {
        CourseActivity courseActivity = (CourseActivity) v.getContext();
        courseActivity.courseDetailView(thisCourse);
    }
}
