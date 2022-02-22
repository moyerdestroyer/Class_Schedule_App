package com.capstone.classschedule.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.capstone.classschedule.DAO.ScheduleRepository;
import com.capstone.classschedule.Model.Course;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class CourseViewModel extends AndroidViewModel {
    private ScheduleRepository repo;
    private final MutableLiveData<String> searchInput = new MutableLiveData<>();
    private final LiveData<List<Course>> allCourses;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        repo = new ScheduleRepository(application);
        allCourses = Transformations.switchMap(searchInput, (input -> {
            if (input == null || input.equals("")) {
                return repo.getAllCourses();
            } else {
                try {
                    return repo.filter(input).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                    return repo.getAllCourses();
                }
            }
        }));
    }

    public void setSearchInput(String query) {
        searchInput.setValue(query);
    }

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    public void insert(Course course) {
        repo.insert(course);
    }

    public void update(int idInt, Course saveCourse) {
        repo.updateCourse(idInt, saveCourse);
    }

    public void deleteById(int id){
        repo.deleteCourseById(id);
    }

    public Course getLastCreated() throws ExecutionException, InterruptedException {
        return repo.getLastCreated().get();
    }
    public void updateAssessmentCount() {
        for(int i = 0; i < Objects.requireNonNull(allCourses.getValue()).size(); i++) {
            int courseId = allCourses.getValue().get(i).getId();
            try {
                repo.updateAssessmentCount(courseId, repo.countOfAssessments(courseId).get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
