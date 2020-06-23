package njpi.soso.Service;

/**
 * @author poppy
 * @mail poppyalyx1983@gmail.com
 */

import njpi.soso.Models.MobileCard;

public interface NetService {
    /**
     * @param flow
     * @param card
     * @throws Exception
     */
    public int netPlay(int flow,MobileCard card) throws Exception;
}