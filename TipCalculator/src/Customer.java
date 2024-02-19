import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * Customer object, responsible for the collecting of orders and calculations of prices
 */
public class Customer {

    //Initialize the list of ordered items, the total cost and the tip percent to be declared later.
    private ObservableList<Order> orderedItems;

    private double grossCost;
    private double tipPercent;

    //Constructor for customer without arguments. Defaults to a 0 cost and a 15% tip
    public Customer() {
        this.orderedItems = FXCollections.observableArrayList();
        setGrossCost(0);
        setTipPercent(.15);
    }

    //Add an item to the customer's list and adds the cost to the gross total
    public void addCustomerItem(Order order){
        orderedItems.add(order);
        grossCost += order.getPrice();
    }

    /*
     * @return the gross cost
     */
    public double getGrossCost(){
        return grossCost;
    }

    /*
     * @return the amount tipped
     */
    public double getTipCost(){
        return getGrossCost() * (tipPercent / 100);
    }

    /*
     * @return the total cost of the tax
     */
    public double getTaxCost(){
        return (getGrossCost() + getTipCost()) * .08265;
    }

    /*
     * @return formatted price calculations as string
     */
    public String getReceipt(){
        StringBuilder str = new StringBuilder();
        str.append("Subtotal: \t\t\t");
        str.append(String.format("$%.2f", getGrossCost()));
        str.append(String.format("\nTip (%.0f%%): \t\t\t", tipPercent));
        str.append(String.format("$%.2f", getTipCost()));
        str.append("\nTax (8.625%): \t\t\t");
        str.append(String.format("$%.2f", getTaxCost()));
        str.append("\n\nTotal: \t\t\t");
        str.append(String.format("$%.2f", (getGrossCost() + getTipCost() + getTaxCost())));
        return str.toString();
    }

    /*
     * Set the gross cost
     *
     * @param the gross cost
     */
    public void setGrossCost(double grossCost) {
        this.grossCost = grossCost;
    }


    /*
     * Sets the tip decimal
     *
     * @param tipPercent in percent, to convert to decimal
     */
    public void setTipPercent(double tipPercent) {
        this.tipPercent = tipPercent;
    }

    /*
     * @return the observable list of items
     */
    public ObservableList<Order> getOrderedItems(){
        return orderedItems;
    }

    /*
     * Turns the orders into a string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Order order : orderedItems) {
            stringBuilder.append(order.getItem());
            stringBuilder.append("\t\t");
            stringBuilder.append(String.format("$%.2f%n", order.getPrice()));
        }

        return stringBuilder.toString();
    }
}
