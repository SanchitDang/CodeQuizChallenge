package com.sanapplications.codequizchallenge;

public class ModelClass {
    String que;
    String op1;
    String op2;
    String op3;
    String op4;
    String ans;


    //Empty Constructor for firebase
    public ModelClass(){}

    public ModelClass(String que, String op1, String op2, String op3, String op4, String ans) {
        this.que = que;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.ans = ans;
    }

    public String getQue() {
        return que;
    }

    public String getOp1() {
        return op1;
    }

    public String getOp2() {
        return op2;
    }

    public String getOp3() {
        return op3;
    }

    public String getOp4() {
        return op4;
    }

    public String getAns() {
        return ans;
    }
}
