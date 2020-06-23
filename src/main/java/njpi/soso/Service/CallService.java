package njpi.soso.Service;

import njpi.soso.Models.MobileCard;

public interface CallService {
    /**
     * @param minCount
     * @param card
     * @return
     * @throws Exception
     */
    public	int call(int minCount,MobileCard card) throws Exception;
}