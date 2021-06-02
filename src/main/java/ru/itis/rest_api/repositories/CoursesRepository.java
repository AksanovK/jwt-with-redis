package ru.itis.rest_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rest_api.models.Course;

public interface CoursesRepository extends JpaRepository<Course, Long> {
}
