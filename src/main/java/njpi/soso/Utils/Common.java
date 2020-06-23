package njpi.soso.Utils;

import java.text.DecimalFormat;

public class Common {
    /**
     * @param data
     * @return
     */
    public static String dataFormat(double data) {
        DecimalFormat format=new DecimalFormat("#0.00");
        return format.format(data);
    }
    /**
     * @param sum1
     * @param sum2
     * @return
     */
    public static double sub(double sum1, double sum2) {
        return (sum1*10-sum2*10)/10;
    }

}