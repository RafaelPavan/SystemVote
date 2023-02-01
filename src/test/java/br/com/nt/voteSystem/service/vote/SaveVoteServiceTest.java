package br.com.nt.voteSystem.service.vote;

import br.com.nt.voteSystem.dto.vote.VoteDto;
import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import br.com.nt.voteSystem.model.vote.VoteEnum;
import br.com.nt.voteSystem.model.vote.VoteModel;
import br.com.nt.voteSystem.model.vote.VotingStatus;
import br.com.nt.voteSystem.model.votingSession.VotingSessionModel;
import br.com.nt.voteSystem.repository.agenda.AgendaRepository;
import br.com.nt.voteSystem.repository.associate.AssociateRepository;
import br.com.nt.voteSystem.repository.resultVoting.VotingResultRepository;
import br.com.nt.voteSystem.repository.vote.VoteRepository;
import br.com.nt.voteSystem.repository.votingSession.VotingSessionRepository;
import br.com.nt.voteSystem.service.resultVoting.SaveVotingResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class SaveVoteServiceTest {

    @InjectMocks
    SaveVoteService saveVoteService;

    @Mock
    AssociateRepository associateRepository;

    @Mock
    AgendaRepository agendaRepository;

    @Mock
    VoteRepository voteRepository;

    @Mock
    VotingSessionRepository votingSessionRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.saveVoteService = new SaveVoteService(associateRepository, agendaRepository,
                voteRepository, votingSessionRepository);
    }

    @Test
    void shouldSaveVote(){

        VoteDto dto = new VoteDto(1L, 1L, VoteEnum.SIM);
        AssociateModel associateModel = new AssociateModel();
        AgendaModel agendaModel = new AgendaModel(1L);
        VoteModel voteModel = new VoteModel();


        VotingSessionModel votingSessionModel = new VotingSessionModel(1L, new AgendaModel(1L),
                LocalDate.now(), LocalTime.now(), LocalTime.now().plusMinutes(1),  1L,
                VotingStatus.EM_ANDAMENTO);


        Mockito.when(associateRepository.existsById(dto.getAssociateId())).thenReturn(true);
        Mockito.when(agendaRepository.existsById(dto.getAgendaId())).thenReturn(true);
        Mockito.when(associateRepository.getReferenceById(dto.getAgendaId())).thenReturn(associateModel);
        Mockito.when(agendaRepository.getReferenceById(dto.getAgendaId())).thenReturn(agendaModel);
        Mockito.when(voteRepository.existsVotingSessionModelByAssociateIdAndAgendaId(associateModel, agendaModel)).thenReturn(false);
        Mockito.when(votingSessionRepository.findByAgendaId(agendaModel)).thenReturn(votingSessionModel);
        Mockito.when(voteRepository.save(Mockito.any(VoteModel.class))).thenReturn(voteModel);

        ResponseEntity result = saveVoteService.execute(dto, 1L);
        assertEquals(result.getStatusCode().value(), 201);
    }


    @Test
    void shouldNotSaveVoteIfAssociateNotExists(){
        VoteDto dto = new VoteDto(1L, 1L, VoteEnum.SIM);

        Mockito.when(associateRepository.existsById(dto.getAssociateId())).thenReturn(false);

        ResponseEntity result = saveVoteService.execute(dto, 1L);
        assertEquals(result.getStatusCode().value(), 404);
    }

    @Test
    void shouldNotSaveVoteIfAgendaNotExists(){
        VoteDto dto = new VoteDto(1L, 1L, VoteEnum.SIM);

        Mockito.when(associateRepository.existsById(dto.getAssociateId())).thenReturn(true);
        Mockito.when(agendaRepository.existsById(dto.getAgendaId())).thenReturn(false);

        ResponseEntity result = saveVoteService.execute(dto, 1L);
        assertEquals(result.getStatusCode().value(), 404);
    }

    @Test
    void shouldNotSaveVoteIfAssociateAlreadyVotedOnTheAgenda(){
        VoteDto dto = new VoteDto(1L, 1L, VoteEnum.SIM);
        AssociateModel associateModel = new AssociateModel();
        AgendaModel agendaModel = new AgendaModel(1L);

        Mockito.when(associateRepository.existsById(dto.getAssociateId())).thenReturn(true);
        Mockito.when(agendaRepository.existsById(dto.getAgendaId())).thenReturn(true);
        Mockito.when(associateRepository.getReferenceById(dto.getAgendaId())).thenReturn(associateModel);
        Mockito.when(agendaRepository.getReferenceById(dto.getAgendaId())).thenReturn(agendaModel);
        Mockito.when(voteRepository.existsVotingSessionModelByAssociateIdAndAgendaId(associateModel, agendaModel)).thenReturn(true);

        ResponseEntity result = saveVoteService.execute(dto, 1L);
        assertEquals(result.getStatusCode().value(), 409);
    }

}