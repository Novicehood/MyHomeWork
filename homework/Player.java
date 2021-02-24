package com.homework;

public class Player {
    public String name;//主角名字
    public int hp;//生命值
    public int damage;//攻击力
    public int defence;//防御力
    public Player(String name,int hp,int damage,int defence){
        this.name=name;
        this.hp=hp;
        this.damage=damage;
        this.defence=defence;
    }
}
