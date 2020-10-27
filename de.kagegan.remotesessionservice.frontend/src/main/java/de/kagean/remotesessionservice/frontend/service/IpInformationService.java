package de.kagean.remotesessionservice.frontend.service;

import de.kagean.remotesessionservice.frontend.service.communicationservice.CommunicationService;

public class IpInformationService {

    private final CommunicationService communicationService;

    public IpInformationService(CommunicationService communicationService) {
        this.communicationService = communicationService;
        init();
    }

    private void init() {
        
    }
}
