package de.kagean.remotesessionservice.frontend.client;

import de.kagean.remotesessionservice.frontend.service.communicationservice.CommunicationService;
import de.kagean.remotesessionservice.frontend.service.configuration.CmdConfiguration;
import de.kagegan.remotesessionservice.entities.accessinformation.AccessInformation;
import de.kagegan.remotesessionservice.entities.client.Client;

import java.io.IOException;

public class CmdFrontend implements Frontend {

    private CmdConfiguration cmdConfiguration;
    private CommunicationService communicationService;

    public CmdFrontend() {
    }

    @Override
    public void init() {
        initConfiguration();
        initCommunicationService();
        try {
            for (Client c : communicationService.getAllClients()) {
                System.out.println(c.getClientName());
                for (AccessInformation i : c.getAccessInformation()) {
                    System.out.println("\t" + i.getCreationTime());
                    System.out.println("\t" + i.getExpirationTime());
                    System.out.println("\t" + i.getIpAddress());
                    System.out.println("\t" + i.getIpType());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initCommunicationService() {
        communicationService = new CommunicationService(cmdConfiguration);
    }

    private void initConfiguration() {
        cmdConfiguration = new CmdConfiguration();
        cmdConfiguration.load();
    }
}
