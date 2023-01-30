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

import static org.junit.jupiter.api.Assertions.*;

class SaveAgendaServiceTest {

    @InjectMocks
    private SaveAgendaService saveAgendaService;

    @Mock
    AgendaRepository agendaRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.saveAgendaService = new SaveAgendaService(agendaRepository);
    }

    @Test
    void shouldSaveAnAgenda(){
        AgendaDto dto = new AgendaDto("Some agenda subjects");

        Mockito.when(agendaRepository.save(Mockito.any(AgendaModel.class))).thenReturn(new AgendaModel());

        SaveAgendaService service = new SaveAgendaService(agendaRepository);
        ResponseEntity result = service.execute(dto);
        assertEquals(result.getStatusCode().value(), 201);
    }

    @Test
    void shouldNotSaveAnAgenda(){
        AgendaDto dto = new AgendaDto();

        Mockito.when(agendaRepository.save(Mockito.any(AgendaModel.class))).thenReturn(new AgendaModel());

        SaveAgendaService service = new SaveAgendaService(agendaRepository);
        ResponseEntity result = service.execute(dto);
        assertEquals(result.getStatusCode().value(), 400);
    }
}