package org.dedriver;

import org.dedriver.model.Address;
import org.dedriver.model.MsgVehicle;
import org.json.JSONObject;

/**
 * Transmits a {@link MsgVehicle}.
 */
public interface Tx {

    /**
     * Transmits a {@link MsgVehicle} using Dede Protocol (ddp)
     *
     * @param msg     the {@link MsgVehicle} to be transmitted
     * @param address the {@link Address} to be used as destination
     */
    void send(MsgVehicle msg, Address address);

    /**
     * Transmits a {@link JSONObject} using Dede Protocol (ddp)
     *
     * @param msg     the {@link JSONObject} to be transmitted
     * @param address the {@link Address} to be used as destination
     */
    void send(JSONObject msg, Address address);
}
