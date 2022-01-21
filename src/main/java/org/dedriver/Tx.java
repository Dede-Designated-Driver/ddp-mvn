package org.dedriver;

import org.dedriver.model.Adr;
import org.dedriver.model.Msg;

/**
 * Transmits a {@link org.dedriver.model.Msg}.
 */
public interface Tx {

    /**
     * Transmits a {@link org.dedriver.model.Msg} using Dede Protocol (ddp)
     *
     * @param msg the {@link Msg} to be transmitted
     * @param adr the {@link Adr} to be used as destination of the {@link Msg}
     */
    void send(final Msg msg, final Adr adr);
}
