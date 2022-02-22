package com.capstone.classschedule;

import com.capstone.classschedule.Validators.CourseValidator;

import org.junit.Test;
import org.junit.Assert;

public class CourseActivityTest {

    @Test
    public void courseValidate() {
        //TODO REDO these tests
        Assert.assertTrue(CourseValidator.courseValidate(
                "Course Title",
                "John Smith",
                "jsmith@school.edu",
                "Note for the course."));
        Assert.assertFalse(CourseValidator.courseValidate(
                "",
                "",
                "",
                ""));
        Assert.assertFalse(CourseValidator.courseValidate(
                "My title",
                "Jimbo",
                "beeep",
                "1234567890!"));
    }
}