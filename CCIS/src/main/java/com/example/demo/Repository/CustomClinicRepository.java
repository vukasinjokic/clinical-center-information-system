package com.example.demo.Repository;

import com.example.demo.ClinicalCenterInformationSystemApplication;
import com.example.demo.dummy.DummyClinicClass;
import com.example.demo.model.Clinic;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Repository("CustomClinicRepository")
public abstract class CustomClinicRepository implements ClinicRepository {
//    @Override
//    public Clinic findById(Integer id) {
//        System.out.println("nemanja");
//        EntityManager manager = ClinicalCenterInformationSystemApplication.managerFactory.createEntityManager();
//        Query q = manager.createQuery("select c from Clinic c where c.id = ?1");
//        q.setParameter(1, id);
//        List<Clinic> clinics = q.getResultList();
//
//        manager.close();
//
//        System.out.println("nemanja");
//        if (clinics.size() == 1)
//            return clinics.get(0);
//        else
//            return null;
//    }
//
//    @Override
//    public Set<Clinic> getAllClinics() {
//        return DummyClinicClass.getAllClinics();
//    }
}
