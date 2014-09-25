package net.avaleo.hackathon.controllers;

import net.avaleo.hackathon.dao.HackEsperDaoInterface;
import net.avaleo.hackathon.events.Event;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value="/event")
public class EventController {
	@Autowired
	private HackEsperDaoInterface webSocketDao;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Event getEvent()  {
		return new Event("sdsd", "sdsd", new DateTime());
	}


	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void addNewEvent(@RequestBody Event event)  {
		webSocketDao.publish(event);
	}
}
