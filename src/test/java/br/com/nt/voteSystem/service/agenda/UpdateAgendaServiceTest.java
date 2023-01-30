package br.com.nt.voteSystem.service.agenda;

import br.com.nt.voteSystem.dto.agenda.AgendaDto;
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

class UpdateAgendaServiceTest {

    @InjectMocks
    UpdateAgendaService updateAgendaService;

    @Mock
    AgendaRepository agendaRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.updateAgendaService = new UpdateAgendaService(agendaRepository);
    }

    @Test
    void shouldUpdateAnAgenda(){
        AgendaModel model = new AgendaModel(1L);
        AgendaDto dto = new AgendaDto("New agenda register");

        Mockito.when(agendaRepository.existsById(model.getId())).thenReturn(true);
        Mockito.when(agendaRepository.findById(model.getId())).thenReturn(Optional.of(model));
        Mockito.when(agendaRepository.save(Mockito.any(AgendaModel.class))).thenReturn(new AgendaModel());

        UpdateAgendaService service = new UpdateAgendaService(agendaRepository);
        ResponseEntity result = service.execute(model.getId(), dto);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    void shouldNotUpdateAnAgendaIfNotExists(){
        Long id = 1L;
        AgendaDto dto = new AgendaDto("New agenda register");

        Mockito.when(agendaRepository.existsById(id)).thenReturn(false);

        UpdateAgendaService service = new UpdateAgendaService(agendaRepository);
        ResponseEntity result = service.execute(id, dto);
        assertEquals(result.getStatusCode().value(), 404);
    }
}