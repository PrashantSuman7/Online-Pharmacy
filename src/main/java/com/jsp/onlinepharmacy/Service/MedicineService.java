package com.jsp.onlinepharmacy.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.MedicalStoreDao;
import com.jsp.onlinepharmacy.dao.MedicineDao;
import com.jsp.onlinepharmacy.dto.MedicineDto;
import com.jsp.onlinepharmacy.entity.MedicalStore;
import com.jsp.onlinepharmacy.entity.Medicine;
import com.jsp.onlinepharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.onlinepharmacy.exception.MedicineIdNotFounded;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class MedicineService {
	
	@Autowired
	private MedicineDao medicineDao;
	
	@Autowired
	private MedicalStoreDao storeDao;

	public ResponseEntity<ResponseStructure<Medicine>> saveMedicine(int storeId, Medicine medicine) {
		MedicalStore dbMedicalStore=storeDao.findMedicalStoreById(storeId);	
		if(dbMedicalStore!=null)
		{
			medicine.setMedicalStore(dbMedicalStore);
			Medicine dbmedicine=medicineDao.saveMedicine(medicine);	
			ResponseStructure<Medicine> structure=new ResponseStructure<>();
			structure.setMessage("Medicine Stored Successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dbmedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.CREATED);
		}
		else
		{
			throw new MedicalStoreIdNotFoundException("Sorry ! Please Check Your Medical Store Id");
		}
	}

	public ResponseEntity<ResponseStructure<Medicine>> updateMedicine(int medicineId, Medicine medicine) {
		Medicine dbMedicine=medicineDao.updateMedicine(medicineId,medicine);
		if(dbMedicine!=null)
		{
			
			ResponseStructure<Medicine> structure=new ResponseStructure<>();
			structure.setMessage("Medicine Update Successfully ");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.OK);
		}
		else
		{
			throw new MedicineIdNotFounded("Sorry Medicine Id not Founded");
		}
		
	}

	public ResponseEntity<ResponseStructure<Medicine>> getMedicine(int medicineId) {
		Medicine dbMedicine=medicineDao.getMedicine(medicineId);
		if(dbMedicine!=null)
		{
			
			ResponseStructure<Medicine> structure=new ResponseStructure<>();
			structure.setMessage("Medicine Fetch Successfully ");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.OK);
		}
		else
		{
			throw new MedicineIdNotFounded("Sorry Medicine Id not Founded");
		}
		
	}

	public ResponseEntity<ResponseStructure<Medicine>> deleteMedicine(int medicineId) {
		Medicine dbMedicine=medicineDao.deleteMedicine(medicineId);
		if(dbMedicine!=null)
		{
			
			ResponseStructure<Medicine> structure=new ResponseStructure<>();
			structure.setMessage("Medicine Deleted Successfully ");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.GONE);
		}
		else
		{
			throw new MedicineIdNotFounded("Sorry Medicine Id not Founded");
		}
	}

	public List<Medicine> getAllMedicine() {
		List<Medicine> medicines=new ArrayList<>();
		List<Medicine> dbmedicine=medicineDao.findAllMedicine();
		for (Medicine medicine : dbmedicine) {
			medicines.add(medicine);	
		}
		return medicines;
		
	}

}
