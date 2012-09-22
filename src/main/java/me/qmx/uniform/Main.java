package me.qmx.uniform;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class Main {

    public static void main(String[] args) {
        new Main().run(args);
    }

    private void run(String[] args) {
        final Options options = new Options();
        final CmdLineParser parser = new CmdLineParser(options);
        try {
            parser.parseArgument(args);
            new Uniform(options).process();
        } catch (CmdLineException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
