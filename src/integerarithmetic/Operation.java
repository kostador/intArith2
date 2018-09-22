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
public class Operation {

    //Only use the ones specific to your operation
    String radix;
    String type;
    String x;
    String y;
    String m;
    private String answer;
    int countAdd;
    int countMul;
    String ansA;
    String ansB;
    String ansD;

    public String getAnswer() {
        if (answer != null && answer.length() != 0) {
            return answer;
        } else {
            throw new IllegalStateException("Answer not found");
        }
    }

    public void setAnswer(String answer) {
        if (answer != null && answer.length() != 0) {
            this.answer = answer;
        }
    }
}
