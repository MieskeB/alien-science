package nl.michelbijnen.minecraft.alien.science;

public class Constants {
    public static final String MODID = "alienscience";

    public static class ItemGroup {
        public static final String DEFAULT = "default";
        public static final String MOON = "moon";
    }

    public static class Block {
        public static final String MOB_TESTER = "mob_tester";
        public static final String OXYGEN_GENERATOR = "oxygen_generator";
        public static final String ALIEN_CRATE = "alien_crate";

        public static class Moon {
            public static final String MOON_DUST = "moon_dust";
            public static final String MOON_GRAVEL = "moon_gravel";
            public static final String MOON_STONE = "moon_stone";
            public static final String MOON_PLANT = "moon_plant";
            public static final String MOON_ORE = "moon_ore";
        }
    }

    public static class Item {
        public static final String ALIEN_INGOT = "alien_ingot";
        public static final String ALIEN_FINDER = "alien_finder";

        public static class Armor {
            public static final String ALIEN_INGOT_HELMET = "alien_ingot_helmet";
            public static final String ALIEN_INGOT_CHESTPLATE = "alien_ingot_chestplate";
            public static final String ALIEN_INGOT_LEGGINGS = "alien_ingot_leggings";
            public static final String ALIEN_INGOT_BOOTS = "alien_ingot_boots";
        }

        public static class Food {
            public static final String EDIBLE_ALIEN_FINDER = "edible_alien_finder";
        }
    }

    public static class Dimension {

        public static class Moon {
            public static final String NAME = "moon";
            public static final String MOON_PLAINS = "moon_plains";
        }
    }

    public static class Structures {

        public static class TestingTent {
            public static final String NAME = "testing_tent";
            public static final String PATH = "testing_tent/testing_tent";
            public static final String PIECE = "testing_tent_piece";
        }
    }

    public static class Feature {
        public static final String MOON_PLANT_PROVIDER = "moon_plant_provider";
    }
}
