package de.kagegan.remotesessionservice.entities.accessinformation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.kagegan.remotesessionservice.entities.client.Client;
import org.joda.time.DateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class AccessInformation {

    @Id
    @GeneratedValue
    @Column(name = "access_information_id")
    private Long id;

    @Version
    private Integer version;

    @NotNull
    @Size(min = 15, max = 40)
    private String ipAddress;

    @Size(min = 4, max = 4)
    private String ipType;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Client client;

    private DateTime creationTime;

    private DateTime expirationTime;

    @PrePersist
    private void createTime() {
        creationTime = DateTime.now();
        expirationTime = creationTime.plusHours(12);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpType() {
        return ipType;
    }

    public void setIpType(String ipType) {
        this.ipType = ipType;
    }

    public DateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(DateTime creationTime) {
        this.creationTime = creationTime;
    }

    public DateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(DateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
