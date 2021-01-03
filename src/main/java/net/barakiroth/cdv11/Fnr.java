package net.barakiroth.cdv11;

import net.barakiroth.cdv11.exceptions.Cdv11StringFormatException;
import net.barakiroth.cdv11.exceptions.DateBasedCdv11StringFormatException;

import java.time.LocalDate;

public class Fnr extends AbstractDateBasedCdv11String {

    private static final int DAY_DELTA = 0;
    private static final int MONTH_DELTA = 0;

    public Fnr(final String value) throws Cdv11StringFormatException, DateBasedCdv11StringFormatException {
        super(value, Fnr.DAY_DELTA, Fnr.MONTH_DELTA);
    }

    public Fnr(final LocalDate localDate, final int counter) {
        super(localDate, counter, Fnr.DAY_DELTA, Fnr.MONTH_DELTA);
    }
}