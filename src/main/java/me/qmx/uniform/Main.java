package me.qmx.uniform;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        final Options options = new Options();
        final CmdLineParser parser = new CmdLineParser(options);
        try {
            parser.parseArgument(args);
            final FileInputStream configFile = new FileInputStream(options.getConfigFile());
            new Uniform(configFile, options.getSourceVersion(), options.getFiles().toArray(new File[0])).process();
        } catch (CmdLineException e) {
            System.err.println("uniform [options] files...");
            parser.printUsage(System.err);
        } catch (FileNotFoundException e) {
            System.err.println("config file not found");
        }
    }
}
