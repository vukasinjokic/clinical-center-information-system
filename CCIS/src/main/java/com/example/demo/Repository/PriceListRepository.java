package com.example.demo.Repository;

import com.example.demo.model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceListRepository extends JpaRepository<PriceList, Integer> {
}
