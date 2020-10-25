package de.kagean.remotesessionservice.frontend;

import de.kagean.remotesessionservice.frontend.client.CmdFrontend;
import de.kagean.remotesessionservice.frontend.client.Frontend;

public class Bootstrap {

    public static void main(String[] args) {
        new CmdFrontend().init();
    }
}
