package com.hodan.model.service;

import java.util.List;
import com.hodan.dto.entity.DVD;

public interface DVDService {

	DVD getDVDByTitle(String title);


	boolean addDVD(DVD dVD);


	List<DVD> getAllDVDS();


	boolean deleteDVDByTitle(String title);


	boolean updateUserRating(String title, String newRating);
}