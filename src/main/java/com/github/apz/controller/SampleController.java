package com.github.apz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.github.apz.model.SampleModel;
import com.github.apz.model.SampleModel.Address;

@Controller
@RequestMapping
public class SampleController {
	@GetMapping("setup")
	public ModelAndView setup(ModelAndView mnv, HttpSession session) {

		//SampleModel sampleModel = SampleModel.of("new user", Address.of("Tochigi", "gyouza-city", 8L));
		SampleModel sampleModel = SampleModel.of("new user", Address.of("Tochigi", "gyouza-city"));

		session.setAttribute("sampleModel", sampleModel);
		mnv.addObject(sampleModel);
		mnv.setViewName("sample");
		return mnv;
	}

	@GetMapping("display")
	public ModelAndView display(ModelAndView mnv, @SessionAttribute SampleModel sampleModel) {
		mnv.addObject(sampleModel);
		mnv.setViewName("sample");
		return mnv;
	}
}
