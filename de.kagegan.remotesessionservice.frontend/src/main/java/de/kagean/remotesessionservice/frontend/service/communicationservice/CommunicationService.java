package de.kagean.remotesessionservice.frontend.service.communicationservice;

import com.google.gson.Gson;
import de.kagean.remotesessionservice.frontend.service.configuration.BackendConfiguration;
import de.kagegan.remotesessionservice.entities.client.Client;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class CommunicationService {

    private final BackendConfiguration backendConfiguration;
    private final CloseableHttpClient httpClient;

    public CommunicationService(BackendConfiguration backendConfiguration) {
        this.backendConfiguration = backendConfiguration;
        this.httpClient = HttpClients.createDefault();
    }

    /**
     * Register a client at backend
     * @param client - the client to register
     * @return true if registration was successful.
     * @throws IOException on error
     */
    public boolean registerClient(Client client) throws IOException {
        HttpPost post = new HttpPost(backendConfiguration.getBackEndUrl() + "client/save");
        HttpEntity stringEntity = new StringEntity(new Gson().toJson(client, Client.class), ContentType.APPLICATION_JSON);
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        return true;
    }

    /**
     * Fetch all clients from backend
     * @return A list of clients fetched from backend
     * @throws IOException on error
     */
    public List<Client> getAllClients() throws IOException {
        HttpGet get = new HttpGet(backendConfiguration.getBackEndUrl() + "/client/all");
        CloseableHttpResponse response = httpClient.execute(get);
        return Arrays.asList(new Gson().fromJson(new InputStreamReader(response.getEntity().getContent()),Client[].class));
    }

}
