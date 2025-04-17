package gameCore.GameObjects.GameElements;

import gameCore.GameObjects.GameElements.Items.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * L'inventaire des héro
 * TODO Modifié pour qu'il sois propre au player ?
 */
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

    // getters
    public List<Item> getInventory() { return this.inventory; }
    public int getActualWeight() { return actualWeight; }
    public int getMaxWeight() { return maxWeight; }

    /**
     * Augmente la taille maximum de l'inventaire.
     * @param howMany
     * La taille de l'augmentation.
     */
    public void upMaxWeight(int howMany) { this.maxWeight += howMany; }

    /**
     * Vérifie si un Item, donné, est présent dans l'inventaire.
     * @param thisOne
     * L'item.
     * @return
     * Vrai s'il est présent, faux sinon.
     */
    public boolean checkInventory(Item thisOne) { return (this.inventory.contains(thisOne)); }

    /**
     * Essaie d'enlèver un Item donné de l'inventaire.
     * @param thisOne
     * L'item.
     * @return
     * Vrai s'il a pu être enlevé.
     */
    public boolean removeItem(Item thisOne) {
        boolean isIn = this.checkInventory(thisOne);
        if (isIn) {
            this.inventory.remove(thisOne);
            this.actualWeight -= thisOne.getWeight();
        }
        return isIn;
    }

    /**
     * Essaie d'ajouter un Item donné à l'inventaire.
     * @param thisOne
     * L'item.
     * @return
     * Vrai s'il a été ajouté, faux sinon.
     */
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
            System.out.println("   " + item + "; ");
        } );
        return "";
    }
}
