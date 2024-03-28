package it.interno.oim;

import it.interno.oim.config.OimProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"file:/u01/app/oracle/config/domains/portale/oim.properties"})
@EnableConfigurationProperties({OimProperties.class})
public class OimServiceApplication {

	public static void main(String[] args) {
//		System.setProperty("javax.net.ssl.trustStore", "C:/u01/app/oracle/config/domains/sicurezza/cacerts");
//		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
		SpringApplication.run(OimServiceApplication.class, args);
	}

}
