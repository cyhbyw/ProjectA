package com.cyh._interface._default.method;

import java.util.function.Supplier;

/**
 * Created by yanhuche on 3/29/2017.
 */
public interface Defaulable {

    default String notRequired() {
        return "Default implementation";
    }
}


class DefaultableImpl implements Defaulable {
}


class OverridableImpl implements Defaulable {
    @Override
    public String notRequired() {
        return "Overridden implementation";
    }
}


interface DefaulableFactory {
    // Interfaces now allow static methods
    static Defaulable create(Supplier<Defaulable> supplier) {
        return supplier.get();
    }
}
