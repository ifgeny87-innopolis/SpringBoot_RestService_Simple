package rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * REST контроллер для тестирования сервиса
 *
 * Created in project RestfuL_Project on 29.01.17
 */
@RestController
@RequestMapping("api/hello")
public class HelloRestController
{
	private final static AtomicLong counter = new AtomicLong();

	/**
	 * Вернет "Hello, User"
	 *
	 * @param name
	 * @return
	 */
	@RequestMapping
	public Map<String, Object> index(@RequestParam(defaultValue = "Unknown") String name)
	{
		return new HashMap<String, Object>()
		{{
			put("message", "Hello, " + name);
		}};
	}

	/**
	 * Метод для проверки возможностей Json преобразователя
	 *
	 * @return
	 */
	@RequestMapping("map")
	public Map<String, Object> map()
	{
		return new HashMap<String, Object>()
		{{
			put("id", counter.getAndIncrement());
			put("message", "Map testing done");
			put("timestamp", new Timestamp(System.currentTimeMillis()));
		}};
	}
}
