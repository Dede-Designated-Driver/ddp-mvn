package org.dedriver;

import org.dedriver.model.Msg;

/**
 * Transmits a {@link org.dedriver.model.Msg}.
 */
public interface Tx {

    /**
     * Transmits a {@link org.dedriver.model.Msg} using Dede Protocol (ddp)
     *
     * @param msg the {@link Msg} to be transmitted
     */
    void send(final Msg msg);
}
