package com.cyh.commons.apache.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Created by yanhuche on 7/2/2016.
 */
public class CommandLineDemo {

    private static Options options = new Options();

    public static void main(String[] args) {
        prepare();
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            System.out.println(line.getOptionValue("target"));
            System.out.println(line.getOptionValue("folder"));
            System.out.println(line.hasOption("c"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public static void prepare() {
        Option target = Option.builder("target").required(true).hasArg().build();
        options.addOption(target);
        Option folder = Option.builder("folder").required(true).hasArg().build();
        options.addOption(folder);
        Option c = Option.builder("c").required(false).hasArg(false).build();
        options.addOption(c);
    }


}
