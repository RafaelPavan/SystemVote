package br.com.nt.voteSystem.repository.votingSession;

import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.votingSession.VotingSessionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingSessionRepository extends JpaRepository<VotingSessionModel, Long> {

    VotingSessionModel findByAgendaId(AgendaModel agendaId);

    boolean existsByAgendaId(AgendaModel agendaId);
}
