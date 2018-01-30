package com.cyh.commons.apache.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.junit.Test;

/**
 * Created by yanhuche on 7/2/2016.
 */
public class CommandLineTest {

    @Test
    public void test_() throws ParseException {
        Options options = new Options();
        options.addOption("t", false, "display current time");
        options.addOption("c", true, "country code");

        String[] args = {"-t"};
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("t")) {
            System.out.println(cmd.getOptionValue("t"));
        } else {
            // print the date
        }

        // get c option value
        String countryCode = cmd.getOptionValue("c");

        if (countryCode == null) {
            // print default date
        } else {
            // print date for country specified by countryCode
        }
    }

    @Test
    public void test_2() {
        Option help = new Option("help", "print this message");
        Option projecthelp = new Option("projecthelp", "print project help information");
        Option version = new Option("version", "print the version information and exit");
        Option quiet = new Option("quiet", "be extra quiet");
        Option verbose = new Option("verbose", "be extra verbose");
        Option debug = new Option("debug", "print debugging information");
        Option emacs = new Option("emacs", "produce logging information without adornments");

        Option logfile =
                OptionBuilder.withArgName("file").hasArg().withDescription("use given file for log").create("logfile");

        Option logger =
                OptionBuilder.withArgName("classname").hasArg()
                        .withDescription("the class which it to perform " + "logging").create("logger");

        Option listener =
                OptionBuilder.withArgName("classname").hasArg()
                        .withDescription("add an instance of class as " + "a project listener").create("listener");

        Option buildfile =
                OptionBuilder.withArgName("file").hasArg().withDescription("use given buildfile").create("buildfile");

        Option find =
                OptionBuilder.withArgName("file").hasArg()
                        .withDescription("search for buildfile towards the " + "root of the filesystem and use it")
                        .create("find");

        Option property =
                OptionBuilder.withArgName("property=value").hasArgs(2).withValueSeparator()
                        .withDescription("use value for given property").create("D");

        Options options = new Options();

        options.addOption(help);
        options.addOption(projecthelp);
        options.addOption(version);
        options.addOption(quiet);
        options.addOption(verbose);
        options.addOption(debug);
        options.addOption(emacs);
        options.addOption(logfile);
        options.addOption(logger);
        options.addOption(listener);
        options.addOption(buildfile);
        options.addOption(find);
        options.addOption(property);
    }




}
