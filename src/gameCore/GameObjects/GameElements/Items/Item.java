package gameCore.GameObjects.GameElements.Items;
import exceptions.NonValidValueException;
import exceptions.NonValidWeightException;
import gameCore.GameObjects.Model;

/**
 * Le model plus spécifique aux objets récupérables. Super-Classe.
 */
public class Item extends Model {
    protected final int  value;
    protected final int weight;

    /**
     * Constructeur
     * @param itValue
     * Valeur de l'item en pieces.
     * @param itWeight
     * Poid de l'item.
     * @throws NonValidValueException
     * Si la valeur de l'item est inferieur a 0.
     * @throws NonValidWeightException
     * Si le poid de l'item est inferieur a 0.
     */
    public Item(String itName, String itDescription, int itValue, int itWeight)
            throws NonValidValueException, NonValidWeightException {

        super(itName, itDescription);

        if (itValue < 0) throw new NonValidValueException();
        if (itWeight < 0) throw new NonValidWeightException();

        this.value = itValue;
        this.weight = itWeight;
    }

    // Getters
    public int getValue() { return value; }
    public int getWeight() { return weight; }

    // affichage
    @Override
    public String toString() {
        return this.name + ", " +
                this.value + "Pieces" + ", " +
                this.weight + "Kg" + "\n" +
                this.description;
    }
}
