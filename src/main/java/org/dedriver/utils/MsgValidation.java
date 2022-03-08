package org.dedriver.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.dedriver.utils.StringUtils.isNumeric;

public class MsgValidation {

    private final static Logger LOG = LoggerFactory.getLogger(MsgValidation.class);

    public final static int TS_MIN = 0;

    public final static int TS_INVALID = -1;

    /**
     * validate timestamp
     *
     * @param ts timestamp to be validated
     * @return true if valid; false otherwise
     */
    public static boolean isValidTs(String ts) {

        boolean tsIsNumeric = isNumeric(ts);
        long tsLong = TS_INVALID;
        if (tsIsNumeric) {
            try {
                tsLong = Long.parseLong(ts);
            } catch (NumberFormatException e) {
                LOG.info("parsing failed, message: " + e.getMessage() + ", trace: " + Arrays.toString(e.getStackTrace()));
                return false;
            }
        }
        if (tsLong >= TS_MIN) {
            return true;
        }
        LOG.info("ts: " + ts + " is invalid");
        return false;
    }
}
