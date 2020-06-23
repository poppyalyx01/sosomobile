package njpi.soso.test;

/**
 * @author poppy
 * @mail poppyalyx1983@gmail.com
 */

import njpi.soso.Models.MobileCard;
import njpi.soso.Models.ServicePackage;
import njpi.soso.Utils.CardUtil;

import java.util.Scanner;

public class main {

    Scanner input=new Scanner(System.in);
    CardUtil cardUtil=new CardUtil();
    MobileCard mobileCard=new MobileCard();

    ServicePackage service= null;

    public static void main(String[] args) {
        main main = new main();
        main.start();
    }

    public void start() {
        cardUtil.intitScene();
        boolean isEsc=true;
        do {
            cardUtil.init();
            System.out.println("*******************欢迎使用嗖嗖移动业务大厅*******************");
            System.out.println("1.用户登录  2.用户注册  3.使用嗖嗖  4.话费充值  5.资费说明  6.退出系统");
            System.out.print("请选择：");
            int key=1;
            try {
                key = input.nextInt();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("输入有误，请重新输入！");
            }
            switch (key) {
                case 1:
                    cardMenu();
                    continue;
                case 2:
                    registCard();
                    continue;
                case 3:
                    System.out.println("执行使用嗖嗖功能");
                    System.out.println("请输入手机卡号:");
                    String number=input.next();
                    cardUtil.userSoso(number);
                    continue;
                case 4:
                    moneyRecharge();
                    continue;
                case 5:
                    cardUtil.showDescription();
                    continue;
                case 6:
                    System.out.println("谢谢使用！");
                    break;
                default:
                    System.out.println("输入有误，请重新输入！");
                    continue;
            }
        } while (isEsc);
    }

    public void moneyRecharge() {
        System.out.println("请输入手机卡号：");
        String strNum=input.next();
        boolean is=cardUtil.isExistCard(strNum);
        if (is) {
            System.out.println("请输入充值余额：");
            double strMoney=input.nextDouble();
            cardUtil.chargeMoney(strNum,strMoney);
            System.out.println("充值成功,当前话费余额为"+CardUtil.cards.get(strNum).getMoney()+"元");
        }else {
            System.out.println("手机输入有误，请重新输入手机号：");
        }
    }

    public void cardMenu() {
        System.out.println("请输入手机卡号：");
        String numberMoney=input.next();
        System.out.println("请输入密码：");
        String pwd=input.next();
        if (cardUtil.isExistCard(numberMoney,pwd)) {
            System.out.println("登录成功");
        }else {
            return;
        }
        boolean isEsc=true;
        do {
            System.out.println("\n******嗖嗖移动用户菜单******");
            System.out.println("1.本月账单查询");
            System.out.println("2.套餐余量查询");
            System.out.println("3.打印消费详单");
            System.out.println("4.套餐变更");
            System.out.println("5.办理退网");
            System.out.print("请选择(输入1~5选择功能,其他键返回上一级)：");
            int key=1;
            try {
                key = input.nextInt();
            } catch (Exception e) {
                new main().start();
            }
            switch (key) {
                case 1:
                    cardUtil.showAmountDetail(numberMoney);
                    continue;
                case 2:
                    cardUtil.showRemainDetail(numberMoney);
                    continue;
                case 3:
                    cardUtil.printAmountDetail(numberMoney);
                    continue;
                case 4:
                    cardUtil.chargeingPack(numberMoney);
                    continue;
                case 5:
                    cardUtil.delCard(numberMoney);
                    continue;
                default:
                    isEsc = false;
                    new main().start();
            }
        } while (isEsc);
    }

    public void registCard() {
        System.out.println("************可选择的卡号************");
        String[] cardNumbers = cardUtil.getNewNumber(9);
        for (int i = 0; i <cardNumbers.length; i++) {
            System.out.print((i+1)+":"+cardNumbers[i]+"\t");
            if (2==i ||5==i||8==i) {
                System.out.println();
            }
        }
        System.out.println("请选择卡号(输入1~9的序号)：");
        int key = input.nextInt();

        if (key >= 1 && key <= 9) {
            mobileCard.setCardNumber(cardNumbers[key - 1]);
            System.out.println("1.话唠套餐  2.网虫套餐  3.超人套餐 ， 请选择套餐（输入序号）:");
        } else {
            System.out.print("输入错误！请输入（1~9）的整数:");
        }

        boolean bol = true;

        int packageNum = input.nextInt();
        while (bol) {
            if (packageNum<=3 && packageNum>=1) {
                service= cardUtil.createPackage(packageNum);
                mobileCard.setSerPackage(service);  //
                bol = false;
            } else {
                System.out.println("输入错误，请重新选择：");
                packageNum = input.nextInt();
            }
        }

        System.out.println("请输入姓名：");
        String userName =  input.next();
        System.out.println("请输入密码：");
        String passWord = input.next();
        System.out.println("请输入预存话费：");
        double money=input.nextInt();
        while (money<service.getPrice()) {
            System.out.println("您预存话费金额不足以支付本月固定套餐资费，请重新充值：");
            money=input.nextInt();
        }
        mobileCard.setMoney(money-service.getPrice());
        MobileCard card=new MobileCard(mobileCard.getCardNumber(), userName, passWord,mobileCard.getSerPackage(),0,mobileCard.getMoney(),0,0,0);
        cardUtil.addCard(card);
        card.showMeg();
    }
}