package br.com.nt.voteSystem.service.vote;

import br.com.nt.voteSystem.base.ErrorDto;
import br.com.nt.voteSystem.builder.BaseDtoErrorBuilder;
import br.com.nt.voteSystem.builder.BaseDtoSuccessBuilder;
import br.com.nt.voteSystem.dto.vote.VoteDto;
import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import br.com.nt.voteSystem.model.vote.VoteModel;
import br.com.nt.voteSystem.model.votingSession.VotingSessionModel;
import br.com.nt.voteSystem.repository.agenda.AgendaRepository;
import br.com.nt.voteSystem.repository.associate.AssociateRepository;
import br.com.nt.voteSystem.repository.vote.VoteRepository;
import br.com.nt.voteSystem.repository.votingSession.VotingSessionRepository;
import br.com.nt.voteSystem.validator.vote.SaveVoteValidator;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.List;

@Service
public class SaveVoteService {

    private final AssociateRepository associateRepository;
    private final AgendaRepository agendaRepository;
    private final VoteRepository voteRepository;
    private final VotingSessionRepository votingSessionRepository;

    public SaveVoteService(AssociateRepository associateRepository,
                           AgendaRepository agendaRepository,
                           VoteRepository voteRepository,
                           VotingSessionRepository votingSessionRepository) {
        this.associateRepository = associateRepository;
        this.agendaRepository = agendaRepository;
        this.voteRepository = voteRepository;
        this.votingSessionRepository = votingSessionRepository;
    }

    @Transactional
    public ResponseEntity execute(VoteDto dto, @RequestParam Long timeVoting) {

        List<ErrorDto> errors = SaveVoteValidator.execute(dto);

        if (!errors.isEmpty()){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.BAD_REQUEST);
            builder.addErrors(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builder.get());
        }

        if (!associateRepository.existsById(dto.getAssociateId())){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError("associateId", "Associado não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(builder.get());
        }

        if(!agendaRepository.existsById(dto.getAgendaId())){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError("agendaId", "Pauta não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(builder.get());
        }

        AssociateModel associateModel = associateRepository.getReferenceById(dto.getAssociateId());
        AgendaModel agendaModel = agendaRepository.getReferenceById(dto.getAgendaId());

        if(voteRepository.existsVotingSessionModelByAssociateIdAndAgendaId(associateModel, agendaModel)){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.CONFLICT);
            builder.addError(null, "Associado já votou na pauta");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(builder.get());
        }

        VotingSessionModel sessionModel = votingSessionRepository.findByAgendaId(agendaModel);
        if (sessionModel == null){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.CONFLICT);
            builder.addError(null, "Votação não iniciada.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(builder.get());

        }

        VoteModel model = VoteDto.transform(dto);
        model.setVoteTime(LocalTime.now());

        if (model.getVoteTime().isAfter(sessionModel.getTimeVotingClosing())){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.CONFLICT);
            builder.addError(null, "Pauta expirou tempo de votação");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(builder.get());
        }

        voteRepository.save(model);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseDtoSuccessBuilder<>("Voto salvo com sucesso",
                HttpStatus.CREATED).get());
    }
}
