package com.capstone.classschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.capstone.classschedule.Adapters.CourseReportAdapter;
import com.capstone.classschedule.ViewModels.CourseReportViewHolder;
import com.capstone.classschedule.ViewModels.CourseViewModel;

public class ReportActivity extends AppCompatActivity {
    CourseViewModel courseViewModel;
    RecyclerView courseRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        courseRecycler = findViewById(R.id.report_activity_recyclerview);
        final CourseReportAdapter adapter = new CourseReportAdapter(new CourseReportAdapter.CourseDiff());
        courseRecycler.setAdapter(adapter);
        courseRecycler.setLayoutManager(new LinearLayoutManager(this));
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, adapter::submitList);
        courseViewModel.setSearchInput("");
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.report_menu, menu);
        return true;
    }
    public void goToCourseActivity(MenuItem item) {
        Intent courseIntent = new Intent(getApplicationContext(), CourseActivity.class);
        startActivity(courseIntent);
    }
}