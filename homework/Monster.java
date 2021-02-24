package com.homework;

public class Monster {
    public String name;//怪兽名字
    public int damage;//攻击力
    public int hp;//生命值
    public int defence;
    public Monster(String name, int hp,int damage,int defence) {
        this.name = name;
        this.damage = damage;
        this.hp = hp;
        this.defence=defence;
    }
}
