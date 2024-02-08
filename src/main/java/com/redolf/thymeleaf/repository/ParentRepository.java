package com.redolf.thymeleaf.repository;

import com.redolf.thymeleaf.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository  extends JpaRepository<Parent, Integer> {
}