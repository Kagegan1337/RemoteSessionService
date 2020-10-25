package de.kagegan.remotesessionservice.entities.client;


import de.kagegan.remotesessionservice.entities.AbstractEntity;
import de.kagegan.remotesessionservice.entities.accessinformation.AccessInformation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity()
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "client_id")
    private Long id;

    @Version
    private Integer version;

    @NotNull
    @Size(min = 2, max = 32)
    private String clientName;

    @OneToMany(mappedBy = "client",cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<AccessInformation> accessInformation;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Set<AccessInformation> getAccessInformation() {
        return accessInformation;
    }

    public void setAccessInformation(Set<AccessInformation> accessInformation) {
        this.accessInformation = accessInformation;
    }
}
