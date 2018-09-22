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
public class KaratsubaCalculator extends Calculator {

    public KaratsubaCalculator(Operation o) {
        super(o);
    }

    @Override
    public void calculate() {
        preKaratsuba(o.x, o.y, Integer.valueOf(o.radix));
    }

    //deals with negative numbers
    void preKaratsuba(String x, String y, int b) {
        o.countAdd = 0;
        o.countMul = 0;
        String[] q = findNegatives(x, y);	//sets xn and yn
        x = q[0];
        y = q[1];
        String finalResult = doKaratsuba(x, y, b);

        // consider the sign
        if ((xn && !yn) || (!xn && yn)) { // if both signs are different
            // final result is with sign -
            finalResult = "-" + finalResult;
        }
        o.setAnswer(finalResult);
    }

    //the recursive method
    String doKaratsuba(String x, String y, int b) {
        String xHigh, xLow, yHigh, yLow;
        int n, n2;
        String[] q;

        //if parts are small enough, multiply using high school multiplication
        if (x.length() == 1 || y.length() == 1) {
            Operation multiplication = new Operation();
            multiplication.x = x;
            multiplication.y = y;
            multiplication.radix = String.valueOf(b);
            new MultiplicationCalculator(multiplication).calculate();
            o.countAdd = o.countAdd + multiplication.countAdd;
            o.countMul = o.countMul + multiplication.countMul;
            return multiplication.getAnswer();
        }

        //make x and y equally long by adding leading zeros
        String[] s = addLeadingZeros(x, y);
        x = s[0];
        y = s[1];

        //make strings of even length as to be able to use split() to get equal length parts
        if (x.length() % 2 == 1) {
            x = "0" + x;
            y = "0" + y;
        }

        //store the length of the strings in n
        n = Math.max(x.length(), y.length());
        n2 = (int) Math.floor(n / 2);

        //split x and y up into xHigh, xLow, yHigh, yLow
        q = split(x, b, n2);
        xHigh = q[0];
        xLow = q[1];
        q = split(y, b, n2);
        yHigh = q[0];
        yLow = q[1];

        //recurse
        String z0 = doKaratsuba(xLow, yLow, b);
        String z1 = doKaratsuba(addStrings(xLow, xHigh, b, "add"), addStrings(yLow, yHigh, b, "add"), b);

        //Since every character from the first string gets added to the character of the second string and the carry
        o.countAdd = o.countAdd + Math.max(xLow.length(), xHigh.length()) * 2;
        o.countAdd = o.countAdd + Math.max(yLow.length(), yHigh.length()) * 2;
        String z2 = doKaratsuba(xHigh, yHigh, b);

        //apply the formula to get the final answer
        String r1 = addTailingSigns(z2, n2 * 2, "0");
        String r2 = addTailingSigns(addStrings(addStrings(z1, z2, b, "sub"), z0, b, "sub"), n2, "0");
        o.countAdd = o.countAdd + Math.max(z1.length(), z2.length()) * 2;
        o.countAdd = o.countAdd + Math.max(Math.max(z1.length(), z2.length()), z0.length()) * 2;
        String r3 = addStrings(addStrings(r1, r2, b, "add"), z0, b, "add");
        o.countAdd = o.countAdd + Math.max(r1.length(), r2.length()) * 2;
        o.countAdd = o.countAdd + Math.max(Math.max(r1.length(), r2.length()), z0.length()) * 2;
        return r3;

    }

}
