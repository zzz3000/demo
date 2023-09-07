package jobtest;

import java.math.BigDecimal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * получение бина аккаунта в банке с начальным балансом и бонусами
	 * @return
	 */
	@Bean
	@Scope("singleton")
	public Account account(){
	    return  new Account(new BigDecimal(5000), new BigDecimal(0));
	}
}
