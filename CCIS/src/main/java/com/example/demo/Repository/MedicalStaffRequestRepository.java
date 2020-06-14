package com.example.demo.Repository;
import com.example.demo.model.MedicalStaffRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

public interface MedicalStaffRequestRepository extends JpaRepository<MedicalStaffRequest, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select r from MedicalStaffRequest r where r.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    Optional<MedicalStaffRequest> findById(Integer id);
}
