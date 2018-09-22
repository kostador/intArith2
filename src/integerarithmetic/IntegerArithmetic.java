/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integerarithmetic;

import java.util.ArrayList;

/**
 *
 * @author s165700
 */
public class IntegerArithmetic {

    private ArrayList<Operation> input;

    void init() {
        //Reads input and stores it in an arrayList of type Operation
        InputReader inputReader = new InputReader();
        input = inputReader.getInput();
        for (Operation o : input) {
            //Check the operation type use the appropriate calculator
            Calculator calculator;
            switch (o.type) {
                case "add":
                    //NormalAddition
                    calculator = new AdditionCalculator(o);
                    calculator.calculate();
                    if (o.m != null) {
                        //Modular Addition
                        o.x = o.getAnswer();
                        ReductionCalculator red = new ReductionCalculator(o);
                        red.calculate();
                    }
                    break;
                case "subtract":
                    calculator = new AdditionCalculator(o);
                    calculator.calculate();
                    if (o.m != null) {
                        //Modular Substraction
                        o.x = o.getAnswer();
                        ReductionCalculator red = new ReductionCalculator(o);
                        red.calculate();
                    }
                    break;
                case "multiply":
                    calculator = new MultiplicationCalculator(o);
                    calculator.calculate();
                    if (o.m != null) {
                        //Modular Multiplication
                        o.x = o.getAnswer();
                        ReductionCalculator red = new ReductionCalculator(o);
                        red.calculate();
                    }
                    break;
                case "karatsuba":
                    calculator = new KaratsubaCalculator(o);
                    calculator.calculate();
                    break;
                case "reduce":
                    calculator = new ReductionCalculator(o);
                    calculator.calculate();
                    break;
                case "inverse":
                    calculator = new InversionCalculator(o);
                    calculator.calculate();
                    break;
                case "euclid":
                    calculator = new EuclidCalculator(o);
                    calculator.calculate();
                    break;
            }
        }
        //Print Results
        new OutputCreator(input);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IntegerArithmetic program = new IntegerArithmetic();
        program.init();
    }

}
