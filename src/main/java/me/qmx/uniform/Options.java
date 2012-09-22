package me.qmx.uniform;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.List;

public class Options {
    @Option(name = "-f", usage = "eclipse formatting settings xml file", required = true, metaVar = "config")
    private File configFile;

    @Option(name = "-s", usage = "source version level", metaVar = "version")
    private String sourceVersion = "1.6";

    @Argument(multiValued = true, required = true, usage = "the files to format", metaVar = "file1 file2...")
    private File[] files = new File[]{};

    public File[] getFiles() {
        return files;
    }

    public File getConfigFile() {
        return configFile;
    }

    public String getSourceVersion() {
        return sourceVersion;
    }
}
