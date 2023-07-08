package com.hodan.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hodan.dto.entity.DVD;
import com.hodan.model.persistence.DVDDao;

@Service
public class DVDServiceImpl implements DVDService {

	@Autowired
	private DVDDao dVDDao;
	
	
	@Override
	public DVD getDVDByTitle(String title) {
		
		return dVDDao.findById(title).orElse(null);
	}

	@Override
	public List<DVD> getAllDVDS() {
		return dVDDao.findAll();
	}

	
	@Override
	public boolean addDVD(DVD dVD) {
		try {
		if(dVDDao.insertDVD(dVD.getTitle(), 
				dVD.getReleaseDate(), 
				dVD.getMpaaRating(), dVD.getDirector(), dVD.getStudio(), dVD.getUserRating())>0)
			return true;
		}
		catch(Exception ex) {
			return false;
		}
		return false;
	}

	@Override
	public boolean deleteDVDByTitle(String title) {
		DVD dVDs=getDVDByTitle(title);
		if(dVDs!=null) {
			dVDDao.deleteById(title);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUserRating(String title, String newRating) {
		
		if(dVDDao.updateUserRatingByTitle(title, newRating)>0)
			return true;
		else
			return false;
	}
}

