package info.hcooper.hoi4modding.ingest;

import com.google.common.collect.ImmutableMap;
import info.hcooper.hoi4modding.repository.Building;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
public class IngestService {

    Logger logger = LoggerFactory.getLogger(IngestService.class);

    public static Map<String, Boolean> BOOL_MAP =
            ImmutableMap.<String, Boolean>builder()
            .put("yes", true)
            .put("no", false)
            .build();

    public NestedLevel readFile(File file) throws IOException {

        String content = new String(Files.readAllBytes(file.toPath()));
        content = content.replace("\t", "");
        String[] blah = content.split("\n");
        List<String> cleaned = new ArrayList<>();

        for (String x : blah) {

            String clean = x.split("#")[0].replace("\n", "").replace("\r", "");

            if (clean.length() > 0) {

                String[] spaceSplit = clean.split(" ");

                for (String s: spaceSplit) {

                    cleaned.addAll(getEqualsDelimited(s));

                }

            }
        }

        return new NestedLevel(file.getName(), combineQuoteStrings(cleaned));

    }

    public List<String> combineQuoteStrings(List<String> strings) {

        String combineString = "";
        List<String> returnList = new ArrayList<>();

        for (String s: strings) {

            if (s.contains("\"")) {
                if (combineString.length() > 0) {
                    returnList.add(combineString + " " + s);
                    combineString = "";
                } else {
                    combineString = s;
                }
            } else {
                if (combineString.length() > 0) {
                    combineString += " " + s;
                } else {
                    returnList.add(s);
                }
            }

        }

        return returnList;

    }

    public List<String> getEqualsDelimited(String s) {

        List<String> retArray = new ArrayList<>();

        if (s.equals("=")) {

            retArray.add("=");
            return retArray;

        } else if (s.equals("<")) {

            retArray.add("<");
            return retArray;

        } else if (s.equals(">")) {

            retArray.add(">");
            return retArray;

        }

        retArray.add("");

        for (char c: s.toCharArray()) {

            if (c == '=') {

                retArray.add("=");
                retArray.add("");

            } else if (c == '<') {

                retArray.add("<");
                retArray.add("");

            } else if (c == '>') {

                retArray.add(">");
                retArray.add("");

            } else {

                retArray.set(retArray.size() - 1, retArray.get(retArray.size() - 1) + c);

            }

        }

        if (retArray.get(retArray.size() - 1).equals("")) {

            retArray.remove(retArray.size() - 1);

        }

        return retArray;

    }

    public void processNestedLevel(NestedLevel level) {

        if (level.getName().contains(".txt")) {

            for (NestedLevel nestedLevel : level.getNestedLevels()) {

                processNestedLevel(nestedLevel);
            }

        } else {

            if (level.getName().equals("buildings")) {

                for (NestedLevel nestedLevel : level.getNestedLevels()) {

                    new Building(nestedLevel);

                }

            }

        }

    }

}
