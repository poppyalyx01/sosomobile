package njpi.soso.Models;

/**
 * @author poppy
 * @mail poppyalyx1983@gmail.com
 */

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