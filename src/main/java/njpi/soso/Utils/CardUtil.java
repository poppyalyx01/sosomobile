package njpi.soso.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import njpi.soso.Models.ConsumInfo;
import njpi.soso.Models.MobileCard;
import njpi.soso.Models.NetPackage;
import njpi.soso.Models.Scene;
import njpi.soso.Models.ServicePackage;
import njpi.soso.Models.SuperPackage;
import njpi.soso.Models.TalkPackage;
import njpi.soso.Service.CallService;
import njpi.soso.Service.SendService;

public class CardUtil {
    Scanner input=new Scanner(System.in);
    public static Map<String, MobileCard> cards = new HashMap<String, MobileCard>();
    public static Map<String, List<ConsumInfo>> consumInfos = new HashMap<String, List<ConsumInfo>>();
    Map<Integer, Scene> scenes = new HashMap<Integer, Scene>();
    private Database db = new Database();

    Scene scene0 = new Scene("通话", 90, "您通话90分钟");
    Scene scene1 = new Scene("通话", 30, "您通话30分钟");
    Scene scene2 = new Scene("短信", 5, "您发送短信5条");
    Scene scene3 = new Scene("短信", 50, "您发送短信50条");
    Scene scene4 = new Scene("上网", 1024, "您使用流量1GB");
    Scene scene5 = new Scene("上网", 2 * 1024, "您使用流量2GB");

    public void intitScene() {
        fetchAll();
    }

