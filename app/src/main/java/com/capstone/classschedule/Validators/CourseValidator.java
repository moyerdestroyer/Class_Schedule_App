package com.capstone.classschedule.Validators;

import java.util.regex.Pattern;

public class CourseValidator {
    public static boolean courseValidate(String title, String instructor, String instructorEmail, String note) {
        final Pattern alphanumericPattern = Pattern.compile("^[a-zA-Z0-9_]+$");
        final Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        if (!alphanumericPattern.matcher(title).matches()) {
            return false;
        }
        if (!alphanumericPattern.matcher(instructor).matches()) {
            return false;
        }
        if (!alphanumericPattern.matcher(note).matches()) {
            return false;
        }
        return emailPattern.matcher(instructorEmail).matches();
    }
}
