package com.homework;

import java.util.NoSuchElementException;

public class Fighting {
    private Player player;
    private Monster monster;
    private int round;
    int count=0;
    int phurt;
    int mhurt;

    public Fighting(Player player,Monster monster,int round){
        this.monster=monster;
        this.player=player;
        this.round=round;
    }
    public void fightProcess(){
        while(player.hp>0&&monster.hp>0){
            count++;
            System.out.println();
            System.out.println("Round"+count);
            if(player.hp>0){
                phurt=player.damage-monster.defence>0?player.damage-monster.defence:1;
                System.out.print(player.name);
                System.out.print("对");
                System.out.print(monster.name);
                System.out.print("造成了");
                System.out.print(phurt);
                System.out.print("点伤害。");
                monster.hp=monster.hp-phurt>0?monster.hp-phurt:0;
                System.out.println(monster.name+"还剩"+monster.hp+"血量");
            }
            if(monster.hp>0){
                mhurt=monster.damage-player.defence>0?monster.damage-player.defence:1;
                System.out.print(monster.name);
                System.out.print("对");
                System.out.print(player.name);
                System.out.print("造成了");
                System.out.print(mhurt);
                System.out.print("点伤害。");
                player.hp=player.hp-mhurt>0?player.hp-mhurt:0;
                System.out.println(player.name+"还剩"+player.hp+"血量");
            }
        }
        System.out.println();
        if(count==round){
            System.out.println("战斗还未分出胜负.");
        }else {
            if(player.hp>0)
            {
                System.out.println("怪兽就这？"+"作战"+count+"回合团灭坏兽！");
            }
            else{
                System.out.println("不会吧，不会有人"+count+"回合就被秒了吧？");
            }
        }
    }
}
