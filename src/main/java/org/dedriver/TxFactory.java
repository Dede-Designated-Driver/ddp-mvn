package org.dedriver;

/**
 * Factory for creating instances of {@link Tx}.
 */
public class TxFactory {

    /**
     * Creates an instance of {@link Tx}
     *
     * @return the new instance
     */
    public static Tx createTx() {
        return new TxImpl();
    }
}
