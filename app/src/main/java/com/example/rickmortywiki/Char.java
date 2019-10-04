package com.example.rickmortywiki;

import java.util.Comparator;

class Char {
    private int id;
    private String name;

    public Char (int id,String name){
        this.id  =id;
        this.name=name;
    }
    public int getIdInfo(){
        return id;
    }
    public String getNameInfo(){
        return name;
    }
}
class SortById implements Comparator<Char>{

    @Override
    public int compare(Char a, Char b) {
        return a.getIdInfo()-b.getIdInfo();
    }
}
class SortByName implements Comparator<Char>{

    @Override
    public int compare(Char a, Char b) {
        return a.getNameInfo().compareTo(b.getNameInfo());
    }
}