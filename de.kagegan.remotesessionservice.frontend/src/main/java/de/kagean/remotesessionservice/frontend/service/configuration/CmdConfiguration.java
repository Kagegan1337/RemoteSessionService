package de.kagean.remotesessionservice.frontend.service.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CmdConfiguration implements FrontEndConfiguration,BackendConfiguration{

    private Properties props;

    private String backendUrl;
    private String authKey;

    public CmdConfiguration() {
        try {
            initProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initProperties() throws IOException {
        File f = new File("workflow.ini");
        props = new Properties();
        if(!f.exists()) {
            throw new FileNotFoundException();
        }
        try (InputStream inputStream = new FileInputStream(f)) {
            props.load(inputStream);
        }

    }

    /**
     * Used to all properties from property var.
     */
    public void load() {
        backendUrl = props.getProperty("backendUrl");

    }

    @Override
    public String getBackEndUrl() {
        return ConfigurationFormatter.getString(backendUrl,"");
    }

    @Override
    public String getAuthKey() {
        return ConfigurationFormatter.getString(authKey,"");
    }
}
