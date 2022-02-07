package com.capstone.classschedule.DAO;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.capstone.classschedule.Model.Assessment;
import com.capstone.classschedule.Model.Course;

import java.util.List;

public class ScheduleRepository {
    private CourseDAO courseDAO;
    private AssessmentDAO assessmentDAO;
    private LiveData<List<Course>> allCourses;
    private LiveData<List<Assessment>> allAssessments;

    public ScheduleRepository(Application application) {
        ScheduleRoomDatabase db = ScheduleRoomDatabase.getDatabase(application);
        courseDAO = db.courseDAO();
        allCourses = courseDAO.getAllCourses();
        assessmentDAO = db.assessmentDAO();
        allAssessments = assessmentDAO.getAllAssessments();
    }

    /*
     * COURSE SECTION
     */
    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    /*
     * ASSESSMENT SECTION
     */
    public LiveData<List<Assessment>> getAllAssessments() {
        return allAssessments;
    }

    //TODO FINISH THIS

}
