package ru.itis.rest_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rest_api.models.Lesson;

public interface LessonsRepository extends JpaRepository<Lesson, Long> {
}
