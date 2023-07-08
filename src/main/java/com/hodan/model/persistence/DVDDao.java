package com.hodan.model.persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hodan.dto.entity.DVD;
@Transactional
@Repository
public interface DVDDao extends JpaRepository<DVD, String> {
	@Modifying
	@Query("update DVD set userRating = :newRating where title = :title")
	int updateUserRatingByTitle(@Param("title") String title, @Param("newRating") String newRating);
	
	@Modifying
	@Query(value = "insert into DVD values(?,?,?,?,?,?)",nativeQuery = true)
	int insertDVD(String title,LocalDate releaseDate,String mpaaRating,String director,String studio, String UserRating);

}
