package njpi.soso.Models;

public class ConsumInfo {

    private String number;
    private String type;
    private int consumData;

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getConsumData() {
        return consumData;
    }
    public void setConsumData(int consumData) {
        this.consumData = consumData;
    }
    public ConsumInfo(String number, String type, int note) {
        this.number = number;
        this.type = type;
        this.consumData = note;
    }

}