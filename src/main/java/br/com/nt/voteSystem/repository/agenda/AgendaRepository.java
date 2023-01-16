package br.com.nt.voteSystem.repository.agenda;

import br.com.nt.voteSystem.model.agenda.AgendaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendaRepository extends JpaRepository<AgendaModel, Long> {
    List<AgendaModel> findAllByOrderByIdAsc();
}
