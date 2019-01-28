package com.sample.gauge;

/**
 * @author Sudarshan Hegde on 28/01/19
 */
public class UserFinder {

    private static UserFinder userFinder = UserFinder.getUserFinder();

    public static UserFinder getUserFinder() {
        synchronized (userFinder) {
            if (userFinder == null) {
                userFinder = new UserFinder();
                System.out.println("new object");
            }
            return userFinder;
        }
    }
}
