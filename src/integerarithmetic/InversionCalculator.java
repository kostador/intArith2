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
public class InversionCalculator extends Calculator {

    public InversionCalculator(Operation o) {
        super(o);
    }

    @Override
    public void calculate() {

        //Declarations
        ArrayList<String> x1 = x;//9
        ArrayList<String> m1 = m;//17
        ArrayList<String> a1 = new ArrayList<>();
        ArrayList<String> a2 = new ArrayList<>();
        ArrayList<String> a3 = new ArrayList<>();
        ArrayList<String> q = new ArrayList<>();
        ArrayList<String> r = new ArrayList<>();
        a1.add("1");
        a2.add("0");
        //debugArray(m1);
        while (!m1.get(0).equals("-") && !m1.get(0).equals("0")) {
            //x1 reduction m1
            //q quotient
            //r remainder
            Operation reduction = new Operation();
            reduction.x = arrayListToString(x1);
            reduction.m = arrayListToString(m1);
            //debugArray(m1);
            reduction.radix = o.radix;
            ReductionCalculator calculator = new ReductionCalculator(reduction);
            calculator.calculate();
            q = stringToArrayList(calculator.q);
            r = stringToArrayList(reduction.getAnswer());
            x1 = m1;
            m1 = r;
            //Multiply q by a2 
            Operation multi = new Operation();
            multi.radix = o.radix;
            multi.x = arrayListToString(q);
            multi.y = arrayListToString(a2);
            new MultiplicationCalculator(multi).calculate();
            //Subtract q*a2 from a1
            Operation subtract = new Operation();
            subtract.radix = o.radix;
            subtract.x = arrayListToString(a1);
            subtract.y = multi.getAnswer();
            subtract.type = "subtract";
            new AdditionCalculator(subtract).calculate();
            //Swap
            a3 = stringToArrayList(subtract.getAnswer());
            a1 = a2;
            a2 = a3;
            //debugArray(m1);
        }
        //debugArray(x1);
        if (x1.get(0).equals("1") && x1.size() == 1) {
            while (a1.get(0).equals("-")) {
                Operation add = new Operation();
                add.radix = o.radix;
                add.x = arrayListToString(a1);
                add.y = o.m;
                add.type = "add";
                new AdditionCalculator(add).calculate();
                a1 = stringToArrayList(add.getAnswer());
            }
            o.setAnswer(arrayListToString(a1));
        } else {
            o.setAnswer("ERROR");
        }
    }

    void debugArray(ArrayList<String> a) {
        for (String s : a) {
            System.out.print(s);
        }
        System.out.println();
    }

    void debugString(String a) {
        System.out.println(a);
    }

}
