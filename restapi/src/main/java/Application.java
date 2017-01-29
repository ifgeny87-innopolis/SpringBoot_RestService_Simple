import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created in project RestfuL_Project on 29.01.17
 */
@SpringBootApplication
@ComponentScan(basePackages = {"rest", "controllers"})
public class Application extends SpringBootServletInitializer
{
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
