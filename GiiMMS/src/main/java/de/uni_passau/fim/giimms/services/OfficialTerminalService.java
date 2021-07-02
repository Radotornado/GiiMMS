package de.uni_passau.fim.giimms.services;

import de.uni_passau.fim.giimms.model.Coordinates;
import de.uni_passau.fim.giimms.model.OfficialTerminal;

import java.util.List;

public interface OfficialTerminalService {

    /**
     * Saves a Terminal as an Official one.
     * @param terminal
     */
    void save(OfficialTerminal terminal);

    /**
     * Deletes a Terminal as an Official one.
     * @param terminal
     */
    void delete(OfficialTerminal terminal);

    /**
     * Checks whether or not the Terminal is official or not.
     * @param coordinates
     * @return true if coordinates match a official Terminal, false if not
     */
    boolean isOfficialTerminal(Coordinates coordinates);

    List<OfficialTerminal> getAll();
}
