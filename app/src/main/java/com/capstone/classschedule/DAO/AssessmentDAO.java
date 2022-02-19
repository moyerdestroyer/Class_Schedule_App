package com.capstone.classschedule.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.capstone.classschedule.Model.Assessment;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Query("SELECT * FROM assessments")
    LiveData<List<Assessment>> getAllAssessments();

    @Query("SELECT * FROM assessments WHERE courseId = :id OR courseId = -1")
    LiveData<List<Assessment>> getAssessmentsByCourseId(int id);

    @Query("INSERT INTO assessments (type, courseId, title, description) VALUES(:type, :courseId, :title, :description)")
    void insertAssessment(int type, int courseId, String title, String description);

    @Delete
    void delete(Assessment assessment);

    @Query("UPDATE assessments SET courseId = :courseId WHERE courseId = :negative")
    void updateNegativeOnes(int negative, int courseId);

    @Query("SELECT COUNT(id) FROM assessments WHERE courseId = :courseId GROUP BY courseId ")
    int countOfAssessments(int courseId);
}
