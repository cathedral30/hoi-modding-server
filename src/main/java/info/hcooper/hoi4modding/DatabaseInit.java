package info.hcooper.hoi4modding;

import info.hcooper.hoi4modding.ingest.IngestService;
import info.hcooper.hoi4modding.ingest.NestedLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DatabaseInit {

    @Autowired
    IngestService ingestService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) throws IOException {

        NestedLevel buildingsFile = ingestService.readFile(new ClassPathResource("hoi/common/buildings/00_buildings.txt").getFile());
        ingestService.processNestedLevel(buildingsFile);
        //ingestService.readFile(new ClassPathResource("hoi/common/characters/ANG.txt").getFile());
        //ingestService.readFile(new ClassPathResource("hoi/common/continuous_focus/generic.txt").getFile());

    }
}
