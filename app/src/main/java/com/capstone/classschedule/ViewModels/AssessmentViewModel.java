package com.capstone.classschedule.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.capstone.classschedule.DAO.ScheduleRepository;
import com.capstone.classschedule.Model.Assessment;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AssessmentViewModel extends AndroidViewModel {
    private ScheduleRepository repo;
    private final MutableLiveData<Integer> selectedCourseId = new MutableLiveData<>();
    private final LiveData<List<Assessment>> allAssessments;

    public AssessmentViewModel(@NonNull Application application) {
        super(application);
        repo = new ScheduleRepository(application);
        allAssessments = Transformations.switchMap(selectedCourseId, (input -> {
            if(input == null || input == -1) {
                return repo.getAllAssessments();
            } else {
                try {
                    return repo.getAssessmentByCourseId(input).get();
                } catch (Exception e) {
                    e.printStackTrace();
                    return repo.getAllAssessments();
                }
            }
        }));
    }
    public void setSelectedId(int id){
        selectedCourseId.setValue(id);
    }
    public LiveData<List<Assessment>> getAllAssessments() {
        return allAssessments;
    }
    public void insertAssessment(Assessment assessment) {
        repo.insertAssessment(assessment);
    }

    public void updateNegativeOnes(int negative, int courseId) {
        repo.updateNegativeOnes(negative, courseId);
    }
}
