package com.homework;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入主角的名称:");
        String name1=scanner.nextLine();
        System.out.println("请输入主角生命值:");
        int hp1=scanner.nextInt();
        System.out.println("请输入主角的攻击力:");
        int damage1=scanner.nextInt();
        System.out.println("请输入主角的防御力:");
        int defence1=scanner.nextInt();
        scanner.nextLine();
        System.out.println("请输入怪兽的名称:");
        String name2=scanner.nextLine();
        System.out.println("请输入怪兽生命值:");
        int hp2=scanner.nextInt();
        System.out.println("请输入怪兽的攻击力:");
        int damage2=scanner.nextInt();
        System.out.println("请输入怪兽的防御力:");
        int defence2=scanner.nextInt();

        System.out.println("请输入战斗的回合数:");
        int round=scanner.nextInt();

        Player player=new Player(name1,hp1,damage1,defence1);
        Monster monster=new Monster(name2,hp2,damage2,defence2);
        Fighting fighting=new Fighting(player,monster,round);
        fighting.fightProcess();
    }
}
