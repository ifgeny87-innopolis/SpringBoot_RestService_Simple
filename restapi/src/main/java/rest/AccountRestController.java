package rest;

import answers.DataRestAnswer;
import answers.ErrorRestAnswer;
import answers.RestAnswer;
import answers.SuccessRestAnswer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * REST контроллер управления аккаунтом
 *
 * Created in project RestfuL_Project on 29.01.17
 */
@RestController
@RequestMapping("api/account")
public class AccountRestController
{
	private final AtomicLong counter = new AtomicLong();

	private String savedToken = null;

	/**
	 * Метод входа пользователя в систему
	 * TODO добавить проверку доступа
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "enter", method = POST)
	public RestAnswer postEnter(@RequestParam String username,
	                            @RequestParam String password)
	{
		return new DataRestAnswer(new HashMap<String, Object>()
		{{
			put("id", counter.incrementAndGet());
			savedToken = UUID.randomUUID().toString();
			put("token", savedToken);
			put("name", username);
		}});
	}

	/**
	 * Метод выхода пользователя из системы
	 *
	 * @param token
	 * @return
	 */
	@RequestMapping("exit")
	public ResponseEntity<?> getExit(@RequestParam String token)
	{
		if (token == null || !token.equals(savedToken)) {
			return new ResponseEntity<RestAnswer>(
					new ErrorRestAnswer(403, "Token wrong"),
					HttpStatus.UNAUTHORIZED);
		}

		savedToken = null;

		return new ResponseEntity<SuccessRestAnswer>(
				new SuccessRestAnswer(true),
				HttpStatus.OK);
	}
}
