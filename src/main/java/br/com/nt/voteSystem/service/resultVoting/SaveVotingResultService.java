package br.com.nt.voteSystem.service.resultVoting;

import br.com.nt.voteSystem.builder.BaseDtoErrorBuilder;
import br.com.nt.voteSystem.builder.BaseDtoSuccessBuilder;
import br.com.nt.voteSystem.dto.resultVoting.SaveVotingResultDto;
import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.votingResult.FinalResult;
import br.com.nt.voteSystem.model.votingResult.VotingResultModel;
import br.com.nt.voteSystem.model.votingSession.VotingSessionModel;
import br.com.nt.voteSystem.repository.agenda.AgendaRepository;
import br.com.nt.voteSystem.repository.resultVoting.VotingResultRepository;
import br.com.nt.voteSystem.repository.votingSession.VotingSessionRepository;
import br.com.nt.voteSystem.service.votingSession.VoteEnum;
import br.com.nt.voteSystem.service.votingSession.VotingStatus;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SaveVotingResultService {

    private final AgendaRepository agendaRepository;
    private final VotingSessionRepository votingSessionRepository;
    private final VotingResultRepository votingResultRepository;

    public SaveVotingResultService(AgendaRepository agendaRepository,
                                   VotingSessionRepository votingSessionRepository,
                                   VotingResultRepository votingResultRepository) {
        this.agendaRepository = agendaRepository;
        this.votingSessionRepository = votingSessionRepository;
        this.votingResultRepository = votingResultRepository;
    }

    @Transactional
    public ResponseEntity execute(Long id) {

        if (!agendaRepository.existsById(id)){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError("agendaId", "Pauta não encontrada.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(builder.get());
        }


        AgendaModel agendaModel = agendaRepository.getReferenceById(id);
        List<VotingSessionModel> allVotes = votingSessionRepository.findAll();
        List<VotingSessionModel> votes = new ArrayList<>();

        if(votingResultRepository.existsAgendaByAgendaId(agendaModel)){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.CONFLICT);
            builder.addError("agendaId", "Esta pauta já está com a votação encerrada.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(builder.get());
        }
        for(VotingSessionModel voting : allVotes){
            if (Objects.equals(voting.getAgendaId().getId(), agendaModel.getId())) {
                votes.add(voting);
            }
        }

        List<Object> prosVotes = new ArrayList<>(
                votes.stream()
                        .filter(v -> v.getAgendaId().getId().equals(id))
                        .filter(p -> p.getVote().equals(VoteEnum.SIM))
                        .toList()
        );

        List<Object> consVotes = new ArrayList<>(
                votes.stream()
                        .filter(v -> v.getAgendaId().getId().equals(id))
                        .filter(p -> p.getVote().equals(VoteEnum.NAO))
                        .toList()
        );


        VotingResultModel model = new VotingResultModel();
        model.setAgendaId(agendaModel);
        model.setProsVotes(prosVotes.size());
        model.setConsVotes(consVotes.size());
        model.setVotesQuantity((long) votes.size());
        if (prosVotes.size() > consVotes.size()){
            model.setFinalResult(FinalResult.APROVADA);
        }else model.setFinalResult(FinalResult.REPROVADA);


        votingResultRepository.save(model);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseDtoSuccessBuilder<>(model.getFinalResult(),
                HttpStatus.OK).get());
    }
}
