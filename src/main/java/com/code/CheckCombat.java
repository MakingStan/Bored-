package com.code;

import net.runelite.api.Client;

import javax.inject.Inject;
import java.util.Objects;

public class CheckCombat {
    @Inject
    Client client;

    public boolean checkCombat() {
        int cb = Objects.requireNonNull(client.getLocalPlayer()).
                getCombatLevel();
        System.out.println(cb);

        if(cb > 90) {
            return true;
        }  else {
            return false;
        }
    }
}

