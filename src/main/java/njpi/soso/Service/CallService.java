package njpi.soso.Service;

/**
 * @author poppy
 * @mail poppyalyx1983@gmail.com
 */

import njpi.soso.Models.MobileCard;

public interface CallService {
    /**
     * @param minCount
     * @param card
     * @throws Exception
     */
    public	int call(int minCount,MobileCard card) throws Exception;
}