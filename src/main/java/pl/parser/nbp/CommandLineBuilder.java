package pl.parser.nbp;

import org.apache.commons.cli.*;
import java.util.Arrays;

class CommandLineBuilder {

    private static final Options options = new Options();

    CommandLineBuilder(){
        Option optionh = Option.builder("h").required(false).desc("Show help.").build();
        Option option1 = Option.builder("").required(false).hasArgs().desc("Choose first argument: currency from USD, EUR, CHF, GBP and then second and third: " +
                "specify starting and ending date in format yyyy-MM-dd to calculate mean of sales rate and standard deviation of buying rate.").build();
        options.addOption(option1);
        options.addOption(optionh);
    }

    private static CommandLine generateCommandLine(String[] args){
        final CommandLineParser lineParser = new DefaultParser();
        CommandLine commandLine = null;
        try
        {
            commandLine = lineParser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(
                    "Unable to parse command-line arguments "
                            + Arrays.toString(args) + " due to: "
                            + e);
        }
        return commandLine;
    }

    private static void generateHelp(){
        String header = "CURRENCY SALE'S RATES PROVIDER";
        String footer = "By Anna Banaszak";
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Solution of a task for Smart4Aviation Internship", header, options, footer,true);
    }


    void run(String[] args) throws java.text.ParseException {

        CommandLine commandLine = generateCommandLine(args);

        if(commandLine.hasOption("h")){
            generateHelp();
            return;
        }
        if (args.length < 3) {
            System.out.println("Too few arguments.");
            generateHelp();
            return;
        }

        Executor.execute(args[0], args[1], args[2]);
    }
}
