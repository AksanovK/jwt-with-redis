package ru.itis.rest_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rest_api.models.Teacher;

import java.util.List;

public interface TeachersRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findAllByIsDeletedIsNull();
}
