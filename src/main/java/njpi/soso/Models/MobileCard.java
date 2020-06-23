package njpi.soso.Models;

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
     * @param consumAmout
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
    /**
     * @return
     */
    public String getCardNumber() {
        return cardNumber;
    }
    /**
     * @return
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    /**
     * @return
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @return
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * @return
     */
    public String getPassWord() {
        return passWord;
    }
    /**
     * @return
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    /**
     * @return
     */
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
    /**
     * @return
     */
    public void setSerPackage(ServicePackage serPackage) {
        this.serPackage = serPackage;
    }
    /**
     * @return
     */
    public double getConsumAmout() {
        return consumAmout;
    }
    /**
     * @return
     */
    public void setConsumAmout(double consumAmout) {
        this.consumAmout = consumAmout;
    }
    /**
     * @return
     */
    public double getMoney() {
        return money;
    }
    /**
     * @return
     */
    public void setMoney(double money) {
        this.money = money;
    }
    /**
     * @return
     */
    public int getRealTakTime() {
        return realTakTime;
    }
    /**
     * @return
     */
    public void setRealTakTime(int realTakTime) {
        this.realTakTime = realTakTime;
    }
    /**
     * @return
     */
    public int getRealSMSCount() {
        return realSMSCount;
    }
    /**
     * @return
     */
    public void setRealSMSCount(int realSMSCount) {
        this.realSMSCount = realSMSCount;
    }
    /**
     * @return
     */
    public int getRealFlow() {
        return realFlow;
    }
    /**
     * @param realFlow
     */
    public void setRealFlow(int realFlow) {
        this.realFlow = realFlow;
    }

    public void showMeg() {
        System.out.println("卡号:"+this.getCardNumber()+"用户名:"+this.getUserName()+"当前余额:"+this.getMoney()+"元");
        this.serPackage.showInfo();
    }
}