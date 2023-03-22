package org.academiadecodigo.testinhos;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "players")
public class Player extends AbstractModel{

    private String name;
    private String champ;


    public Player(String name, String champ) {

        this.name = name;
        this.champ = champ;
    }

    public Player() {}



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChamp() {
        return champ;
    }

    public void setChamp(String champ) {
        this.champ = champ;
    }

    @Override
    public String toString() {
        return "Player{" +
                ", name='" + name + '\'' +
                ", champ='" + champ + '\'' +
                '}';
    }
}


