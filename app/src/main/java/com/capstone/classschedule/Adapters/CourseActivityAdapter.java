package com.capstone.classschedule.Adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.capstone.classschedule.Model.Course;
import com.capstone.classschedule.ViewModels.CourseActivityViewHolder;

public class CourseActivityAdapter extends ListAdapter<Course, CourseActivityViewHolder> {
    protected CourseActivityAdapter(@NonNull DiffUtil.ItemCallback<Course> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CourseActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CourseActivityViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseActivityViewHolder holder, int position) {
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