    public void fetchAll(){
        try (Connection conn = db.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery("SELECT * FROM card")){
            // loop through the result set
            while (rs.next()) {

                MobileCard card=new MobileCard(
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        createPackage(rs.getInt("serPackage")),
                        rs.getDouble("consumAmout"),
                        rs.getDouble("money"),
                        rs.getInt("realTakTime"),
                        rs.getInt("realSMSCount"),
                        rs.getInt("realFlow")
                );
                addCard(card);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateTable(MobileCard card) {
        String sql = "INSERT INTO card(id,username,password,serPackage,money,consumAmout,realTakTime,realSMSCount,realFlow) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.setString(1, card.getCardNumber());
             pstmt.setString(2, card.getUserName());
             pstmt.setString(3, card.getPassWord());
             pstmt.setInt(4, card.getSerPackages());
             pstmt.setDouble(5, card.getMoney());
             pstmt.setDouble(6, card.getConsumAmout());
             pstmt.setInt(7, card.getRealTakTime());
             pstmt.setInt(8, card.getRealSMSCount());
             pstmt.setInt(9, card.getRealFlow());
             pstmt.executeUpdate();
             pstmt.close();
             conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void init(){
        Iterator it = CardUtil.cards.entrySet().iterator();

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM card")) {
             pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            MobileCard card = cards.get(pair.getKey());
            updateTable(card);
        }
    }
    /**
     * @param card
     */
    public void addCard(MobileCard card) {
        cards.put(card.getCardNumber(), card);
    }

    /**
     * @param number
     * @param money
     */
    public void chargeMoney(String number, double money) {
        cards.get(number).setMoney(cards.get(number).getMoney()+money);
    }

    /**
     * @param number
     */
    public void userSoso(String number) {

        scenes.put(0, scene0);
        scenes.put(1, scene1);
        scenes.put(2, scene2);
        scenes.put(3, scene3);
        scenes.put(4, scene4);
        scenes.put(5, scene5);

        MobileCard card=cards.get(number);
        ServicePackage pack=card.getSerPackage();
        Random random=new Random();
        int ranNum=0;
        int temp=0;
        do {
            ranNum=random.nextInt(6);
            Scene scene=scenes.get(ranNum);
            switch (ranNum) {
                case 0:
                case 1:
                    if (pack instanceof CallService) {
                        System.out.println(scene.getDescription());
                        CallService callService=(CallService)pack;
                        try {
                            temp=callService.call(scene.getData(), card);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        addConsumInfo(number, new ConsumInfo(number, scene.getType(), temp));
                        break;
                    }else {
                        continue;
                    }
                case 2:
                case 3:
                    if (pack instanceof SendService) {
                        System.out.println(scene.getDescription());
                        SendService sendService=(SendService)pack;
                        try {
                            temp=sendService.send(scene.getData(), card);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        addConsumInfo(number, new ConsumInfo(number, scene.getType(), temp));
                        break;
                    }else {
                        continue;
                    }
                case 4:
                case 5:
                    if (pack instanceof NetPackage) {
                        System.out.println(scene.getDescription());
                        NetPackage netPackage=(NetPackage)pack;
                        try {
                            temp=netPackage.netPlay(scene.getData(), card);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        addConsumInfo(number, new ConsumInfo(number, scene.getType(), temp));
                        break;
                    }else {
                        continue;
                    }
            }
            break;
        } while (true);

    }

    public void showDescription() {
        System.out.println(
                "\n**话唠套餐**\n"+
                "通话时长(分钟): 500\n" +
                "上网流量(GB): 0\n" +
                "短信条数(条): 30\n" +
                "资费(元): 58\n\n"+
                "**网虫套餐**\n"+
                "通话时长(分钟): 0\n" +
                "上网流量(GB): 3\n" +
                "短信条数(条): 0\n" +
                "资费(元): 68\n\n"+
                "**超人套餐**\n"+
                "通话时长(分钟): 200\n" +
                "上网流量(GB): 1\n" +
                "短信条数(条): 50\n" +
                "资费(元): 78\n");
    }

    /**
     * @param number
     */
    public void showAmountDetail(String number) {
        StringBuffer meg = new StringBuffer();
        MobileCard card=cards.get(number);
        meg.append("您的卡号:"+card.getCardNumber()+"当月账单:\n");
        meg.append("套餐资费:"+card.getSerPackage().getPrice()+"元\n");
        meg.append("合计:"+Common.dataFormat(card.getConsumAmout())+"元\n");
        meg.append("账号余额:"+Common.dataFormat(card.getMoney())+"元");
        System.out.println(meg);
    }

    /**
     * @param number
     */
    public void showRemainDetail(String number) {
        MobileCard card=cards.get(number);
        int remainTalkTime;
        int remainSmsCount;
        double remainFlow;
        StringBuffer meg=new StringBuffer();
        meg.append("您的卡号是"+number+",套餐内剩余:\n");
        ServicePackage pack=card.getSerPackage();
        if (pack instanceof TalkPackage) {
            TalkPackage cardPack=(TalkPackage)pack;
            remainTalkTime=cardPack.getTalkTime()>card.getRealTakTime()?cardPack.getTalkTime()
                    -card.getRealTakTime():0;
            meg.append("通话时长:"+remainTalkTime+"分钟\n");
            remainSmsCount=cardPack.getSmsCount()>card.getRealSMSCount()?cardPack.getSmsCount()
                    -card.getRealSMSCount():0;
            meg.append("短信条数:"+remainSmsCount+"条");
        }else if (pack instanceof NetPackage) {
            NetPackage netPackage=(NetPackage)pack;
            remainFlow=netPackage.getFlow()*1024>card.getRealFlow()?netPackage.getFlow()*1024
                    -card.getRealFlow():0;
            meg.append("上网流量:"+Common.dataFormat(remainFlow/1024)+"GB");
        }else {
            SuperPackage superPackage=(SuperPackage)pack;
            remainTalkTime=superPackage.getTalkTime()>card.getRealTakTime()?superPackage.getTalkTime()
                    -card.getRealTakTime():0;
            meg.append("通话时长:"+remainTalkTime+"分钟\n");
            remainSmsCount=superPackage.getSmsCount()>card.getRealSMSCount()?superPackage.getSmsCount()
                    -card.getRealSMSCount():0;
            meg.append("短信条数:"+remainSmsCount+"条");
            remainFlow=superPackage.getFlow()*1024>card.getRealFlow()?superPackage.getFlow()*1024
                    -card.getRealFlow():0;
            meg.append("上网流量:"+Common.dataFormat(remainFlow/1024)+"GB");
        }
        System.out.println(meg);
    }

    /**
     * @param number
     */
    public void printAmountDetail(String number){
        Writer fileWriter = null;
        try {
            String url = Database.class.getClassLoader().getResource("consumes.txt").toString().replace("file:","");
            fileWriter = new FileWriter(url);
            Set<String> numbers = consumInfos.keySet();
            Iterator<String> it = numbers.iterator();
            List<ConsumInfo> infos = new ArrayList<ConsumInfo>();
            infos = consumInfos.get(number);
            boolean isExist = false;
            while(it.hasNext()){
                String numberKey = it.next();
                if(number.equals(numberKey)){
                    isExist = true;
                }/*else{
                    isExist = false;
                }*/

            }

            if(isExist){
                StringBuffer content = new StringBuffer("***********" + number + "消费记录************\n");
                content.append("序号\t类型\t数据（通话（分钟）/上网（MB）/短信（条））\n");
                for(int i = 0; i < infos.size(); i++){
                    ConsumInfo info = infos.get(i);
                    System.out.println((i + 1) + ".\t" + info.getType() + "\t" + info.getConsumData() + "\n");
                    content.append((i + 1) + ".\t" + info.getType() + "\t" + info.getConsumData() + "\n");
                }
                fileWriter.write(content.toString());
                fileWriter.flush();
                System.out.println("消息记录打印完毕！");
            }else{
                System.out.println("对不起，不存在此号码的消费记录，不能够打印！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param number
     */
    public void chargeingPack(String number) {
        System.out.println("1.话唠套餐 2.网虫套餐 3.超人套餐 ：请选择(序号)：");
        int packNum = input.nextInt();
        switch(packNum){
            case 1:
                if(cards.get(number).getSerPackage() instanceof TalkPackage){
                    System.out.println("对不起，您已经是该套餐用户，无需换套餐！");
                }else{
                    if(cards.get(number).getMoney() < cards.get(number).getSerPackage().getPrice()){
                        System.out.println("对不起,您的的余额不足以支付新的套餐本月资费，请充值后再办理业务！");
                    }else{
                        cards.get(number).setRealSMSCount(0);
                        cards.get(number).setRealTakTime(0);
                        cards.get(number).setRealFlow(0);
                        cards.get(number).setSerPackage(createPackage(packNum));
                        System.out.println("套餐更换成功！" );
//                    Common.talkPackage.showInfo();
                        createPackage(packNum).showInfo();
                    }
                }
                break;
            case 2:
                if(cards.get(number).getSerPackage() instanceof NetPackage){
                    System.out.println("对不起，您已经是该套餐用户，无需换套餐！");
                }else{
                    if(cards.get(number).getMoney() < cards.get(number).getSerPackage().getPrice()){
                        System.out.println("对不起,您的的余额不足以支付新的套餐本月资费，请充值后再办理业务！");
                    }else{
                        cards.get(number).setRealSMSCount(0);
                        cards.get(number).setRealTakTime(0);
                        cards.get(number).setRealFlow(0);
                        cards.get(number).setSerPackage(createPackage(packNum));
                        System.out.println("套餐更换成功！" );
//                    Common.netPackage.showInfo();
                        createPackage(packNum).showInfo();
                    }
                }
                break;
            case 3:
                if(cards.get(number).getSerPackage() instanceof SuperPackage){
                    System.out.println("对不起，您已经是该套餐用户，无需换套餐！");
                }else{
                    if(cards.get(number).getMoney() < cards.get(number).getSerPackage().getPrice()){
                        System.out.println("对不起,您的的余额不足以支付新的套餐本月资费，请充值后再办理业务！");
                    }else{
                        cards.get(number).setRealSMSCount(0);
                        cards.get(number).setRealTakTime(0);
                        cards.get(number).setRealFlow(0);
                        cards.get(number).setSerPackage(createPackage(packNum));
                        System.out.println("套餐更换成功！" );
//                    Common.superPackage.showInfo();
                        createPackage(packNum).showInfo();
                    }
                }
                break;
        }
    }

    /**
     * @param number
     */
    public void delCard(String number) {
        if (cards.containsKey(number)) {
            cards.remove(number);
            System.out.println("卡号:"+number+"办理退网成功！");
            System.out.println("谢谢使用！");
        }else {
            System.out.println("手机号码输入有误！");
        }
    }

    /**
     * @param number
     * @param pwd
     * @return
     */
    public boolean isExistCard(String number, String pwd) {
        if (cards.containsKey(number)&&(cards.get(number).getPassWord().equals(pwd))) {
            return true;
        }
        System.out.println("登录失败!");
        return false;
    }

    /**
     * @param number
     * @return
     */
    public boolean isExistCard(String number) {
        Set<String>	numbers=cards.keySet();
        Iterator<String> iterator=numbers.iterator();
        while (iterator.hasNext()) {
            String str=iterator.next();
            if (str.equals(number))
                return true;
        }
        return false;
    }

    /**
     * @return
     */
    public String createNumber(){
        Random random = new Random();
        boolean isExist = false;
        String number = "";
        int temp = 0;
        do{
            isExist = false;
            do{
                temp = random.nextInt(100000000);
            }while(temp < 10000000);
            number = "139" + temp;
            if(cards != null){
                Set<String> cardNumbers = cards.keySet();
                for(String cardNumber : cardNumbers){
                    if(number.equals(cardNumber)){
                        isExist = true;
                        break;
                    }
                }
            }
        }while(isExist);
        return number;
    }

    /**
     * @param count
     * @return
     */
    public String[] getNewNumber(int count) {
        String[] strs = new String[count];
        for (int i = 0; i < count; i++) {
            strs[i] = createNumber();
        }
        return strs ;
    }

    /**
     * @param number
     * @param info
     */
    public void addConsumInfo(String number, ConsumInfo info) {
        if (consumInfos.containsKey(number)) {
            consumInfos.get(number).add(info);
        }else{
            List<ConsumInfo> list = new ArrayList<ConsumInfo>();
            list.add(info);
            consumInfos.put(number, list);
        }
    }

    /**
     * @param packId
     * @return
     */
    public ServicePackage createPackage(int packId) {
        ServicePackage servicePackage=null;
        switch (packId) {
            case 1:
                servicePackage=new TalkPackage();
                break;
            case 2:
                servicePackage=new NetPackage();
                break;
            case 3:
                servicePackage=new SuperPackage();
                break;
            default:
                break;
        }
        return servicePackage;
    }
}