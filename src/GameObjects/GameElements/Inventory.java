package GameObjects.GameElements;

import GameObjects.GameElements.Items.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> inventory;
    private int actualWeight;
    private int maxWeight;

    // Constructeur
    public Inventory(int itMaxWeight) {
        this.inventory = new ArrayList<Item>();
        this.actualWeight = 0;
        this.maxWeight = itMaxWeight;
    }

    // getter
    public List getInventory() { return this.inventory; }

    public void upMaxWeight(int howMany) { this.maxWeight += howMany; }

    public boolean checkInventory(Item thisOne) { return (this.inventory.contains(thisOne)); }

    public boolean removeItem(Item thisOne) {
        boolean isIn = this.checkInventory(thisOne);
        if (isIn) {
            this.inventory.remove(thisOne);
            this.actualWeight -= thisOne.getWeight();
        }
        return isIn;
    }

    public boolean addItem(Item thisOne) {
        boolean isAdded = false;
        if (thisOne.getWeight()+this.actualWeight < this.maxWeight) {
            this.inventory.add(thisOne);
            this.actualWeight += thisOne.getWeight();
            isAdded = true;
        }
        return isAdded;
    }

    // Affichage
    @Override
    public String toString() {
        this.inventory.forEach( (item) -> {
            System.out.println("   " + item + "\n");
        } );
        return "";
    }
}
