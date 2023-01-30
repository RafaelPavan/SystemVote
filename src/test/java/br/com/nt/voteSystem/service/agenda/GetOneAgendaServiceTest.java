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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GetOneAgendaServiceTest {
    @InjectMocks
    GetOneAgendaService getOneAgendaService;

    @Mock
    AgendaRepository agendaRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.getOneAgendaService = new GetOneAgendaService(agendaRepository);
    }

    @Test
    void shouldGetOneAgenda(){

        Long id = 1L;

        Mockito.when(agendaRepository.existsById(id)).thenReturn(true);
        Mockito.when(agendaRepository.findById(id)).thenReturn(Optional.of(new AgendaModel()));

        GetOneAgendaService service = new GetOneAgendaService(agendaRepository);
        ResponseEntity result = service.execute(id);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    void shouldNotGetOneAgendaIfNotExists(){
        Long id = 1L;

        Mockito.when(agendaRepository.existsById(id)).thenReturn(false);

        GetOneAgendaService service = new GetOneAgendaService(agendaRepository);
        ResponseEntity result = service.execute(id);
        assertEquals(result.getStatusCode().value(), 404);
    }
}