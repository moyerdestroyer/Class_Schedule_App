package com.capstone.classschedule.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.capstone.classschedule.Model.Assessment;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Query("SELECT * FROM assessments")
    LiveData<List<Assessment>> getAllAssessments();

    //TODO ADD MORE STUFF
}
