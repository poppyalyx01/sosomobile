package njpi.soso.Models;

public abstract class ServicePackage {

    private double price;
    public double getPrice() {
        return price;
    }
    /**
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    public abstract void showInfo();

}