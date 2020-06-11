package com.example.demo.Repository;
import com.example.demo.model.MedicalStaffRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalStaffRequestRepository extends JpaRepository<MedicalStaffRequest, Integer> {

//    @Query("delete from MedicalStaffRequest req where req.id=:id")
//    void deleteRequest(@Param("id") Integer id);
}
