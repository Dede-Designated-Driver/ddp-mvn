package org.dedriver;

import org.dedriver.model.Address;
import org.dedriver.model.MsgDedeObu;
import org.json.JSONObject;

/**
 * Transmits a {@link MsgDedeObu}.
 */
public interface Tx {

    /**
     * Transmits a {@link MsgDedeObu} using Dede Protocol (ddp)
     *
     * @param msg     the {@link MsgDedeObu} to be transmitted
     * @param address the {@link Address} to be used as destination
     */
    void send(MsgDedeObu msg, Address address);

    /**
     * Transmits a {@link JSONObject} using Dede Protocol (ddp)
     *
     * @param msg     the {@link JSONObject} to be transmitted
     * @param address the {@link Address} to be used as destination
     */
    void send(JSONObject msg, Address address);
}
