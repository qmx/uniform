package me.qmx.uniform;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    @Option(name = "-f", usage = "eclipse formatting settings")
    private File configFile;

    @Argument
    private List<File> files = new ArrayList<File>();

    public static void main(String[] args) {
        new Main().run(args);
    }

    private void run(String[] args) {
        final CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            new Uniform(configFile).format(files);
        } catch (CmdLineException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
