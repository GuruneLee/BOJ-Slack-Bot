package algoBot.helper.resourceReader;

import algoBot.slack.BotSecretDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YamlReader {
    public BotSecretDto readToMap(String fileName) {
        Yaml yaml = new Yaml();
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
        return new ObjectMapper().convertValue(yaml.load(inputStream), BotSecretDto.class);
    }
}