package ru.itis.rest_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.rest_api.dto.CourseDto;
import ru.itis.rest_api.dto.TeacherDto;
import ru.itis.rest_api.models.Course;
import ru.itis.rest_api.models.Teacher;
import ru.itis.rest_api.repositories.CoursesRepository;
import ru.itis.rest_api.repositories.TeachersRepository;

import java.util.List;

@Service
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private TeachersRepository teachersRepository;

    @Override
    public List<Course> getAllCourses() {
        return coursesRepository.findAll();
    }

    @Override
    public Course addCourse(CourseDto course) {
        return coursesRepository.save(Course.builder()
                .title(course.getTitle())
                .build());
    }

    @Override
    public Course addTeacherIntoCourse(Long courseId, TeacherDto teacher) {
        Course course = coursesRepository.findById(courseId)
                .orElseThrow(IllegalArgumentException::new);
        Teacher teacherForCourse = teachersRepository.findById(teacher.getId())
                .orElseThrow(IllegalArgumentException::new);

        course.getTeachers().add(teacherForCourse);
        coursesRepository.save(course);
        return course;
    }

}
