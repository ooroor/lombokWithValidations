package net.barakiroth.cdv11;

import java.util.Objects;

public class IntSpan {
    public static final IntSpan EMPTY = new IntSpan();
    final int lo;
    final int hi;
    private IntSpan() {
        this(0, -1);
    }
    private IntSpan(final int lo, final int hi) {
        this.lo = lo;
        this.hi = hi;
    }
    public static IntSpan of(final int lo, final int hi) {
        return lo > hi ? IntSpan.EMPTY : new IntSpan(lo, hi);
    }
    public boolean contains(final int elm) {
        return elm >= lo && elm <= hi;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final IntSpan range = (IntSpan) o;
        return lo == range.lo && hi == range.hi;
    }
    @Override
    public int hashCode() {
        return Objects.hash(lo, hi);
    }
}