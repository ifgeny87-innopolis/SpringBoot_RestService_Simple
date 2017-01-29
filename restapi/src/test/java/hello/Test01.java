package hello;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

/**
 * FIXME Не работает, отказался в пользу тестов на Javascript
 *
 * Created in project RestfuL_Project on 29.01.17
 */
@SpringBootApplication
public class Test01
{
	static final String rootUrl = "http://localhost:8080/";
	static final String ACCOUNT_ENTER = rootUrl + "account/enter";

	static HttpHeaders httpHeaders;
	static RestTemplate restTemplate;

	@BeforeClass
	static public void beforeClass()
	{
		// Стартуем REST-сервер
//		SpringApplication.run(Application.class);

		httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		httpHeaders.add("User-Agent", "firefox");

		restTemplate = new RestTemplate();
	}

	private final String username = "Kitty";
	private final String password = "123";

	private String tokenId;

	@Before
	public void beforeTest()
	{
		/*if (tokenId != null) return;

		HttpEntity entity = new HttpEntity(httpHeaders);
		ResponseEntity<String> res = restTemplate.postForEntity(
				ACCOUNT_ENTER,
				entity,
				String.class,
				new HashMap<String, String>()
				{{
					put("name", username);
					put("password", password);
				}});

		System.out.println("Res = " + res);*/
	}

	@Test
	public void test01()
	{
//		HttpEntity payload = new HttpEntity(httpHeaders);
//
//		ResponseEntity<String> response = restTemplate.exchange(
//				rootUrl + "account/enter", POST, payload, String.class);

//		assertEquals("", response.getStatusCode(), HttpStatus.OK);
	}
}
