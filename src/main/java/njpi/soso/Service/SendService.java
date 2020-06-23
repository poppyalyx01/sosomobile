package njpi.soso.Service;

/**
 * @author poppy
 * @mail poppyalyx1983@gmail.com
 */

import njpi.soso.Models.MobileCard;

public interface SendService {
    /**
     * @param count
     * @param card
     * @throws Exception
     */
    public int send(int count,MobileCard card) throws Exception;
}