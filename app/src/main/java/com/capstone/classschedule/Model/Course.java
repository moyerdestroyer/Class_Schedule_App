package com.capstone.classschedule.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "start")
    private String start;
    @ColumnInfo(name = "end")
    private String end;
    @ColumnInfo(name = "instructor")
    private String instructor;
    @ColumnInfo(name = "instructorEmail")
    private String instructorEmail;
    @ColumnInfo(name = "note")
    private String note;
    @ColumnInfo(name = "complete")
    private int complete;
    @ColumnInfo(name ="createdTime", defaultValue = "CURRENT_TIMESTAMP")
    String createdTime;
    @ColumnInfo(name = "lastModifiedTime", defaultValue = "CURRENT_TIMESTAMP")
    String lastModifiedTime;

    public Course(String title, String start, String end, String instructor, String instructorEmail, String note, int complete) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.instructor = instructor;
        this.instructorEmail = instructorEmail;
        this.note = note;
        this.complete = complete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(String lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
