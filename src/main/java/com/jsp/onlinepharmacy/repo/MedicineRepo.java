package com.jsp.onlinepharmacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.onlinepharmacy.entity.Medicine;

public interface MedicineRepo extends JpaRepository<Medicine, Integer> {

}
