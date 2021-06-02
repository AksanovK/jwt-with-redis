package ru.itis.rest_api.services;

import ru.itis.rest_api.dto.TeacherDto;

import java.util.List;

public interface TeachersService {

    List<TeacherDto> getAllTeachers();

    TeacherDto addTeacher(TeacherDto teacher);

    TeacherDto updateTeacher(Long teacherId, TeacherDto teacher);

    void deleteTeacher(Long teacherId);

}
