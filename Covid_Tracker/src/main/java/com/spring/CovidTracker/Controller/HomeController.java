package com.spring.CovidTracker.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.CovidTracker.Model.DailyData;
import com.spring.CovidTracker.Service.CovidServices;

@Controller
public class HomeController {
	@Autowired
	CovidServices covidservices;	
	
@GetMapping("/")
public String home(Model model) {
	List<DailyData> newData=covidservices.getData();
	int freshCases = newData.stream().mapToInt(e -> e.getNewCases()).sum();
	int prevCases = newData.stream().mapToInt(g -> g.getPrevDayCase()).sum();
	int totalCases=freshCases+prevCases;
	model.addAttribute("Data", newData);
	model.addAttribute("newcases", freshCases);
	model.addAttribute("totalCases", totalCases);
	return "home";
	
}
}
