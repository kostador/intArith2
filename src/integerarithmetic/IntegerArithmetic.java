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
                    testEuclid(o);
                    break;
            }
        }
        //Print Results
        new OutputCreator(input);

    }
    
    void testEuclid(Operation o){
        String x = o.x;
        String y = o.y;
        String a = o.ansA;
        String b = o.ansB;
        Operation xa = new Operation();
        xa.x = x;
        xa.y = a;
        xa.radix = o.radix;
        new MultiplicationCalculator(xa).calculate();
        Operation yb = new Operation();
        yb.x = y;
        yb.y = b;
        yb.radix = o.radix;
        new MultiplicationCalculator(yb).calculate();
        Operation plus = new Operation();
        plus.x = xa.getAnswer();
        plus.y = yb.getAnswer();
        plus.radix = o.radix;
        plus.type = "add";
        new AdditionCalculator(plus).calculate();
        System.out.println("Testing");
        if(!o.ansD.equals(plus.getAnswer())){
            throw new IllegalStateException("Euclid is wrong");
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IntegerArithmetic program = new IntegerArithmetic();
        program.init();
        Operation o1 = new Operation();
        o1.x = "111111";
        o1.m = "11";
        o1.radix = "1";
        ReductionCalculator calc = new ReductionCalculator(o1);
        calc.calculate();
        System.out.println(o1.getAnswer());
        //System.out.println(calc.q);
        //System.out.println(calc.negRemainder);
        //System.out.println(calc.oldQuotient);
        
        Operation o2 = new Operation();
        o2.x = "2";
        o2.m = "3";
        o2.radix = "10";
        new InversionCalculator(o2).calculate();
        System.out.println(o2.getAnswer());
    }

}
