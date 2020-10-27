package de.kagean.remotesessionservice.frontend.client;

import de.kagean.remotesessionservice.frontend.service.IpInformationService;
import de.kagean.remotesessionservice.frontend.service.communicationservice.CommunicationService;
import de.kagean.remotesessionservice.frontend.service.configuration.CmdConfiguration;

public class CmdFrontend implements Frontend {

    private CmdConfiguration cmdConfiguration;
    private CommunicationService communicationService;
    private IpInformationService ipInformationService;

    public CmdFrontend() {
    }

    @Override
    public void init() {
        initConfiguration();
        initCommunicationService();
        initIpService();
        initCommandLine();
    }

    private void initConfiguration() {
        cmdConfiguration = new CmdConfiguration();
        cmdConfiguration.load();
    }

    private void initCommunicationService() {
        communicationService = new CommunicationService(cmdConfiguration);
    }

    private void initIpService() {
        ipInformationService = new IpInformationService(communicationService);
    }

    private void initCommandLine() {

    }
}
