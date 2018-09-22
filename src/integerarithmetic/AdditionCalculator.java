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
public class AdditionCalculator extends Calculator {

    public AdditionCalculator(Operation o) {
        super(o);
    }

    //called to add or subtract 2 numbers, possibly negative
    void addSub(String x, String y, int b, String op) {
        //op = operator
        String[] neg = findNegatives(x, y);
        x = neg[0];
        y = neg[1];

        //if subtracting, pretend it's an addition of a + (-y)
        if (op.equals("-")) {
            yn = !yn;
        }

        //add leading zeros
        String[] newStrings = addLeadingZeros(x, y);
        x = newStrings[0];
        y = newStrings[1];

        //if x and y have the same sign, use addition (but keep track of a minus sign for the answer)
        if (xn == yn) {
            if (xn) {
                an = true;
            } else {
                an = false;
            }
            add(x, y, b);

            //if x and y have different signs use subtraction with the biggest number first
        } else if (aGreaterThanB(y, x)) {
            an = yn;
            sub(y, x, b);
        } else {
            an = xn;
            sub(x, y, b);
        }
    }

    //adds the positive numbers in string x and y of radix b together by splitting it into n-length bits
    void add(String x, String y, int b) {
        int[] xArray = convert(split(x, b, 1));
        int[] yArray = convert(split(y, b, 1));
        int[] zArray = addArrays(xArray, yArray, b);
        String result = combine(zArray, b);
        o.setAnswer(result);
    }

    //subtracts y from x in radix b using words of length 1 (only works if x > y > 0)
    void sub(String x, String y, int b) {
        int[] xArray = convert(split(x, b, 1));
        int[] yArray = convert(split(y, b, 1));
        int[] zArray = subArrays(xArray, yArray, b);
        String result = combine(zArray, b);
        o.setAnswer(result);
    }

    //adds xArray and yArray into zArray
    int[] addArrays(int[] xArray, int[] yArray, int b) {
        //add all groups of x and y to each other into the z-array using carries
        int[] zArray = new int[xArray.length + 1];
        int c = 0;
        for (int i = zArray.length - 2; i >= 0; i--) {
            int sum = (xArray[i] + yArray[i] + c);

            //exception for base 1
            if (b == 1) {
                zArray[i + 1] = sum;
            } else {
                //regular way to convert the sum into zarray[i+1] and a carry
                zArray[i + 1] = sum % b;
                c = (int) Math.floor(sum / b);
            }
        }
        zArray[0] = c;
        return zArray;
    }

    //subtract xArray and yArray into zArray where x <= y
    int[] subArrays(int[] xArray, int[] yArray, int b) {
        int[] zArray = new int[xArray.length];
        int c = 0;
        for (int i = zArray.length - 1; i >= 0; i--) {
            int sub = (xArray[i] - yArray[i] - c);
            if (sub < 0) {
                sub = (int) (sub + b);
                c = 1;
            } else {
                c = 0;
            }
            zArray[i] = sub;
        }
        return zArray;
    }

    @Override
    public void calculate() {
        if (o.type.equals("add")) {
            addSub(o.x, o.y, Integer.valueOf(o.radix), "+");
        } else {
            addSub(o.x, o.y, Integer.valueOf(o.radix), "-");
        }
    }

}
