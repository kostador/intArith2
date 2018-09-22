/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integerarithmetic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author s165700
 */
public class InputReader {

    ArrayList<Operation> inputOperations = new ArrayList<Operation>();
    Scanner sc;

    void loadFile() {
        try {
            sc = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            System.out.print("File not found.");
        }
    }

    ArrayList<Operation> getInput() {
        //Load the file then read from it
        loadFile();
        if (sc != null) {
            Operation o = new Operation();
            while (sc.hasNext()) {
                String input = sc.next();
                switch (input) {
                    //if we reach a radix line check if the operation is not the first one and add it to the list of operations
                    case "[radix]":
                        if (o.radix != null) {
                            inputOperations.add(o);
                        }
                        o = new Operation();
                        o.radix = sc.next();
                        break;
                    case "[add]":
                        o.type = "add";
                        break;
                    case "[subtract]":
                        o.type = "subtract";
                        break;
                    case "[multiply]":
                        o.type = "multiply";
                        break;
                    case "[karatsuba]":
                        o.type = "karatsuba";
                        break;
                    case "[reduce]":
                        o.type = "reduce";
                        break;
                    case "[inverse]":
                        o.type = "inverse";
                        break;
                    case "[euclid]":
                        o.type = "euclid";
                        break;
                    case "[x]":
                        o.x = sc.next();
                        break;
                    case "[y]":
                        o.y = sc.next();
                        break;
                    case "[m]":
                        o.m = sc.next();
                        break;
                }
            }
            //Add the last operation
            if (o.radix != null);
            inputOperations.add(o);
        }
        //Close the scanner when done and return the input
        sc.close();
        return inputOperations;
    }

}
