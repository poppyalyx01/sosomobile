package njpi.soso.Models;

/**
 * @author poppy
 * @mail poppyalyx1983@gmail.com
 */

public class MobileCard {

    private String cardNumber;
    private String userName;
    private String passWord;
    private ServicePackage serPackage;
    private double consumAmout;
    private double money;
    private int realTakTime;
    private int realSMSCount;
    private int realFlow;

    public MobileCard(){}
    /**
     * @param cardNumber
     * @param userName
     * @param passWord
     * @param serPackage
     */
    public MobileCard(String cardNumber, String userName, String passWord, ServicePackage serPackage, double money){
        this.cardNumber = cardNumber;
        this.userName = userName;
        this.passWord = passWord;
        this.serPackage = serPackage;
        this.money = money;
    }
    /**
     * @param cardNumber
     * @param userName
     * @param passWord
     * @param serPackage
     * @param consumAmout
     * @param money
     * @param realTakTime
     * @param realSMSCount
     * @param realFlow
     */
    public MobileCard(String cardNumber, String userName, String passWord, ServicePackage serPackage,
                      double consumAmout, double money, int realTakTime, int realSMSCount, int realFlow) {

        this.cardNumber = cardNumber;
        this.userName = userName;
        this.passWord = passWord;
        this.serPackage = serPackage;
        this.consumAmout = consumAmout;
        this.money = money;
        this.realTakTime = realTakTime;
        this.realSMSCount = realSMSCount;
        this.realFlow = realFlow;
    }

    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public ServicePackage getSerPackage() {
        return serPackage;
    }

    public int getSerPackages() {
        int packages = 1;
        if(this.serPackage.getPrice() == 68){
            packages = 2;
        }else if(this.serPackage.getPrice() == 78){
            packages = 3;
        }
        return packages;
    }

    public void setSerPackage(ServicePackage serPackage) {
        this.serPackage = serPackage;
    }
    public double getConsumAmout() {
        return consumAmout;
    }
    public void setConsumAmout(double consumAmout) {
        this.consumAmout = consumAmout;
    }
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public int getRealTakTime() {
        return realTakTime;
    }
    public void setRealTakTime(int realTakTime) {
        this.realTakTime = realTakTime;
    }
    public int getRealSMSCount() {
        return realSMSCount;
    }
    public void setRealSMSCount(int realSMSCount) {
        this.realSMSCount = realSMSCount;
    }
    public int getRealFlow() {
        return realFlow;
    }

    public void setRealFlow(int realFlow) {
        this.realFlow = realFlow;
    }

    public void showMeg() {
        System.out.println("卡号:"+this.getCardNumber()+"用户名:"+this.getUserName()+"当前余额:"+this.getMoney()+"元");
        this.serPackage.showInfo();
    }
}