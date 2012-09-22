package me.qmx.uniform;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Options {
    @Option(name = "-f", usage = "eclipse formatting settings xml", required = true)
    private File configFile;

    @Argument(multiValued = true, required = true, usage = "the files to format")
    private List<File> files = new ArrayList<File>();

    public List<File> getFiles() {
        return files;
    }

    public File getConfigFile() {
        return configFile;
    }
}
