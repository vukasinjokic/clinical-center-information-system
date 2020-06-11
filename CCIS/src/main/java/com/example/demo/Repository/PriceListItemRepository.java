package com.example.demo.Repository;

import com.example.demo.model.PriceListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceListItemRepository extends JpaRepository<PriceListItem, Integer> {
}
