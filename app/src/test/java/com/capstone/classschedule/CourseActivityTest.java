package com.capstone.classschedule;

import com.capstone.classschedule.Validators.CourseValidator;

import org.junit.Test;
import org.junit.Assert;

public class CourseActivityTest {

    @Test
    public void goodCourse() {
        Assert.assertTrue(CourseValidator.courseValidate(
                "Course Title",
                "John Smith",
                "jsmith@school.edu",
                "Note for the course."));
    }
    @Test
    public void emptyTitle() {
        Assert.assertFalse(CourseValidator.courseValidate(
                "",
                "John Smith",
                "example@example.com",
                "John Smith has a note"));
    }
    @Test
    public void badEmail() {
        Assert.assertFalse(CourseValidator.courseValidate(
                "My title",
                "Jimbo",
                "test",
                "1234567890!"));
    }
    @Test
    public void nonAlpha() {
        Assert.assertFalse(CourseValidator.courseValidate(
                "title",
                "↓φ⌂",
                "test@test.com",
                "test note"));
    }
}