package com.jsp.onlinepharmacy.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jsp.onlinepharmacy.entity.Medicine;
import com.jsp.onlinepharmacy.repo.MedicineRepo;

@Repository
public class MedicineDao {
	
	@Autowired
	private MedicineRepo repo;

	public Medicine saveMedicine(Medicine medicine) {
		
		return repo.save(medicine);
	}

	public Medicine updateMedicine(int medicineId, Medicine medicine) {
		Optional<Medicine> optional=repo.findById(medicineId);
		if(optional!=null)
		{
			Medicine oldMedicine=optional.get();
			medicine.setMedicineId(medicineId);
			medicine.setMedicalStore(oldMedicine.getMedicalStore());
			return repo.save(medicine);
		}
		return null;
		
		
	}

	public Medicine getMedicine(int medicineId) {
		if(repo.findById(medicineId).isPresent())
		{
			return repo.findById(medicineId).get();
		}
		return null;
	}

	public Medicine deleteMedicine(int medicineId) {
		 Optional<Medicine> optional=repo.findById(medicineId);
		 if(optional.isPresent())
		 {
			Medicine medicine= optional.get();
			 repo.delete(medicine);
			 return medicine;
		 }
		 return null;
	}

	public List<Medicine> findAllMedicine() {
		return repo.findAll();
	}

	

}
