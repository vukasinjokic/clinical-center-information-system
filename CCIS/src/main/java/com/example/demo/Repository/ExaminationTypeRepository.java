package com.example.demo.Repository;

import com.example.demo.model.ExaminationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExaminationTypeRepository extends JpaRepository<ExaminationType,Integer> {

    ExaminationType findByName(String name);

    @Query("select count(type) from ExaminationType type where type.id <> :id and type.name = :newName ")
    int findUsedName(@Param("id") Integer id, @Param("newName") String newName);
}
