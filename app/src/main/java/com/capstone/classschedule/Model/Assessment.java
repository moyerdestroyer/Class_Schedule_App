package com.capstone.classschedule.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "assessments")
public class Assessment {
    private int id;
    private int type;
    private String title;
    private String description;
    @ColumnInfo(name ="createdTime", defaultValue = "CURRENT_TIMESTAMP")
    private String createdTime;

    //TODO FINISH THIS
}
