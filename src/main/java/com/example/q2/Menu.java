package com.example.q2;
import java.util.ArrayList;
import java.util.HashMap;

public class Menu {
    private ArrayList<Item> appetizers;
    private ArrayList<Item> mains;
    private ArrayList<Item> deserts;
    private ArrayList<Item> drinks;
    private int counter;
    public Menu(ArrayList<Item> itemArrayList){
        this.appetizers = new ArrayList<>();
        this.mains= new ArrayList<>();
        this.deserts= new ArrayList<>();
        this.drinks= new ArrayList<>();
        this.counter = 0;
        for (Item item : itemArrayList){
            switch (item.getType().name()){
                case "Appetizer" -> this.appetizers.add(item);
                case "Main" -> this.mains.add(item);
                case "Desert" -> this.deserts.add(item);
                case "Drink" -> this.drinks.add(item);
            }
        }
        this.counter = this.appetizers.size() + this.mains.size() + this.deserts.size() + this.drinks.size();
    }

    public int getCounter() {
        return counter;
    }

    public void addItem(Item item){
        switch (item.getType().name()){
            case "Appetizer" -> this.appetizers.add(item);
            case "Main" -> this.mains.add(item);
            case "Desert" -> this.deserts.add(item);
            case "Drink" -> this.drinks.add(item);
        }
    }

    public HashMap<mealCourse,ArrayList<Item>> getMenu() {
        HashMap<mealCourse,ArrayList<Item>> mp = new HashMap<>(){{
            put(mealCourse.Appetizer, appetizers);
            put(mealCourse.Main, mains);
            put(mealCourse.Desert, deserts);
            put(mealCourse.Drink, drinks);
        }};
        return mp;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "appetizers=" + appetizers +
                ", mains=" + mains +
                ", deserts=" + deserts +
                ", drinks=" + drinks +
                '}';
    }
}