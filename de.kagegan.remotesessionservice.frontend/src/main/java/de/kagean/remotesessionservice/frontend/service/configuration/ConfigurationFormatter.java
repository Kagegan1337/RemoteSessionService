package de.kagean.remotesessionservice.frontend.service.configuration;

public class ConfigurationFormatter {

    static String getString(String input, String defaultValue) {
        String res = input;
        if (input == null) {
            res = defaultValue;
        } else if (input.isEmpty()) {
            res = defaultValue;
        }

        return res;
    }
}
