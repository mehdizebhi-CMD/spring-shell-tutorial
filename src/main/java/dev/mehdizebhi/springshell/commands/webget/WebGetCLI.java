package dev.mehdizebhi.springshell.commands.webget;

import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Slf4j
@ShellComponent
public class WebGetCLI {
    // ----------------------------------------------------
    // Command Line Interface Methods
    // ----------------------------------------------------

    @ShellMethod(key = {"wget"}, value = "Return the source content of a URL.")
    public String webGetSource(
            @ShellOption String url
    ) {
        return getSourceOfUrlAsString(url);
    }

    @ShellMethod(key = {"wsave"}, value = "Save the source content of a URL to the file.")
    public String webSaveSource() {
        return null;
    }

    // ----------------------------------------------------
    // Private Helper
    // ----------------------------------------------------

    private String getSourceOfUrlAsString(String urlAsString) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlAsString);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    sb.append(inputLine);
                }
            }
        } catch (IOException ex) {
            log.error("There was a problem receiving the data");
            sb.append(ex.getMessage());
        }
        return sb.toString();
    }
}
