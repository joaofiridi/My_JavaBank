package org.academiadecodigo.testinhos;

import javax.persistence.Embeddable;

@Embeddable
public class Item {

    private String item;

    public Item (){}

    public Item (String item){
        this.item = item;

    }
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item='" + item + '\'' +
                '}';
    }
}

