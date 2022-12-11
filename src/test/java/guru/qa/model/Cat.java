package guru.qa.model;

import java.util.List;

public class Cat {
    public String name;

    public Features features;

    public static class Features {
        public List<String> skills;
        public int whiskers;
    }


}
