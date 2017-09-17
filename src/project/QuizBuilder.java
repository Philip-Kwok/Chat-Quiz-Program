/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

/**
 *
 * @author Philip
 */
public class QuizBuilder extends JFrame {

    private JButton Next;
    private JTextField qq;
    private JRadioButton choice1, choice2, choice3, choice4;
    private ButtonGroup group;
    private int choice;
    private int count = 0;
    private double points;
    private int size;
    ArrayList <QuizMaker> al;

    QuizBuilder() {
       QuizMaker [] arr;
       
         try{
       Socket herpes = new Socket ("localhost", 4321);
       ObjectInputStream oos = new ObjectInputStream(herpes.getInputStream());
       arr = (QuizMaker[])oos.readObject();
       al=new ArrayList<QuizMaker>(Arrays.asList(arr));
         }
       catch(Exception e){
           e.printStackTrace();
       }
        
        size = al.get(count).getNum();
        while(count<=size){
        JPanel quizpanel = new JPanel(new GridLayout(7, 1));
        qq = new JTextField(al.get(count).getQuestion());
        qq.setEditable(false);
        Next = new JButton("Confirm");
        choice1 = new JRadioButton(al.get(count).getChoicea());
        choice2 = new JRadioButton(al.get(count).getChoiceb());
        choice3 = new JRadioButton(al.get(count).getChoicec());
        choice4 = new JRadioButton(al.get(count).getChoiced());
        group = new ButtonGroup();
        group.add(choice1);
        group.add(choice2);
        group.add(choice3);
        group.add(choice4);
        quizpanel.add(qq);
        quizpanel.add(choice1);
        quizpanel.add(choice2);
        quizpanel.add(choice3);
        quizpanel.add(choice4);
        quizpanel.add(Next);
        add(quizpanel, BorderLayout.CENTER);
        setSize(300, 400);
        setVisible(true);
        Next.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (count<=size){
                int answer = -1;
                choice = al.get(count).getAnswer();
                if (choice1.isSelected()) {
                    answer = 0;
                } else if (choice2.isSelected()) {
                    answer = 1;
                } else if (choice3.isSelected()) {
                    answer = 2;
                } else if (choice4.isSelected()) {
                    answer = 3;
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a choice", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                if (choice == answer) {
                    points++;
                    count++;
                } else {
                    count++;
                }
                }
                else
                {
                    Next.setEnabled(false);
                    Next.setVisible(false);
                    JButton finish = new JButton("Finish");
                    quizpanel.add(finish);
                    finish.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                double total;
                total = (points/size)*100;
                JOptionPane.showMessageDialog(null, "Your grade is" + total, "Alert", JOptionPane.INFORMATION_MESSAGE);
            }
        });    
                }
            }
        });
    }
    }

    public static void main(String args[]) {
        new QuizBuilder();
    }

}
