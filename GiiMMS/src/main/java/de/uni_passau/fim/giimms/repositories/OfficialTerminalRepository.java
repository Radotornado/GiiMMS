package de.uni_passau.fim.giimms.repositories;

import de.uni_passau.fim.giimms.model.OfficialTerminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficialTerminalRepository extends
        JpaRepository<OfficialTerminal, Long> {
}
