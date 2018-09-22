/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integerarithmetic;

/**
 *
 * @author s165700
 */
public class ReductionCalculator extends Calculator {

    public String q; // stores the answer for q globally

    public ReductionCalculator(Operation o) {
        super(o);
    }

    @Override
    public void calculate() {
        reduce(o.x, o.m, Integer.valueOf(o.radix)); //To change body of generated methods, choose Tools | Templates.
    }

    public void reduce(String x, String y, int b) {
        String[] nums = findNegatives(x, y); // values for xn and yn are set
        // REMARK: the nums should be without leading zeros
        String[] xArrayStr = split(nums[0], b, 1); // the first num in the form of arrays
        String[] yArrayStr = split(nums[1], b, 1); // the second num in the form of arrays
        int[] xArray = convert(xArrayStr);
        int[] yArray = convert(yArrayStr);
        invertUsingFor(xArray);
        invertUsingFor(yArray);
        int m = xArray.length;
        int n = yArray.length;
        int k = m - n + 1;
        String multiple; // intermediate value for the multiple
        int[] qArray = new int[k];
        String interimAnswer;
        String interimRemainder;
        String remainder;

        if (y.substring(0, 1).equals("-")) {
            y = y.substring(1, y.length());
        }

        if (x.substring(0, 1).equals("-")) {
            remainder = x.substring(1, x.length()); // value for the remainder (initially x)    
        } else {
            remainder = x;
        }

        for (int i = k - 1; i >= 0; i--) {
            for (int j = b - 1; j >= 0; j--) {
                if (j == 0 && b == 1) {
                    multiple = makeString(1, 1);
                } else {
                    multiple = makeString(j, b); // start a string with the base num (9 or F)
                }
                if (b != 1) {
                    multiple = addTailingSigns(multiple, i, "0"); // num of zeros (b^i)*a number
                } else {
                    multiple = addTailingSigns(multiple, i, "1");
                }
                Operation mul = new Operation();
                mul.x = multiple;
                mul.y = y;
                mul.radix = String.valueOf(b);
                new MultiplicationCalculator(mul).calculate();
                interimAnswer = mul.getAnswer(); // value to subtract
                Operation subtr = new Operation();
                subtr.x = remainder; //
                subtr.y = interimAnswer;
                subtr.radix = String.valueOf(b);
                subtr.type = "subtract";
                new AdditionCalculator(subtr).calculate();
                interimRemainder = subtr.getAnswer();
                if (!interimRemainder.substring(0, 1).equals("-")) {
                    qArray[i] = j; // set the quotient to the number that was divided the most
                    remainder = interimRemainder;
                    break;
                }
            }
        }

        invertUsingFor(qArray);
        String quotient = combine(qArray, b);
        if ((xn && !yn) || (!xn && yn)) { // if both signs are different
            // both the quotient and the remainder need to be with a "-"
            quotient = "-" + quotient;
            Operation subtract = new Operation();
            subtract.x = quotient; //
            subtract.y = "1";
            subtract.radix = String.valueOf(b);
            subtract.type = "subtract";
            new AdditionCalculator(subtract).calculate();
            quotient = subtract.getAnswer();
            remainder = "-" + remainder;
            Operation addition = new Operation();
            addition.x = remainder; //
            addition.y = y;
            addition.radix = String.valueOf(b);
            addition.type = "add";
            new AdditionCalculator(addition).calculate();
            remainder = addition.getAnswer();
        }
        o.setAnswer(remainder); // the answer is the remainder
        this.q = quotient;
    }

}
