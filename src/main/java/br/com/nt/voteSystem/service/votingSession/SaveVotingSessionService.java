package br.com.nt.voteSystem.service.votingSession;

import br.com.nt.voteSystem.base.ErrorDto;
import br.com.nt.voteSystem.builder.BaseDtoErrorBuilder;
import br.com.nt.voteSystem.builder.BaseDtoSuccessBuilder;
import br.com.nt.voteSystem.dto.associate.SaveAssociateDto;
import br.com.nt.voteSystem.dto.votingSession.SaveVotingSessionDto;
import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import br.com.nt.voteSystem.model.votingSession.VotingSessionModel;
import br.com.nt.voteSystem.repository.agenda.AgendaRepository;
import br.com.nt.voteSystem.repository.associate.AssociateRepository;
import br.com.nt.voteSystem.repository.votingSession.VotingSessionRepository;
import br.com.nt.voteSystem.validator.votingSession.SaveVotingSessionValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SaveVotingSessionService {

    private final AssociateRepository associateRepository;
    private final AgendaRepository agendaRepository;
    private final VotingSessionRepository votingSessionRepository;

    public SaveVotingSessionService(AssociateRepository associateRepository,
                                    AgendaRepository agendaRepository,
                                    VotingSessionRepository votingSessionRepository) {
        this.associateRepository = associateRepository;
        this.agendaRepository = agendaRepository;
        this.votingSessionRepository = votingSessionRepository;
    }

    @Transactional
    public ResponseEntity execute(SaveVotingSessionDto dto) {

        List<ErrorDto> errors = SaveVotingSessionValidator.execute(dto);

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

        if(votingSessionRepository.existsVotingSessionModelByAssociateIdAndAgendaId(associateModel, agendaModel)){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.CONFLICT);
            builder.addError(null, "Associado já votou na pauta");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(builder.get());
        }




        VotingSessionModel model = SaveVotingSessionDto.transform(dto);
        model.setVote(VoteEnum.valueOf(dto.getVote().toString().toUpperCase()));
        votingSessionRepository.save(model);
//        System.out.println(model.getId());


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseDtoSuccessBuilder<>("Voto salvo com sucesso",
                HttpStatus.CREATED).get());
    }
}
