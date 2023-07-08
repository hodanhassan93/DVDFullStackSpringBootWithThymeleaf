package com.hodan.dto.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DVD {

	@Id
	private String title;
	@Column(name="releaseDate")
	private LocalDate releaseDate;
	@Column(name="mpaaRating")
	private String mpaaRating;
	@Column(name="director")
	private String director;
	@Column(name="studio")
	private String studio;
	@Column(name="userRating")
	private String userRating;
}


