package info.hcooper.hoi4modding.repository;

import info.hcooper.hoi4modding.ingest.IngestService;
import info.hcooper.hoi4modding.ingest.NestedLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

import static info.hcooper.hoi4modding.ingest.IngestService.BOOL_MAP;

@NoArgsConstructor
public class Building {

    private static final Logger logger = LoggerFactory.getLogger(IngestService.class);

    public int id;
    private String name;
    private int max_level;
    private boolean fuel_silo;
    private boolean radar;
    private boolean air_base;
    private boolean refinery;
    private boolean infrastructure;
    private boolean is_port;
    private boolean nuclear_reactor;
    private boolean supply_node;
    private boolean anti_air;
    private int military_production;
    private int general_production;
    private int naval_production;
    private int rocket_production;
    private int naval_fort;
    private int land_fort;
    private int air_defence;
    private int rocket_launch_capacity;
    private int nuclear_production_factor;
    private boolean infrastructure_construction_effect;
    private int icon_frame;
    private int show_on_map;
    private boolean show_modifier;
    private boolean always_shown;
    private int show_on_map_meshes;
    private boolean has_destroyed_mesh;
    private int base_cost;
    private int base_cost_conversion;
    private int local_resources_oil;
    private int local_resources_rubber;
    private boolean shares_slots;
    private int value;
    private float fuel_gain_from_states;
    private boolean allied_build;
    private float max_fuel_building;
    private float damage_factor;
    private boolean centered;
    private boolean disabled_in_dmz;
    private boolean provincial;
    private int per_level_extra_cost;
    private boolean only_costal;

    public Building(NestedLevel level) {

        Building building = new Building();

        for (String key: level.getProperties().keySet()) {

            String value = level.getProperties().get(key);

            try {

                Field field = building.getClass().getDeclaredField(key);

                if (field.getType() == int.class) {

                    field.setInt(building, Integer.parseInt(value));

                } else if (field.getType() == boolean.class) {

                    field.setBoolean(building, BOOL_MAP.get(value));

                } else if (field.getType() == float.class) {

                    field.setFloat(building, Float.parseFloat(value));

                } else if (field.getType() == String.class) {

                    field.set(building, value);

                }

            } catch (NoSuchFieldException | IllegalAccessException e) {

                logger.error("Unknown buildings field {}", key);

            }

        }

    }

}
