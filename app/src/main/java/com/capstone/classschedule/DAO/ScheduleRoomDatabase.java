package com.capstone.classschedule.DAO;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.capstone.classschedule.Model.Assessment;
import com.capstone.classschedule.Model.Course;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Course.class, Assessment.class}, version = 1)
public abstract class ScheduleRoomDatabase extends androidx.room.RoomDatabase {
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();
    private static final int NUMBER_OF_THREADS = 4;
    private static ScheduleRoomDatabase INSTANCE;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ScheduleRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (ScheduleRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ScheduleRoomDatabase.class, "schedule_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
