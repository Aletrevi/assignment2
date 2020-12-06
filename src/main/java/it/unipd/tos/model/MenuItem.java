////////////////////////////////////////////////////////////////////
// [Alessio] [Trevisan] [1187399]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class MenuItem {
    public enum items {Budino, Gelato, Bevanda};
    private items itemName;
    private String name;
    private double price;
    public MenuItem(items i, String n, double pr){
        this.itemName = i;
        this.name = n;
        this.price = pr;
    }
    public items getItemName(){
        return itemName;
    }
    public String getNameMenu(){
        return name;
    }
    public double getPrice(){
        return price;
    }
}