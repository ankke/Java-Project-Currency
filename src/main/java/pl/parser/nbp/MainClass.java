package pl.parser.nbp;

import java.text.ParseException;

public class MainClass {

    public static void main(String[] args) {

        try{
            CommandLineBuilder cmd = new CommandLineBuilder();
            cmd.run(args);
        }catch(IllegalArgumentException e){
            System.out.println("Try -h for help.");
            e.printStackTrace();
        }catch (ParseException e){
            System.out.println("Wrong date format. Should be yyyy-MM-dd. Try -h for help.");
            System.out.println(e.getMessage());
        }
    }

}
