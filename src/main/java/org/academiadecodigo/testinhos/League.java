package org.academiadecodigo.testinhos;

import javax.persistence.*;

@Entity
@Table(name = "League")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "League_type")
public class League extends AbstractModel{
    private String type;
    private String position;
    private String name;

    private Item item;


    public League (String type, String position, String name){
        this.type = type;
        this.position = position;
        this.name = name;

    }
    public League (){}



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "League{" +
                ", type='" + type + '\'' +
                ", position='" + position + '\'' +
                ", name='" + name + '\'' +
                ", item=" + item +
                '}';
    }
}
