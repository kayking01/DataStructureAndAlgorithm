package com.example.linkedlist;


import java.util.Stack;

/**
 * 简单 栈的使用
 * @author kay
 * @create 2022 - 03 - 29-15:20
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");
        stack.push("s");

        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}
