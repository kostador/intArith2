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
public class MultiplicationCalculator extends Calculator {

    public MultiplicationCalculator(Operation o) {
        super(o);
    }

    @Override
    public void calculate() {
        multiply(o.x, o.y, Integer.valueOf(o.radix));
    }

    // Multriplication method according to the book
    public void multiply(String x, String y, int b) {
        String[] nums = findNegatives(x, y); // values for xn and yn are set
        // REMARK: the nums should be without leading zeros
        String[] xArrayStr = split(nums[0], b, 1); // the first num in the form of arrays
        String[] yArrayStr = split(nums[1], b, 1); // the second num in the form of arrays
        int[] xArray = convert(xArrayStr);
        int[] yArray = convert(yArrayStr);
        invertUsingFor(xArray);
        invertUsingFor(yArray);
        int carry = 0;
        int result = 0;
        int n = yArray.length;
        int m = xArray.length;
        int k = m + n;
        int[] zArray = new int[k];
        o.countAdd = 0;
        o.countMul = 0;

        for (int i = n; i <= n + m - 1; i++) {
            zArray[i] = 0;
        }
        for (int i = 0; i <= m - 1; i++) {
            carry = 0;
            for (int j = 0; j <= n - 1; j++) {
                o.countMul = o.countMul + 2;	//TODO check if this is the correct number of elementary multiplications
                o.countAdd = o.countAdd + 3;	//TODO check if this is the correct number of elementary additions
                result = zArray[i + j] + xArray[i] * yArray[j] + carry;
                carry = result / b;
                zArray[i + j] = result - carry * b;
            }
            zArray[i + n] = carry;
        }

        invertUsingFor(zArray);
        String finalResult = combine(zArray, b);
        // consider the sign
        if ((xn && !yn) || (!xn && yn)) { // if both signs are different
            // final result is with sign -
            finalResult = "-" + finalResult;
        }
        o.setAnswer(finalResult);
    }
}
