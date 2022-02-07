package com.capstone.classschedule.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.capstone.classschedule.Model.Course;

import java.util.List;

@Dao
public interface CourseDAO {
    @Query("SELECT * FROM courses")
    LiveData<List<Course>> getAllCourses();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Course course);

    //TODO ADD MORE FUnctions
}
