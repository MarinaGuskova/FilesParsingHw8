package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.Cat;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonParsingHw8 {
    @Test
    void CatJsonReader() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        File catjsonefile = new File("src/test/resources/cat.json");
        Cat cat = objectMapper.readValue(catjsonefile, Cat.class);

        assertThat(cat.name).isEqualTo("Murzik");
        assertThat(cat.features.skills.get(0)).isEqualTo("eating");
        assertThat(cat.features.skills.get(1)).isEqualTo("meowing");
        assertThat(cat.features.whiskers).isEqualTo(19875);

    }

}
