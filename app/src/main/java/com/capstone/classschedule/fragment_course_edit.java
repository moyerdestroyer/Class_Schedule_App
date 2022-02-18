package com.capstone.classschedule;

import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.capstone.classschedule.Adapters.AssessmentAdapter;
import com.capstone.classschedule.Model.Course;
import com.capstone.classschedule.Model.SelectedCourse;
import com.capstone.classschedule.ViewModels.AssessmentViewModel;

import org.w3c.dom.Text;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_course_edit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_course_edit extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public fragment_course_edit() {
    }
    public static fragment_course_edit newInstance(String param1, String param2) {
        fragment_course_edit fragment = new fragment_course_edit();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_course_edit, container, false);
        SelectedCourse viewModel = new ViewModelProvider(requireActivity()).get(SelectedCourse.class);
        AssessmentViewModel assessmentViewModel = new ViewModelProvider(requireActivity()).get(AssessmentViewModel.class);
        RecyclerView assessmentRecycler = rootView.findViewById(R.id.fragment_course_assessment_recyclerview);
        final AssessmentAdapter adapter = new AssessmentAdapter(new AssessmentAdapter.AssessmentDiff());
        assessmentRecycler.setAdapter(adapter);
        assessmentRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        assessmentViewModel.getAllAssessments().observe(requireActivity(), adapter::submitList);

        Course thiscourse;
        if(viewModel.getSelectedCourse().getValue() != null) {
            thiscourse = viewModel.getSelectedCourse().getValue();
            TextView id = rootView.findViewById(R.id.fragment_course_id_textview);
            EditText title = rootView.findViewById(R.id.fragment_course_title_edittext);
            EditText startDate = rootView.findViewById(R.id.fragment_course_start_date_edittext);
            EditText endDate = rootView.findViewById(R.id.fragment_course_end_date_edittext);
            EditText instructor = rootView.findViewById(R.id.fragment_course_instructor_edittext);
            EditText instructorEmail = rootView.findViewById(R.id.fragment_course_instructor_email_edittext);
            EditText note = rootView.findViewById(R.id.fragment_course_note_edittext);
            SwitchCompat complete = rootView.findViewById(R.id.fragment_course_complete_switch);

            //Set values
            id.setText(String.valueOf(thiscourse.getId()));
            title.setText(thiscourse.getTitle());
            startDate.setText(thiscourse.getStart());
            endDate.setText(thiscourse.getEnd());
            instructor.setText(thiscourse.getInstructor());
            instructorEmail.setText(thiscourse.getInstructorEmail());
            note.setText(thiscourse.getNote());
            //If not zero, set checked
            complete.setChecked(thiscourse.getComplete() != 0);
            //update assessments
            assessmentViewModel.setSelectedId(thiscourse.getId());
        } else {
            assessmentViewModel.setSelectedId(null);
        }
        return rootView;
    }
}