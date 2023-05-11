package com.hitema.jee.mvc.controllers;

import com.hitema.jee.mvc.entities.City;
import com.hitema.jee.mvc.services.CityService;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CityController {
    private static final Logger log = LoggerFactory.getLogger(CityController.class);

    private CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping("/cities")
    public ModelAndView getAll(){
        log.trace("Cities Get All called ...");
        var view = new ModelAndView();
        view.addObject("mmsg","autres attribut");
        view.addObject("cities",service.readAll());
        return view;
    }

    @GetMapping("/cities/{id}")
    public ModelAndView getCityDetails(@PathVariable Long id){
        log.trace("Get city details for id : " + id);
        var view = new ModelAndView("cityDetails");
        view.addObject("mmsg","autres attribut");
        view.addObject("cityDetails", service.getDetails(id));
        return view;
    }

    @GetMapping("/cities/{id}/cityImageModify")
    public ModelAndView getCity(@PathVariable Long id){
        log.trace("Get cityImage for id : " + id);
        var view = new ModelAndView("cityImageModify");
        view.addObject("cityDetails", service.getDetails(id));
        return view;
    }

    @PostMapping("/cities/{id}/cityImageModify")
	public ModelAndView cityImageModify(@PathVariable Long id, @ModelAttribute("cityForm") City city, ModelMap model) {
		log.trace("Appel cityImageModify : {} ", city);
		service.create(city);
		return new ModelAndView("cityImageModify", "city", city);
	}
}
