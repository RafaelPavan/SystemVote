package br.com.nt.voteSystem.service.votingSession;

import br.com.nt.voteSystem.base.ErrorDto;
import br.com.nt.voteSystem.builder.BaseDtoErrorBuilder;
import br.com.nt.voteSystem.builder.BaseDtoSuccessBuilder;
import br.com.nt.voteSystem.dto.votingSession.VotingSessionDto;
import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.vote.VotingStatus;
import br.com.nt.voteSystem.model.votingSession.VotingSessionModel;
import br.com.nt.voteSystem.repository.agenda.AgendaRepository;
import br.com.nt.voteSystem.repository.votingSession.VotingSessionRepository;
import br.com.nt.voteSystem.validator.votingSession.VotingSessionValidador;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class VotingSessionService {

    private final VotingSessionRepository votingSessionRepository;
    private final AgendaRepository agendaRepository;

    public VotingSessionService(VotingSessionRepository votingSessionRepository, AgendaRepository agendaRepository) {
        this.votingSessionRepository = votingSessionRepository;
        this.agendaRepository = agendaRepository;
    }

    public ResponseEntity execute(Long timeVoting, VotingSessionDto dto) {
        List<ErrorDto> errors = VotingSessionValidador.execute(dto);


        if (!agendaRepository.existsById(dto.getAgendaId().getId())){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError("agendaId", "Pauta não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(builder.get());
        }

        if (votingSessionRepository.existsByAgendaId(dto.getAgendaId())){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.CONFLICT);
            builder.addError("agendaId", "Pauta já encerrou votação");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(builder.get());
        }

        VotingSessionModel model = VotingSessionDto.trasform(dto);
        model.setTimeVotingOpening(LocalTime.now());
        if (!(timeVoting == null)){
            model.setTimeVoting(timeVoting);
            model.setTimeVotingClosing(LocalTime.now().plusMinutes(timeVoting));
        } else if (timeVoting == null) {
            model.setTimeVoting(1L);
            model.setTimeVotingClosing(LocalTime.now().plusMinutes(1L));
        }

        AgendaModel agendaModel = agendaRepository.getReferenceById(dto.getAgendaId().getId());
        sessionTime(model);

        votingSessionRepository.save(model);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseDtoSuccessBuilder<>("Votação aberta para pauta: " +
                        agendaModel.getDescription(), HttpStatus.OK).get());
    }

    private void sessionTime(VotingSessionModel votingSessionModel){
        Timer timer = new Timer();
        if (!LocalTime.now().equals(votingSessionModel.getTimeVotingClosing()) ||
                LocalTime.now().equals(votingSessionModel.getTimeVotingClosing())){
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (LocalTime.now().isAfter(votingSessionModel.getTimeVotingClosing())){
                        votingSessionModel.setVotingStatus(VotingStatus.FECHADA);
                        timer.cancel();
                        votingSessionRepository.save(votingSessionModel);
                    }else {
                        timer.cancel();
                        sessionTime(votingSessionModel);
                    }
                }
            },0, 6000);
        }
    }
}
