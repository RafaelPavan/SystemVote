package br.com.nt.voteSystem.repository.resultVoting;

import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.votingResult.VotingResultModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingResultRepository extends JpaRepository<VotingResultModel, Long> {

    boolean existsAgendaByAgendaId(AgendaModel agendaModel);
}
