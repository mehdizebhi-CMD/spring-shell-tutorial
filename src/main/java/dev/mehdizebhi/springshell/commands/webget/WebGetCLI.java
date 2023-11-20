package dev.mehdizebhi.springshell.commands.webget;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class WebGetCLI {

    // ----------------------------------------------------
    // Command Line Interface Methods
    // ----------------------------------------------------

    @ShellMethod(key = {"wget"}, value = "Return the source content of a URL.")
    public String webGetSource() {
        return null;
    }

    @ShellMethod(key = {"wsave"}, value = "Save the source content of a URL to the file.")
    public String webSaveSource() {
        return null;
    }

    // ----------------------------------------------------
    // Private Helper
    // ----------------------------------------------------
}
