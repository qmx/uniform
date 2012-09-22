package me.qmx.uniform;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

public class ConfigParserTest {

    @Test
    public void parsesTheEclipseConfig() throws IOException {
        final InputStream stream = ConfigParserTest.class.getResourceAsStream("/eclipse-formatting.xml");
        Map<String, String> config = new ConfigParser(stream).parse();
        stream.close();
        assertThat(config.size()).isEqualTo(286);
    }
}
