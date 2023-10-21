package pt.com.rubira.CryptoCurrency;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pt.com.rubira.CryptoCurrency.principal.Principal;

@SpringBootApplication
public class
CryptoCurrencyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CryptoCurrencyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.ExibeMenu();
	}
}
