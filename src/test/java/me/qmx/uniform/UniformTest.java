package me.qmx.uniform;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class UniformTest {

    private final URL resource = UniformTest.class.getResource("/ToBeFormatted.java");

    @Test
    public void testFormat() throws IOException {
        final InputStream configStream = UniformTest.class.getResourceAsStream("/eclipse-formatting.xml");
        new Uniform(configStream, "1.6", new File(resource.getPath())).process();
        configStream.close();
    }
}
