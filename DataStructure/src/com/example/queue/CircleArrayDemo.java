package com.example.queue;

import java.util.Scanner;

/**
 * 环形队列
 *
 * @author kay
 * @create 2022 - 03 - 28-17:11
 */
public class CircleArrayDemo {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列的案例");

        CircleArray queue = new CircleArray(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头数据");
            key = scanner.next().charAt(0);  // 接收一个字符
            switch (key){
                case 's' :
                    queue.showQueue();
                    break;
                case 'a' :
                    System.out.println("输出一个数");
                    queue.addQueue(scanner.nextInt());
                    break;
                case 'g' :      //  取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是：%d",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是：%d",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case'e':
                    scanner.close();
                    loop = false;
                    break;
                default:break;
            }
        }
        System.out.println("程序退出");
    }
}

class CircleArray{
    private int maxSize;  // 表示数组的最大容量
    // 调整：指向队列的第一个元素，也就是说  arr[front] 就是队列的第一个元素
    // 初始值  front = 0
    private int front;
    // rear 含义调整： rear 指向队列的最后一个元素的后一个位置。(空出一个空间作为约定？)
    // 初始值 rear =  0
    private int rear;
    private int[] arr;

    public CircleArray(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isFull(){
        // rear = 1;
        // maxSize =3;
        // front = 2
        return (rear + 1 ) % maxSize == front;     // ?
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }

    // 添加数据到队列
    public void addQueue(int n){
        // 判断队列是否满
        if (isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        arr[rear] = n;
        rear = (rear +1 ) % maxSize ; //   rear后移
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否空
        if (isEmpty()){
            //通过抛异常处理
            throw new RuntimeException("队列空，不能获取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1、先把front对应的值保留到一个临时变量
        //2、将front后移
        int value = arr[front];
        front = (front+1)%maxSize;
        return value;
    }

    // 显示队列的所有数据
    public void showQueue(){
        // 遍历
        if (isEmpty()){
            System.out.println("队列为空，没有数据");
            return;
        }
        // 从front开始遍历，遍历多少个元素
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize,arr[ i % maxSize]);
        }
    }

    // 求出当前队列有效数据的个数
    public int size(){

        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列的头数据，注意不是取出数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }
}
