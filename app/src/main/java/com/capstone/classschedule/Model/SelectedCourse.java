package com.capstone.classschedule.Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SelectedCourse extends ViewModel {
    private final MutableLiveData<Course> selectedCourse = new MutableLiveData<Course>();

    public void setSelectedCourse(Course course) {
        selectedCourse.setValue(course);
    }
    public LiveData<Course> getSelectedCourse() {
        return selectedCourse;
    }
}
