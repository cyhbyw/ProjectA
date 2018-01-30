package com.cyh._interface._default.method;

/**
 * Created by yanhuche on 3/29/2017.
 */
public class DefaulableMain {


    public static void main(String[] args) {
        Defaulable defaulable = DefaulableFactory.create(DefaultableImpl::new);
        System.out.println(defaulable.notRequired());

        defaulable = DefaulableFactory.create(OverridableImpl::new);
        System.out.println(defaulable.notRequired());
    }



}
