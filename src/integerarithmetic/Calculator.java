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
public abstract class Calculator {

    ArrayList<String> x = new ArrayList<>();
    ArrayList<String> y = new ArrayList<>();
    ArrayList<String> m = new ArrayList<>();
    Operation o;
    boolean xn, yn, an;		//keeps track of negativity of x, y and the answer

    public Calculator(Operation o) {
        this.o = o;
        if (o != null) {
            formatOperation(o);
        }
    }

    private void formatOperation(Operation o) {
        for (int i = 0; i < o.x.length(); i++) {
            x.add(String.valueOf(o.x.charAt(i)));
        }
        if (o.y != null) {
            for (int i = 0; i < o.y.length(); i++) {
                y.add(String.valueOf(o.y.charAt(i)));
            }
        }
        if (o.m != null) {
            for (int i = 0; i < o.m.length(); i++) {
                m.add(String.valueOf(o.m.charAt(i)));
            }
        }
    }

    //adds two strings and returns a string (op = "add" for addition op = "sub" for subtraction)
    String addStrings(String x, String y, int b, String op) {
        Operation addition = new Operation();
        addition.x = x;
        addition.y = y;
        addition.radix = String.valueOf(b);
        addition.type = op;
        new AdditionCalculator(addition).calculate();
        return addition.getAnswer();
    }

    //multiplies two strings adn returns a string
    String multiplyStrings(String x, String y, int b) {
        Operation multiplication = new Operation();
        multiplication.x = x;
        multiplication.y = y;
        multiplication.radix = String.valueOf(b);
        new MultiplicationCalculator(multiplication).calculate();
        return multiplication.getAnswer();
    }

    //returns if a is greater than b (only works on strings of equal length
    boolean aGreaterThanB(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            if (makeInt(a.substring(i, i + 1)) > makeInt(b.substring(i, i + 1))) {
                return true;
            } else if (makeInt(a.substring(i, i + 1)) < makeInt(b.substring(i, i + 1))) {
                return false;
            }
        }
        return false;
    }

    //adds leading zeros to make x and y of equal length
    String[] addLeadingZeros(String x, String y) {
        int arrayLength = Math.max(x.length(), y.length());
        for (int i = (arrayLength) - x.length(); i > 0; i--) {
            x = "0" + x;
        }

        for (int i = (arrayLength) - y.length(); i > 0; i--) {
            y = "0" + y;
        }
        String[] rStrings = new String[2];
        rStrings[0] = x;
        rStrings[1] = y;
        return rStrings;
    }

    //stores the correct value in xn and yn and additionally removes the "-" (before adding leading zeros)
    String[] findNegatives(String x, String y) {
        String[] n = new String[2];

        //updates xn and stores new x in n[0]
        if (x.substring(0, 1).equals("-")) {
            xn = true;
            n[0] = x.substring(1, x.length());
        } else {
            xn = false;
            n[0] = x;
        }

        //updates yn and stores new y in n[1]
        if (y.substring(0, 1).equals("-")) {
            yn = true;
            n[1] = y.substring(1, y.length());
        } else {
            yn = false;
            n[1] = y;
        }
        return n;
    }

    //chop strings up into n-length bits and convert these to a String-array
    String[] split(String a, int b, int n) {
        double l = a.length();
        String[] aArray = new String[(int) Math.ceil(l / n)];
        for (int i = aArray.length - 1; i >= 0; i--) {
            aArray[i] = a.substring(a.length() - n, a.length());
            a = a.substring(0, a.length() - n);
        }
        return aArray;
    }

    int[] convert(String[] a) {
        int[] r = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            r[i] = makeInt(a[i]);
        }
        return r;
    }

    String combine(int[] zArray, int b) {

        //build up the resulting string
        String result = "";
        for (int i = zArray.length - 1; i >= 0; i--) {
            result = makeString(zArray[i], b) + result;
        }
        //remove leading zeros
        result = result.replaceFirst("^0*", "");
        if (result.isEmpty()) {
            result = "0";
        }
        if (an && !result.equals("0")) {
            result = "-" + result;
        }
        return result;
    }

    //converts a string s of a single character into a decimal number equal to the radix b string
    int makeInt(String s) {

        //converts base 11-16 letters into decimal numbers
        switch (s) {
            case "a":
                return 10;
            case "b":
                return 11;
            case "c":
                return 12;
            case "d":
                return 13;
            case "e":
                return 14;
            case "f":
                return 15;
            default:
                return Integer.valueOf(s);
        }
    }

    //converts a decimal int a into a String of length 1 representing the int in radix b
    String makeString(int a, int b) {
        String s = ""; //string to return

        //exception for base 1
        if (b == 1) {
            for (int i = 0; i < a; i++) {
                s = s + "1";
            }

            //normal way to convert int to a string
        } else {
            switch (a) {
                case 10:
                    s = "a";
                    break;
                case 11:
                    s = "b";
                    break;
                case 12:
                    s = "c";
                    break;
                case 13:
                    s = "d";
                    break;
                case 14:
                    s = "e";
                    break;
                case 15:
                    s = "f";
                    break;
                default:
                    s = s + a;
                    break;
            }
        }
        return s;
    }

    void invertUsingFor(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    //This adds n tailing zeros to a string
    String addTailingSigns(String a, int n, String sign) {
        for (int i = 0; i < n; i++) {
            a = a + sign;
        }
        return a;
    }

    String arrayListToString(ArrayList<String> x) {
        String string = x.get(0);
        for (int i = 1; i < x.size(); i++) {
            string = string + x.get(i);
        }
        return string;
    }

    ArrayList<String> stringToArrayList(String x) {
        ArrayList<String> array = new ArrayList<>();
        for (int i = 0; i < x.length(); i++) {
            array.add(String.valueOf(x.charAt(i)));
        }
        return array;
    }

    //Returns the answer. To be implemented according to the operation at hand.
    public abstract void calculate();
}
