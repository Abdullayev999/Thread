package com.company;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Набор задач (Task) - класс в котором хранится 2 строки
 * ( задача и срок задачи) мы размещаем задачи в 1 стек и с помощью
 * трех потоков (работников) решаем задачи
 */
class Task {
    String task;
    String data;
   
    public void Aditional(){
        System.out.println("Add method");
    }

    Task(String task, String data) {
        this.task = task;
        this.data = data;
    }

    public String getTask() {
        return task;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    //метод сделать задачу
    public void setupTask() {
        System.out.println("Делаем задачу!");
    }
}


public class Main {

    public static Stack<Task> stack = new Stack<>();

    public static boolean isEmptyStack = false;

    public static void main(String[] args) throws InterruptedException {
        // 23.03.2021
        Task task1 = new Task("Открыть", "22.03.2021");
        Task task2 = new Task("Прочитать", "22.03.2021");
        Task task3 = new Task("Отредактировать", "22.03.2021");
        Task task4 = new Task("Сохранить", "23.03.2021");
        Task task5 = new Task("Проверить", "24.03.2021");
        //стек из задач
        LinkedList<Task> list = new LinkedList<>();
        list.add(task1);
        list.add(task2);
        list.add(task3);
        list.add(task4);
        list.add(task5);
        list.add(task1);
        list.add(task2);
        list.add(task3);
        list.add(task4);
        list.add(task5);
        list.add(task1);
        list.add(task2);
        list.add(task3);
        list.add(task4);
        list.add(task5);
        //удаление из стека

        long start = System.nanoTime();
        Runnable runnable1 = () -> {
            Task task = null;
            while (!isEmptyStack) {
                task = null;
                synchronized (stack) {
                    if (!stack.isEmpty()) {
                        System.out.println(Thread.currentThread().getName());
                        task = stack.pop();
                    }
                }
                if (task != null) {
                    task.setupTask();
                    System.out.println(task.getTask() + " " + task.getData() + " " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable1);
        Thread thread3 = new Thread(runnable1);
        Thread thread4 = new Thread(runnable1);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        for (Task x : list) {
            synchronized (stack) {
                stack.push(x);
            }
            Thread.currentThread().sleep(100);
        }


        while (!stack.isEmpty()){

        }

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        isEmptyStack = true;

        long stop = System.nanoTime();
        System.out.println((stop-start)/1000000000);
    }
}


