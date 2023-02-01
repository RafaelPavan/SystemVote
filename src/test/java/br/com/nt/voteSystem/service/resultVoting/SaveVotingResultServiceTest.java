package br.com.nt.voteSystem.service.resultVoting;

import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.vote.VoteModel;
import br.com.nt.voteSystem.model.votingResult.VotingResultModel;
import br.com.nt.voteSystem.repository.agenda.AgendaRepository;
import br.com.nt.voteSystem.repository.resultVoting.VotingResultRepository;
import br.com.nt.voteSystem.repository.vote.VoteRepository;
import br.com.nt.voteSystem.repository.votingSession.VotingSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaveVotingResultServiceTest {

    @InjectMocks
    SaveVotingResultService saveVotingResultService;

    @Mock
    AgendaRepository agendaRepository;

    @Mock
    VoteRepository voteRepository;

    @Mock
    VotingResultRepository votingResultRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.saveVotingResultService = new SaveVotingResultService(agendaRepository,
                voteRepository, votingResultRepository);
    }

    @Test
    void shouldSaveResultVoting(){

        Long id = 1L;
        AgendaModel model = new AgendaModel(id);
        List<VoteModel> list = new ArrayList<>();

        Mockito.when(agendaRepository.existsById(id)).thenReturn(true);
        Mockito.when(agendaRepository.getReferenceById(id)).thenReturn(model);
        Mockito.when(voteRepository.findAll()).thenReturn(list);
        Mockito.when(votingResultRepository.existsAgendaByAgendaId(model)).thenReturn(false);
        Mockito.when(votingResultRepository.save(Mockito.any(VotingResultModel.class))).thenReturn(new VotingResultModel());

        ResponseEntity result = saveVotingResultService.execute(id);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    void shouldNotSaveResultVotingIfAgendaIdNotExists(){
        Long id = 1L;

        Mockito.when(agendaRepository.existsById(id)).thenReturn(false);

        ResponseEntity result = saveVotingResultService.execute(id);
        assertEquals(result.getStatusCode().value(), 404);
    }

    @Test
    void shouldNotSaveResultVotingIfVotingWasClosed(){
        Long id = 1L;
        AgendaModel model = new AgendaModel(id);
        List<VoteModel> list = new ArrayList<>();

        Mockito.when(agendaRepository.existsById(id)).thenReturn(true);
        Mockito.when(agendaRepository.getReferenceById(id)).thenReturn(model);
        Mockito.when(voteRepository.findAll()).thenReturn(list);
        Mockito.when(votingResultRepository.existsAgendaByAgendaId(model)).thenReturn(true);

        ResponseEntity result = saveVotingResultService.execute(id);
        assertEquals(result.getStatusCode().value(), 409);
    }

}