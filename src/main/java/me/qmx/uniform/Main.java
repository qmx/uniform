package me.qmx.uniform;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class Main {

    public static void main(String[] args) {
        final Options options = new Options();
        final CmdLineParser parser = new CmdLineParser(options);
        try {
            parser.parseArgument(args);
            new Uniform(options).process();
        } catch (CmdLineException e) {
            System.err.println("uniform [options] files...");
            parser.printUsage(System.err);
        }
    }
}
