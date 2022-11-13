package com.mybanksystem.util;

public class IdGenerator {
    private static long accountIdSeed = 10000;
    private static long bankIdSeed = 100;

    public static Long generateAccountId() {
        return accountIdSeed++;
    }

    public static Long generateBankId() {
        return bankIdSeed++;
    }
}
