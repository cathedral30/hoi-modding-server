package info.hcooper.hoi4modding.ingest;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class NestedLevel {

    static final Logger logger = LoggerFactory.getLogger(NestedLevel.class);

    private String name;
    private HashMap<String, String> properties;
    private List<NestedLevel> nestedLevels;

    public NestedLevel(String name, List<String> data) {

        logger.info("Processing new NestedLevel with name {} and data length {}", name, data.size());
        //System.out.println(data);
        this.name = name;
        this.nestedLevels = new ArrayList<>();
        this.properties = new HashMap<>();

        int level = 0;
        String currName = "";
        List<String> currData = new ArrayList<>();

        for (String x: data) {
            //logger.info("Processing {}, level {}, currName {}, data size {}", x, level, currName, currData.size());

            if (level > 0) {

                currData.add(x);

            } else {

                if (!x.equals("=")) {

                    if (currName.equals("")) {

                        currName = x;

                    } else {

                        if (!x.equals("{")) {
                            if (x.contains(">") | x.contains("<")) {
                                currName += x;
                            } else {
                                properties.put(currName, x);
                                currName = "";
                            }


                        }

                    }

                }

            }
            if (x.equals("{")) {

                level += 1;

            } else if (x.equals("}")) {

                level -= 1;

                if (level == 0) {

                    nestedLevels.add(new NestedLevel(currName, currData));
                    currData.clear();
                    currName = "";

                }

            }

        }

    }

    public List<String> getFormattedStrings() {

        List<String> strings = new ArrayList<>();
        strings.add(name + ":");
        for (String prop: properties.keySet()) {

            strings.add("\t" + prop + " = " + properties.get(prop));

        }

        for (NestedLevel n: nestedLevels) {
            for (String s: n.getFormattedStrings()) {

                strings.add("\t" + s);

            }
        }

        return strings;

    }

}
