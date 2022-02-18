package com.capstone.classschedule.Adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.capstone.classschedule.Model.Assessment;
import com.capstone.classschedule.ViewModels.AssessmentViewHolder;

public class AssessmentAdapter extends ListAdapter<Assessment, AssessmentViewHolder> {
    public AssessmentAdapter(@NonNull DiffUtil.ItemCallback<Assessment> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AssessmentViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
        Assessment current = getItem(position);
        holder.bind(current);
    }
    public static class AssessmentDiff extends DiffUtil.ItemCallback<Assessment> {

        @Override
        public boolean areItemsTheSame(@NonNull Assessment oldItem, @NonNull Assessment newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Assessment oldItem, @NonNull Assessment newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }
}
