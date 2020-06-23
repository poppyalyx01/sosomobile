package njpi.soso.Service;

import njpi.soso.Models.MobileCard;

public interface NetService {
    /**
     * @param flow
     * @param card
     * @return
     * @throws Exception
     */
    public int netPlay(int flow,MobileCard card) throws Exception;
}