/*
 * The order object, where the item and price are turned into an object
 */
public class Order {
    //Initialize the item and price
    private String item;
    private double price;

    /*
     * Constructor for the order object without arguments, defaults to an empty string and a price of 0
     */
    public Order() {
        this("", 0);
    }

    /*
     * Constructor for the order object with arguments
     *
     * @param the name and the price of the item
     */
    public Order(String item, double price) {
        setItem(item);
        setPrice(price);
    }

    /*
     * @return the name of the item
     */
    public String getItem() {
        return item;
    }

    /*
     * Set the value of the name of the item
     *
     * @param the string containing the name of the item
     */
    public void setItem(String item) {
        this.item = item;
    }

    /*
     * @return the price of the item
     */
    public double getPrice() {
        return price;
    }

    /*
     * Sets the price of the item
     *
     * @param the price of the item
     */
    public void setPrice(double price) {
        if(price >= 0) {
            this.price = price;
        }
    }
}
