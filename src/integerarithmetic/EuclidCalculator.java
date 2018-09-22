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
public class EuclidCalculator extends Calculator {

    public EuclidCalculator(Operation o) {
        super(o);
    }

    //finds x, y and for which gcd(a, b) = d = xa + yb NOTE: b is not the base
    void euclid(String a, String b, int base)	{
    	String q, r, d, x, x1, x2, x3, y, y1, y2, y3;	 //where d is the gcd(x, y)
    	
    	//removes the minus signs and store negativity in xn and yn
    	String[] s = findNegatives(a, b);
    	a = s[0];
    	b = s[1];
    	
    	//initialize x1, x2, y1, y2
    	x1 = "1";
    	x2 = "0";
    	y1 = "0";
    	y2 = "1";
    	
    	while (aGreaterThanB(b, "0"))	{
    		
    		//get q and r from division of a over b
    		Operation reduction = new Operation();
            reduction.x = a;
            reduction.m = b;
            reduction.radix = String.valueOf(base);
            ReductionCalculator reductionCalculator = new ReductionCalculator(reduction);
            reductionCalculator.calculate();
            q = reductionCalculator.q;
            r = reduction.getAnswer();
    		
    		//update a and b
    		a = b;
    		b = r; 	
    		
    		//update x1, x2, x3, y1, y2, y3
    		x3 = addStrings(x1, multiplyStrings(q, x2, base), base, "sub");
    		y3 = addStrings(y1, multiplyStrings(q, y2, base), base, "sub");
    		x1 = x2;
    		x2 = x3;
    		y1 = y2;
    		y2 = y3;
    	}
    	
    	//construct d, x and y
    	d = a;
    	if (xn)	{
    		x = "-" + x1;
    	} else {
    		x = x1;
    	}
    	if (yn) {
    		y = "-" + y1;
    	} else {
    		y = y1;
    	}	
    	
    	//pass answers to operator object
    	o.ansA = x;
    	o.ansB = y;
    	o.ansD = d;
    }
  
    @Override
    public void calculate() {
    	euclid(o.x, o.y, Integer.valueOf(o.radix));
    }
    
}
