package com.example.demo.Repository;

import com.example.demo.model.PriceListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PriceListItemRepository extends JpaRepository<PriceListItem, Integer> {

    Optional<PriceListItem> findByExaminationTypeId(@Param("id") Integer id);

}
