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
     * @return getCardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }
    /**
     * @return setCardNumber
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    /**
     * @return getUserName
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @return setUserName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * @return getPassWord
     */
    public String getPassWord() {
        return passWord;
    }
    /**
     * @return setPassWord
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    /**
     * @return getSerPackage
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
     * @return setSerPackage
     */
    public void setSerPackage(ServicePackage serPackage) {
        this.serPackage = serPackage;
    }
    /**
     * @return getConsumAmout
     */
    public double getConsumAmout() {
        return consumAmout;
    }
    /**
     * @return setConsumAmout
     */
    public void setConsumAmout(double consumAmout) {
        this.consumAmout = consumAmout;
    }
    /**
     * @return getMoney
     */
    public double getMoney() {
        return money;
    }
    /**
     * @return setMoney
     */
    public void setMoney(double money) {
        this.money = money;
    }
    /**
     * @return getRealTakTime
     */
    public int getRealTakTime() {
        return realTakTime;
    }
    /**
     * @return setRealTakTime
     */
    public void setRealTakTime(int realTakTime) {
        this.realTakTime = realTakTime;
    }
    /**
     * @return getRealSMSCount
     */
    public int getRealSMSCount() {
        return realSMSCount;
    }
    /**
     * @return setRealSMSCount
     */
    public void setRealSMSCount(int realSMSCount) {
        this.realSMSCount = realSMSCount;
    }
    /**
     * @return getRealFlow
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