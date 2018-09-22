package integerarithmetic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class OutputCreator {

    ArrayList<Operation> output;

    public OutputCreator(ArrayList<Operation> output) {
        boolean firstEmpty = false;
        this.output = output;
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("output.txt", true));

            for (Operation answer : output) {
                if (firstEmpty) {
                    writer.println();
                }
                firstEmpty = true;
                writer.print("[radix]  ");
                writer.print(answer.radix);
                writer.println();
                writer.println("[" + answer.type + "]");
                writer.print("[x]      ");
                writer.println(answer.x);
                if (!(answer.type.equals("inverse") || answer.type.equals("reduce"))) {
                    //Unless its inversion or reduction print y
                    writer.print("[y]      ");
                    writer.println(answer.y);
                }
                if (answer.m != null) {
                    //If we have a modular operation
                    writer.print("[m]      ");
                    writer.println(answer.m);
                }
                if (!answer.type.equals("euclid")) {
                    //Normal answer
                    writer.print("[answer] ");
                    writer.println(answer.getAnswer());
                } else {
                    //Euclid answer
                    writer.print("[ans-d] ");
                    writer.println(answer.ansD);
                    writer.print("[ans-a] ");
                    writer.println(answer.ansA);
                    writer.print("[ans-b] ");
                    writer.println(answer.ansB);
                }
                //If we have multiplication or karatsuba then print counts
                if (answer.type.equals("multiply") || answer.type.equals("karatsuba")) {
                    writer.print("[count-add] ");
                    writer.println(answer.countAdd);
                    writer.print("[count-mul] ");
                    writer.println(answer.countMul);
                }
            }

            writer.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
