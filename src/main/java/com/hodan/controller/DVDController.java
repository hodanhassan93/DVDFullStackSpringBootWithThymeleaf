package com.hodan.controller;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hodan.dto.entity.DVD;
import com.hodan.model.service.DVDService;

@Controller
public class DVDController {
	
	@Autowired
	private DVDService dVDService;
	
	@RequestMapping("/")
	public ModelAndView welcomePageController() {
		return new ModelAndView("index");
	}

	@RequestMapping("/InputDVDTitlePage")
	public ModelAndView InputDVDTitlePageController() {
		return new ModelAndView("InputDVDTitle");
	}
	
	@RequestMapping("/searchDVDByTitle")
	public ModelAndView searchDVDByTitleController(HttpServletRequest request) {
		ModelAndView modelAndView=new ModelAndView();
		DVD dVD=dVDService.getDVDByTitle(request.getParameter("title"));
		if(dVD!=null) {
			modelAndView.addObject("dVD", dVD);
			modelAndView.setViewName("ShowDVD");
		}
		else {
			modelAndView.addObject("message", "Employee with Title "+request.getParameter("title")+" does not exist");
			modelAndView.setViewName("Output");
			
		}
			
		
		return modelAndView;
	}
	
	@RequestMapping("/InputDVDDetails")
	public ModelAndView InputDVDDetailsPageController() {
		return new ModelAndView("InputDVDDetails");
	}
	
	@RequestMapping("/showAllDVDS")
	public ModelAndView showAllDVDSController() {
		ModelAndView modelAndView = new ModelAndView();

		List<DVD> dVDList = dVDService.getAllDVDS();
		modelAndView.addObject("dVDList", dVDList);
		modelAndView.setViewName("DisplayAllDVDS");
		return modelAndView;
	}
	
	@RequestMapping("/saveDVD")
	public ModelAndView saveDVDController(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String title = request.getParameter("title");
		String releaseDateStr = request.getParameter("releaseDate");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate releaseDate = LocalDate.parse(releaseDateStr, formatter);
		String mpaaRating = request.getParameter("mpaaRating");
		String director = request.getParameter("director");
		String studio = request.getParameter("studio");
		String userRating = request.getParameter("userRating");
		DVD dVD = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);

		String message = null;
		if (dVDService.addDVD(dVD))
			message = "DVD Added";
		else
			message = "DVD Not Added";

		modelAndView.addObject("message", message);
		modelAndView.setViewName("Output");

		return modelAndView;
	}
	
	@RequestMapping("/InputDVDTitlePageForDelete")
	public ModelAndView inputDVDTitlePageForDeleteController() {
		return new ModelAndView("InputDVDTitleForDelete");
	}
	
	@RequestMapping("/deleteDVD")
	public ModelAndView deleteDVDController(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String message = null;
		if (dVDService.deleteDVDByTitle(request.getParameter("title"))) {
			message = "DVD with title " + request.getParameter("title") + " deleted !";
		} else {
			message = "DVD with title " + request.getParameter("title") + " not deleted !";
		}
		modelAndView.addObject("message", message);
		modelAndView.setViewName("Output");
		
		return modelAndView;
	}
	
	@RequestMapping("/InputDVDDetailsPageForUpdate")
	public ModelAndView InputDVDDetailsPageForUpdateController(){
		return new ModelAndView("InputDVDDetailsForUpdate");
	}
	
	@RequestMapping("/updateDVDUserRating")
	public ModelAndView updateDVDUserRatingController(HttpServletRequest request) {
		
		String message=null;
		String title=request.getParameter("title");
		String newRating=request.getParameter("newRating");
		if(dVDService.updateUserRating(title, newRating))
			message="User Rating Updated for DVD Title "+request.getParameter("title");
		else
			message="User Rating Updated for DVD Title "+request.getParameter("title");
		
		return new ModelAndView("Output", "message", message);
	}
}
