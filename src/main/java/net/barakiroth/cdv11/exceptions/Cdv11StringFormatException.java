package net.barakiroth.cdv11;

public class CannotCreateCdv11Exception extends Exception {

    public CannotCreateCdv11Exception(final String msg) {
        super(msg);
    }

    public CannotCreateCdv11Exception(final Throwable e) {
        super(e);
    }
}