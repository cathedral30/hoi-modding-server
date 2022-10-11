package info.hcooper.hoi4modding;

import info.hcooper.hoi4modding.ingest.IngestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hoi4moddingApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hoi4moddingApplication.class, args);
	}

}
