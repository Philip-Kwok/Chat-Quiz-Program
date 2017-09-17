/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javax.swing.JOptionPane;

/**
 *
 * @author Philip
 */
public class QuizInfo {

    public QuizMaker[] getThing() {
        return thing;
    }

    private QuizMaker[] thing;

    private int count = 0;
    private int num = 0;

    public void setnum(int a) {
        num = a;
        thing = new QuizMaker[num];
        
    }

    public int getNum() {
        return num;
    }

    public void insert(QuizMaker holder) {
        thing[count].setvalues(holder.getQuestion(), holder.getChoicea(), holder.getChoiceb(), holder.getChoicec(), holder.getChoiced(), holder.getAnswer());
    }

    public static void main(String args[]) {

    }
}
