package njpi.soso.Service;

import njpi.soso.Models.MobileCard;

public interface SendService {
    /**
     * @param count
     * @param card
     * @return
     * @throws Exception
     */
    public int send(int count,MobileCard card) throws Exception;
}