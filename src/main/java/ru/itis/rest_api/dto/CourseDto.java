package ru.itis.rest_api.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseDto {
    private String title;
    private List<String> teachers;

}
