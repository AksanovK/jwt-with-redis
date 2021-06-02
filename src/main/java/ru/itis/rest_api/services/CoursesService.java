package ru.itis.rest_api.services;

import ru.itis.rest_api.dto.CourseDto;
import ru.itis.rest_api.dto.TeacherDto;
import ru.itis.rest_api.models.Course;

import java.util.List;

public interface CoursesService {

    List<Course> getAllCourses();

    Course addCourse(CourseDto course);

    Course addTeacherIntoCourse(Long courseId, TeacherDto teacher);

}
