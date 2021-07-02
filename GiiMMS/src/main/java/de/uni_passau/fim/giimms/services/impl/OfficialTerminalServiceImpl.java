package de.uni_passau.fim.giimms.services.impl;

import de.uni_passau.fim.giimms.model.Coordinates;
import de.uni_passau.fim.giimms.model.OfficialTerminal;
import de.uni_passau.fim.giimms.repositories.OfficialTerminalRepository;
import de.uni_passau.fim.giimms.services.OfficialTerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficialTerminalServiceImpl implements OfficialTerminalService {

    @Autowired
    OfficialTerminalRepository officialTerminalRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(OfficialTerminal terminal) {
        officialTerminalRepository.save(terminal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(OfficialTerminal terminal) {
        officialTerminalRepository.delete(terminal);
    }

    @Override
    public boolean isOfficialTerminal(Coordinates coordinates) {
        boolean isOfficial = false;
        List<OfficialTerminal> terminalList =
                officialTerminalRepository.findAll();
        for(int i=0; i < terminalList.size(); i++){
            if(terminalList.get(i).getCoordinates().toString() == coordinates.toString()){
                isOfficial = true;
                break;
            }
        }
        return false;
    }
}
