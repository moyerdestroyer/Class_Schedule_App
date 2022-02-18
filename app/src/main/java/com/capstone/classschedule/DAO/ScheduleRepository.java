package com.capstone.classschedule.DAO;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.capstone.classschedule.Model.Assessment;
import com.capstone.classschedule.Model.Course;

import java.util.List;
import java.util.concurrent.Future;

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
    public void insert(Course course) {
        ScheduleRoomDatabase.databaseWriteExecutor.execute(() ->
                courseDAO.insert(course.getTitle(), course.getStart(), course.getEnd(), course.getInstructor(), course.getInstructorEmail(), course.getNote(), course.getComplete()));
    }
    public void updateCourse(int idInt, Course course) {
        ScheduleRoomDatabase.databaseWriteExecutor.execute(() ->
                courseDAO.updateCourse(idInt, course.getTitle(), course.getStart(), course.getEnd(), course.getInstructor(), course.getInstructorEmail(), course.getNote(), course.getComplete()));
    }
    public void deleteCourseById(int id) {
        ScheduleRoomDatabase.databaseWriteExecutor.execute(() ->
                courseDAO.deleteCourseById(id));
    }
    //FILTER STUFF
    public Future<LiveData<List<Course>>> filter(String input) {
        return ScheduleRoomDatabase.databaseWriteExecutor.submit(() ->
                courseDAO.filter(input));
    }

    /*
     * ASSESSMENT SECTION
     */
    public LiveData<List<Assessment>> getAllAssessments() {
        return allAssessments;
    }

    public Future<LiveData<List<Assessment>>> getAssessmentByCourseId(int id) {
        return ScheduleRoomDatabase.databaseWriteExecutor.submit(() ->
                assessmentDAO.getAssessmentsByCourseId(id));
    }

    public void insertAssessment(Assessment assessment) {
        ScheduleRoomDatabase.databaseWriteExecutor.execute(() ->
                assessmentDAO.insertAssessment(assessment.getType(), assessment.getCourseId(), assessment.getTitle(), assessment.getDescription()));
    }

    public void updateNegativeOnes(int negative, int courseId) {
        ScheduleRoomDatabase.databaseWriteExecutor.execute(() ->
                assessmentDAO.updateNegativeOnes(negative, courseId));
    }
}
