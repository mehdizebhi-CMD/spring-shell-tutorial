package dev.mehdizebhi.springshell.commands.webget;

import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.*;
import java.net.URL;

@Slf4j
@ShellComponent
public class WebGetCLI {
    // ----------------------------------------------------
    // Command Line Interface Methods
    // ----------------------------------------------------

    @ShellMethod(key = {"wget"}, value = "Return the source content of a URL.")
    public String webGetSource(
            @ShellOption(value = {"u", "url"}) String url
    ) {
        return getSourceOfUrlAsString(url);
    }

    @ShellMethod(key = {"wsave"}, value = "Save the source content of a URL to the file.")
    public String webSaveSource(
            @ShellOption(value = {"u", "url"}) String url,
            @ShellOption(value = {"f", "file"}) String file
    ) {
        String source = getSourceOfUrlAsString(url);
        try (PrintWriter writer = new PrintWriter(file, "UTF-8")){
            writer.write(source);
        } catch (FileNotFoundException ex){
            log.error("File \'{}\' is not found.", file);
        } catch (UnsupportedEncodingException e) {
            log.error("UTF-8 is not supported.");
        }
        return String.format("File Saved! You can see the result in %s.", file);
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
