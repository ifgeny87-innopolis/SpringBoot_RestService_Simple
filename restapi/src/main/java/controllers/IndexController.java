package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер рута, просто переводит на домашнюю страницу
 *
 * Created in project RestfuL_Project on 29.01.17
 */
@Controller
@RequestMapping("/")
public class IndexController
{
	@RequestMapping
	public String index()
	{
		return "index";
	}
}
