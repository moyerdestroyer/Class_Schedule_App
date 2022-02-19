package com.capstone.classschedule.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.capstone.classschedule.Model.Course;

import java.util.List;

@Dao
public interface CourseDAO {
    @Query("SELECT * FROM courses")
    LiveData<List<Course>> getAllCourses();

    @Query("INSERT INTO courses (title, startDate, endDate, instructor, instructorEmail, note, complete) VALUES (:title, :startDate, :endDate, :instructor, :instructorEmail, :note, :complete)")
    void insert(String title, String startDate, String endDate, String instructor, String instructorEmail, String note, int complete);

    @Query("UPDATE courses SET title = :title, startDate = :startDate, endDate = :endDate, instructor = :instructor, instructorEmail = :instructorEmail, note = :note, complete = :complete, lastModifiedTime = CURRENT_TIMESTAMP WHERE id = :idInt")
    void updateCourse(int idInt, String title, String startDate, String endDate, String instructor, String instructorEmail, String note, int complete);

    @Query("SELECT * FROM courses WHERE LOWER(title) LIKE '%' || :input || '%'")
    LiveData<List<Course>> filter(String input);

    @Query("DELETE FROM courses WHERE id = :id")
    void deleteCourseById(int id);

    @Query("SELECT * FROM courses ORDER BY id DESC LIMIT 1")
    Course getLastCreated();
}
