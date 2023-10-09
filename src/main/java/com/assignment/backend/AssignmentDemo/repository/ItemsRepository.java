package com.assignment.backend.AssignmentDemo.repository;

import com.assignment.backend.AssignmentDemo.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, Integer> {
    // This interface provides data access methods for the Item entity
}
