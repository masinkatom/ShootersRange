package com.example.shootersrange;

import java.util.UUID;

public class Player {
    private String id;
    private String name;
    private int score;

    public Player(String name, int score) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.score = score;
    }

    public Player(String data){
        String [] lajna = data.split("@#♂");
        this.id = lajna[0];
        this.name = lajna[1];
        this.score = Integer.parseInt(lajna[2]);
    }

    @Override
    public String toString() {
        return (PlayerManager.instance.players.indexOf(this)+1) + ". Hráč:  "+ name + "\n   Skóre: "+ score;
        /*return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                "}\n";*/
    }

    public String toDb(){
        return getId() + "@#♂" + getName() + "@#♂" + getScore();
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }
}


