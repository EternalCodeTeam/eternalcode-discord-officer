package com.eternalcode.discordapp.util;

public final class Preconditions {

    private Preconditions() {
        throw new UnsupportedOperationException("Cannot create instance of class Preconditions");
    }

    public static void notNull(Object object, String name) {
        if (object == null) {
            throw new IllegalArgumentException(name + " cannot be null");
        }
    }

    public static void notEmpty(String owner, String name) {
        if (owner.isEmpty()) {
            throw new IllegalArgumentException(name + " cannot be empty");
        }
    }

}
