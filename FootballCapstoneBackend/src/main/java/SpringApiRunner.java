import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"service","controller","models"} )
public class SpringApiRunner {

	public static void main(String[] args) {
		// API Runner
		SpringApplication.run(SpringApiRunner.class, args);
		System.out.println("Application running");
	}

}
