package ru.itis.rest_api.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.rest_api.dto.TeacherDto;
import ru.itis.rest_api.services.TeachersService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class TeachersControllers {
    @Autowired
    private TeachersService teachersService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherDto>> getTeachers(@CookieValue(value = "bucketId", required = false) String bucketId, HttpServletResponse response) {
        return ResponseEntity.ok(teachersService.getAllTeachers());
    }

    @ApiOperation(value = "Добавление педагога")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено", response = TeacherDto.class)})
    @PostMapping("/teachers")
    public ResponseEntity<TeacherDto> addTeacher(@RequestBody TeacherDto teacher) {
        return ResponseEntity.ok(teachersService.addTeacher(teacher));
    }

    @PutMapping("/teachers/{teacher-id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable("teacher-id") Long teacherId, @RequestBody TeacherDto teacher) {
        return ResponseEntity.ok(teachersService.updateTeacher(teacherId, teacher));
    }

    @DeleteMapping("/teachers/{teacher-id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable("teacher-id") Long teacherId) {
        teachersService.deleteTeacher(teacherId);
        return ResponseEntity.ok().build();
    }

}
