/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mythread;

/**
 *
 * @author student
 */
public class MyThread extends Thread {
@Override
public void run(){
    System.out.println("Thread is runing");
}
    
    public static void main(String[] args) {
       MyThread t=new MyThread();
       t.start();
    }
    
}
