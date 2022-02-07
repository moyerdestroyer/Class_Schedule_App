package com.capstone.classschedule.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.capstone.classschedule.DAO.ScheduleRepository;
import com.capstone.classschedule.Model.Course;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {
    private ScheduleRepository repo;
    private final LiveData<List<Course>> allCourses;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        repo = new ScheduleRepository(application);
        allCourses = repo.getAllCourses();
    }
    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    //TODO add more methods
}
