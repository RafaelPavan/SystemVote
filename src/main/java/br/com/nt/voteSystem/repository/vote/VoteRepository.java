package br.com.nt.voteSystem.repository.vote;

import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import br.com.nt.voteSystem.model.vote.VoteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<VoteModel, Long> {


    boolean existsVotingSessionModelByAssociateIdAndAgendaId(AssociateModel associateModel, AgendaModel agendaModel);
}
