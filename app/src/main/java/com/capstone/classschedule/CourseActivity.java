package com.capstone.classschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.classschedule.Adapters.CourseActivityAdapter;
import com.capstone.classschedule.Dialogs.AddAssessmentFragment;
import com.capstone.classschedule.Dialogs.DatePickerFragment;
import com.capstone.classschedule.Model.Assessment;
import com.capstone.classschedule.Model.Course;
import com.capstone.classschedule.Model.SelectedCourse;
import com.capstone.classschedule.Validators.CourseValidator;
import com.capstone.classschedule.ViewModels.AssessmentViewModel;
import com.capstone.classschedule.ViewModels.CourseViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseActivity extends AppCompatActivity {
    public CourseViewModel courseViewModel;
    public SelectedCourse selectedCourse;
    public AssessmentViewModel assessmentViewModel;
    ArrayList<Assessment> selectedAssessments;
    RecyclerView courseRecycler;
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        courseRecycler = findViewById(R.id.course_activity_recyclerview);
        courseRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        final CourseActivityAdapter adapter = new CourseActivityAdapter(new CourseActivityAdapter.CourseDiff());
        courseRecycler.setAdapter(adapter);
        courseRecycler.setLayoutManager(new LinearLayoutManager(this));
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, adapter::submitList);

        //Selected Course
        selectedCourse = new ViewModelProvider(this).get(SelectedCourse.class);

        //AssessmentViewModel
        assessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);

        //Searchview Setup
        SearchView searchView = findViewById(R.id.course_activity_searchview);
        courseViewModel.setSearchInput("");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                courseViewModel.setSearchInput(newText);
                return false;
            }
        });
        Objects.requireNonNull(getSupportActionBar()).setTitle("My Courses");
    }

    @Override
    public void onBackPressed() {
        try {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
            super.onBackPressed();
        }
    }

    /*
    MENU FUNCTIONS
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_menu, menu);
        return true;
    }
    public void AddCourseFragment(MenuItem item) {
        selectedCourse.setSelectedCourse(null);
        selectedAssessments = new ArrayList<>();
        currentFragment = new fragment_course_edit();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.course_activity_fragment, currentFragment).commit();
    }
    public void GoToReportActivity(MenuItem item) {
        courseViewModel.updateAssessmentCount();
        Intent reportIntent = new Intent(getApplicationContext(), ReportActivity.class);
        startActivity(reportIntent);
    }

    /*
    DIALOG FUNCTIONS
     */
    //Date Picker
    public void showDatePickerDialog(View v) {
        //Assumes no other datepicker fragments
        if(v.getId() == R.id.fragment_course_start_date_button) {
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "startPicker");
        } else {
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "endPicker");
        }
    }
    public void showAddAssessmentDialog(View v) {
        //Get id for the tag
        TextView id = (TextView) findViewById(R.id.fragment_course_id_textview);
        String idText = id.getText().toString();
        DialogFragment newFragment = new AddAssessmentFragment();
        newFragment.show(getSupportFragmentManager(), idText);
    }
    public void positiveAssessmentClick(int id, int type, String title, String note) {
        Assessment createdAssessment = new Assessment(type, id, title, note);
        assessmentViewModel.insertAssessment(createdAssessment);
    }


    /*
    FRAGMENT
     */
    //GOTO DETAILED COURSE VIEW
    public void courseDetailView(Course course) {
        selectedAssessments = new ArrayList<>();
        selectedCourse.setSelectedCourse(course);
        currentFragment = new fragment_course_edit();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.course_activity_fragment, currentFragment).commit();
    }
    //SAVE COURSE
    public void SaveCourse(View view){
        //Get fields
        TextView id = findViewById(R.id.fragment_course_id_textview);
        EditText title = findViewById(R.id.fragment_course_title_edittext);
        EditText startDate = findViewById(R.id.fragment_course_start_date_edittext);
        EditText endDate = findViewById(R.id.fragment_course_end_date_edittext);
        EditText instructor = findViewById(R.id.fragment_course_instructor_edittext);
        EditText instructorEmail = findViewById(R.id.fragment_course_instructor_email_edittext);
        EditText note = findViewById(R.id.fragment_course_note_edittext);
        SwitchCompat complete = findViewById(R.id.fragment_course_complete_switch);
        //Get Values
        String titleString = title.getText().toString();
        String startDateString = startDate.getText().toString();
        String endDateString = endDate.getText().toString();
        String instructorString = instructor.getText().toString();
        String instructorEmailString = instructorEmail.getText().toString();
        String noteString = note.getText().toString();

        int completeInt = 0;
        if (complete.isChecked()) {completeInt = 1;}
        int idInt;

        if (!CourseValidator.courseValidate(titleString, instructorString, instructorEmailString, noteString)) {
            Toast.makeText(getApplicationContext(), "Check Title, Instructor Name, and Email for Errors!", Toast.LENGTH_LONG).show();
        } else {
            Course saveCourse = new Course(titleString, startDateString, endDateString, instructorString, instructorEmailString, noteString, completeInt);
            if (id.getText().toString().equals("Add Course???")) {
                //Create new, don't update
                courseViewModel.insert(saveCourse);
                //we need to get id of the just saved course
                try {
                    selectedCourse.setSelectedCourse(courseViewModel.getLastCreated());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Unable to save Assessments", Toast.LENGTH_LONG).show();
                }
                if(selectedCourse.getSelectedCourse().getValue() != null) {
                    assessmentViewModel.updateNegativeOnes(-1, selectedCourse.getSelectedCourse().getValue().getId());
                }
                Toast.makeText(getApplicationContext(), "Course Created Successfully", Toast.LENGTH_LONG).show();
            } else {
                //update by id
                idInt = Integer.parseInt(id.getText().toString());
                courseViewModel.update(idInt, saveCourse);
                Toast.makeText(getApplicationContext(), "Course Updated", Toast.LENGTH_LONG).show();
            }
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
        }
    }

    //DELETE COURSE
    public void DeleteCourse(View view) {
        int idInt;
        TextView id = findViewById(R.id.fragment_course_id_textview);
        if(id.getText().toString().equals("Add Course???")) {
            Toast.makeText(getApplicationContext(), "Course Creation Cancelled", Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
        } else {
            idInt = Integer.parseInt(id.getText().toString());
            if(assessmentViewModel.countOfAssessments(idInt) > 0) {
                Toast.makeText(getApplicationContext(), "Please Delete Assessments Before Deleting Course", Toast.LENGTH_LONG).show();
            } else {
                courseViewModel.deleteById(idInt);
                getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
                Toast.makeText(getApplicationContext(), "Course Deleted", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void DeleteAssessment(View view) {
        for(int i = 0; i<selectedAssessments.size(); i++) {
            assessmentViewModel.deleteAssessment(selectedAssessments.get(i));
        }
        Toast.makeText(getApplicationContext(), "Selected Assessments Deleted", Toast.LENGTH_LONG).show();
    }

    public void setSelectedAssessment(Assessment assessment) {
        if (selectedAssessments != null && selectedAssessments.contains(assessment)) {
            selectedAssessments.remove(assessment);
        } else {
            assert selectedAssessments != null;
            selectedAssessments.add(assessment);
        }
    }
}