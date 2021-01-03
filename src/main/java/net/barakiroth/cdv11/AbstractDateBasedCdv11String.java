package net.barakiroth.cdv11;

import net.barakiroth.cdv11.exceptions.Cdv11StringFormatException;
import net.barakiroth.cdv11.exceptions.DateBasedCdv11StringFormatException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://www.skatteetaten.no/en/person/foreign/norwegian-identification-number/d-number/
 * https://www.skatteetaten.no/en/person/national-registry/birth-and-name-selection/children-born-in-norway/national-id-number/
 */
abstract class AbstractDateBasedCdv11String {

    private static final Map<IntSpan, Map<IntSpan, Integer>> centuryOn2YearSpanAndCounterSpan;
    static {
        final Map<IntSpan, Map<IntSpan, Integer>> centuryOn2YearSpanAndCounterSpanTemp = new HashMap<>();

        centuryOn2YearSpanAndCounterSpanTemp.put(IntSpan.of(0, 39), new HashMap<>() {{
            put(IntSpan.of(0, 499), 1900);
            put(IntSpan.of(500, 999), 2000);
        }});
        centuryOn2YearSpanAndCounterSpanTemp.put(IntSpan.of(40, 53), new HashMap<>() {{
            put(IntSpan.of(0, 499), 1900);
            put(IntSpan.of(900, 999), 1900);
        }});
        centuryOn2YearSpanAndCounterSpanTemp.put(IntSpan.of(54, 99), new HashMap<>() {{
            put(IntSpan.of(0, 499), 1900);
            put(IntSpan.of(500, 749), 1800);
            put(IntSpan.of(900, 999), 1900);
        }});
        centuryOn2YearSpanAndCounterSpan = Collections.unmodifiableMap(centuryOn2YearSpanAndCounterSpanTemp);
    }

    private static final Map<IntSpan, Set<IntSpan>> counterSpansOnYearSpan;
    static {
        final Map<IntSpan, Set<IntSpan>> counterSpansOnYearSpanTemp = new HashMap<>();
        counterSpansOnYearSpanTemp
                .put(
                        IntSpan.of(1854, 1899),
                        new HashSet<>() {{
                            add(IntSpan.of(500, 749));
                        }}
                );
        counterSpansOnYearSpanTemp
                .put(
                        IntSpan.of(1900, 1939),
                        new HashSet<>() {{
                            add(IntSpan.of(0, 499));
                        }}
                );
        counterSpansOnYearSpanTemp
                .put(
                        IntSpan.of(1940, 1999),
                        new HashSet<>() {{
                            add(IntSpan.of(0, 499));
                            add(IntSpan.of(900, 999));
                        }}
                );
        counterSpansOnYearSpanTemp
                .put(
                        IntSpan.of(2000, 2039),
                        new HashSet<>() {{
                            add(IntSpan.of(500, 999));
                        }}
                );

        counterSpansOnYearSpan = Collections.unmodifiableMap(counterSpansOnYearSpanTemp);
    }

    private final Cdv11String cdv11String;

    protected AbstractDateBasedCdv11String(
            final String dateBasedCdv11String,
            final int dayDelta,
            final int monthDelta) throws Cdv11StringFormatException, DateBasedCdv11StringFormatException {

        final Cdv11String cdv11String = new Cdv11String(dateBasedCdv11String);

        final int lastTwoDigitsOfYear = Integer.parseInt(dateBasedCdv11String.substring(4, 6));
        final int counter             = Integer.parseInt(dateBasedCdv11String.substring(6, 9));

        final Integer century = AbstractDateBasedCdv11String.calculateCenturyBasedOn(lastTwoDigitsOfYear, counter);
        if (century == null) {
            throw new DateBasedCdv11StringFormatException("The counter " + counter + " cannot be combined with a year ending with " + lastTwoDigitsOfYear);
        } else {
            final int day   = Integer.parseInt(dateBasedCdv11String.substring(0, 2)) - dayDelta;
            final int month = Integer.parseInt(dateBasedCdv11String.substring(2, 4)) - monthDelta;
            final int year = century + lastTwoDigitsOfYear;
            try {
                LocalDate.of(year, month, day);
            } catch (Throwable e) {
                throw new DateBasedCdv11StringFormatException("The combination of the counter " + counter + " and the lastTwoDigitsOfYear " + lastTwoDigitsOfYear + " with day " + day + " and month " + month + " gives a calculated year of " + year + " which altogether would bring about an invalid date from: " + dateBasedCdv11String, e);
            }
            this.cdv11String = cdv11String;
        }
    }

    protected AbstractDateBasedCdv11String(
            final LocalDate localDate,
            final int counter,
            final int dayDelta,
            final int monthDelta) {
        this.cdv11String = null;
    }

    /**
     * Calculate the century from the last
     * two digits of the year of birth (fødselsdato)
     * and the day counter (the first three digits of the personnummer)
     *
     * @param twoLastDigitsOfYear
     * @param counter
     * @return <code>null</code> if no such exists, otherwise the centyry
     */
    static Integer calculateCenturyBasedOn(
            final int twoLastDigitsOfYear,
            final int counter) {

        return
            AbstractDateBasedCdv11String.centuryOn2YearSpanAndCounterSpan
                .getOrDefault(
                    AbstractDateBasedCdv11String.centuryOn2YearSpanAndCounterSpan
                        .keySet()
                        .stream()
                        .filter(intSpan -> intSpan.contains(twoLastDigitsOfYear))
                        .findFirst()
                        .orElse(IntSpan.EMPTY),
                    new HashMap<>()
                )
                .get(
                    AbstractDateBasedCdv11String.centuryOn2YearSpanAndCounterSpan
                        .getOrDefault(
                            AbstractDateBasedCdv11String.centuryOn2YearSpanAndCounterSpan
                                .keySet()
                                .stream()
                                .filter(intSpan -> intSpan.contains(twoLastDigitsOfYear))
                                .findFirst()
                                .orElse(IntSpan.EMPTY),
                            new HashMap<>()
                        )
                        .keySet()
                        .stream()
                        .filter((intSpan) -> intSpan.contains(counter))
                        .findFirst()
                        .orElse(IntSpan.EMPTY)
                );
    }

    public String getValue() {
        return this.cdv11String.getValue();
    }
}
