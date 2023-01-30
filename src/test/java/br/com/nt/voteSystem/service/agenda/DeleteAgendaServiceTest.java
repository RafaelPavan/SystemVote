package br.com.nt.voteSystem.service.agenda;

import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.repository.agenda.AgendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class DeleteAgendaServiceTest {

    @InjectMocks
    private DeleteAgendaService deleteAgendaService;

    @Mock
    AgendaRepository agendaRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.deleteAgendaService = new DeleteAgendaService(agendaRepository);
    }

    @Test
    void shouldDeleteAnAgenda(){
        AgendaModel model = new AgendaModel(1L);

        Mockito.when(agendaRepository.existsById(model.getId())).thenReturn(true);

        DeleteAgendaService service = new DeleteAgendaService(agendaRepository);
        ResponseEntity result = service.execute(model.getId());
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    void shouldNotDeleteAnAgendaIfNotExists(){
        AgendaModel model = new AgendaModel(1L);

        Mockito.when(agendaRepository.existsById(model.getId())).thenReturn(false);

        DeleteAgendaService service = new DeleteAgendaService(agendaRepository);
        ResponseEntity result = service.execute(model.getId());
        assertEquals(result.getStatusCode().value(), 404);
    }

}