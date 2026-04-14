package com.edsoft.ed.soft.repository;

import com.edsoft.ed.soft.model.BeachMenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<BeachMenuItems, Long> {
    BeachMenuItems findOneById(Long id);
}
