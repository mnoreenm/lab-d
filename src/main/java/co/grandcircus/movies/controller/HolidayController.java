package co.grandcircus.movies.controller;

/*
 * Source Material (c) 2016 GCD
 * All rights reserved
 */

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.grandcircus.movies.rest.HolidayService;

/**
 * Handles requests for the weather page.
 */

@Controller
public class HolidayController {
	private static final Logger logger = LoggerFactory.getLogger(HolidayController.class);

	@Autowired
	private HolidayService holidayService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping("/holiday") // url /holiday. will work for GET or POST
	public String home(Locale locale, Model model) { // when someone click.
		// add the 'holiday' variable to the JSP
		model.addAttribute("holidayList", holidayService.getCurrentHoliday());// variable
																			// holiday.
							
		// calling
																			// the
																			// mehod
		// model.addAttribute = that is how we set variable in .jsp
		logger.info("/holiday -> holiday.jsp");
		// select to use the holiday.jsp view
		return "holiday"; // holiday.jsp
	}

}
