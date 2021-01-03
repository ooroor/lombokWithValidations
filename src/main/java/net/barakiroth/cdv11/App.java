package net.barakiroth.cdv11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        final App app = new App();
        app.run();
    }

    private void run() {
        
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(-1, -1));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(-1, 0));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(-1, 500));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(-1, 999));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(-1, 1000));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(100, -1));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(100, 0));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(100, 500));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(100, 999));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(100, 1000));

        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(0, 0));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(0, 250));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(0, 499));

        log.debug("expected: {}, actual: {}", 2000, AbstractDateBasedCdv11String.calculateCenturyBasedOn(0, 500));
        log.debug("expected: {}, actual: {}", 2000, AbstractDateBasedCdv11String.calculateCenturyBasedOn(0, 750));
        log.debug("expected: {}, actual: {}", 2000, AbstractDateBasedCdv11String.calculateCenturyBasedOn(0, 999));

        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(20, 0));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(20, 250));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(20, 499));

        log.debug("expected: {}, actual: {}", 2000, AbstractDateBasedCdv11String.calculateCenturyBasedOn(20, 500));
        log.debug("expected: {}, actual: {}", 2000, AbstractDateBasedCdv11String.calculateCenturyBasedOn(20, 750));
        log.debug("expected: {}, actual: {}", 2000, AbstractDateBasedCdv11String.calculateCenturyBasedOn(20, 999));

        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(39, 0));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(39, 250));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(39, 499));

        log.debug("expected: {}, actual: {}", 2000, AbstractDateBasedCdv11String.calculateCenturyBasedOn(39, 500));
        log.debug("expected: {}, actual: {}", 2000, AbstractDateBasedCdv11String.calculateCenturyBasedOn(39, 750));
        log.debug("expected: {}, actual: {}", 2000, AbstractDateBasedCdv11String.calculateCenturyBasedOn(39, 999));

        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(40, 0));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(40, 250));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(40, 499));

        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(40, 500));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(40, 700));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(40, 899));

        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(40, 900));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(40, 950));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(40, 999));

        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(46, 0));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(46, 250));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(46, 499));

        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(46, 500));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(46, 700));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(46, 899));

        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(46, 900));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(46, 950));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(46, 999));

        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(53, 0));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(53, 250));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(53, 499));

        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(53, 500));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(53, 700));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(53, 899));

        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(53, 900));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(53, 950));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(53, 999));

        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(54, 0));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(54, 250));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(54, 499));

        log.debug("expected: {}, actual: {}", 1800, AbstractDateBasedCdv11String.calculateCenturyBasedOn(54, 500));
        log.debug("expected: {}, actual: {}", 1800, AbstractDateBasedCdv11String.calculateCenturyBasedOn(54, 625));
        log.debug("expected: {}, actual: {}", 1800, AbstractDateBasedCdv11String.calculateCenturyBasedOn(54, 749));

        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(54, 750));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(54, 825));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(54, 899));

        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(54, 900));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(54, 950));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(54, 999));

        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(77, 0));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(77, 250));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(77, 499));

        log.debug("expected: {}, actual: {}", 1800, AbstractDateBasedCdv11String.calculateCenturyBasedOn(77, 500));
        log.debug("expected: {}, actual: {}", 1800, AbstractDateBasedCdv11String.calculateCenturyBasedOn(77, 625));
        log.debug("expected: {}, actual: {}", 1800, AbstractDateBasedCdv11String.calculateCenturyBasedOn(77, 749));

        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(77, 750));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(77, 825));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(77, 899));

        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(77, 900));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(77, 500));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(77, 999));

        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(99, 0));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(99, 250));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(99, 499));

        log.debug("expected: {}, actual: {}", 1800, AbstractDateBasedCdv11String.calculateCenturyBasedOn(99, 500));
        log.debug("expected: {}, actual: {}", 1800, AbstractDateBasedCdv11String.calculateCenturyBasedOn(99, 625));
        log.debug("expected: {}, actual: {}", 1800, AbstractDateBasedCdv11String.calculateCenturyBasedOn(99, 749));

        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(99, 750));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(99, 825));
        log.debug("expected: {}, actual: {}", null, AbstractDateBasedCdv11String.calculateCenturyBasedOn(99, 899));

        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(99, 900));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(99, 950));
        log.debug("expected: {}, actual: {}", 1900, AbstractDateBasedCdv11String.calculateCenturyBasedOn(99, 999));
    }
}