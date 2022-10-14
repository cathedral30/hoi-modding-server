package info.hcooper.hoi4modding;

import info.hcooper.hoi4modding.ingest.IngestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class DatabaseInit {

    @Autowired
    IngestService ingestService;

    @Bean
    void initialise() throws IOException {

        //NestedLevel buildingsFile = ingestService.readFile(new ClassPathResource("hoi/common/buildings/00_buildings.txt").getFile());
        //ingestService.processNestedLevel(buildingsFile);
        //ingestService.readFile(new ClassPathResource("hoi/common/characters/ANG.txt").getFile());
        //ingestService.readFile(new ClassPathResource("hoi/common/continuous_focus/generic.txt").getFile());

    }

}
