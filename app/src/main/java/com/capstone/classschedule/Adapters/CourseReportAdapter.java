package com.capstone.classschedule.Adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.capstone.classschedule.Model.Course;
import com.capstone.classschedule.ViewModels.CourseReportViewHolder;

public class CourseReportAdapter extends ListAdapter<Course, CourseReportViewHolder> {
    public CourseReportAdapter(@NonNull DiffUtil.ItemCallback<Course> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CourseReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CourseReportViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseReportViewHolder holder, int position) {
        Course current = getItem(position);
        holder.bind(current);
    }
    public static class CourseDiff extends DiffUtil.ItemCallback<Course> {

        @Override
        public boolean areItemsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }
}
