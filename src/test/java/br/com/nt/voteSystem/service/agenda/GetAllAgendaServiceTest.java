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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetAllAgendaServiceTest {

    @InjectMocks
    GetAllAgendaService getAllAgendaService;

    @Mock
    AgendaRepository agendaRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.getAllAgendaService = new GetAllAgendaService(agendaRepository);
    }

    @Test
    void shouldGetAllAgendas(){

        List<AgendaModel> list = new ArrayList<>();
        AgendaModel model = new AgendaModel(1L, "Some Agenda");
        list.add(model);

        Mockito.when(agendaRepository.findAllByOrderByIdAsc()).thenReturn(list);

        GetAllAgendaService service = new GetAllAgendaService(agendaRepository);
        ResponseEntity result = service.execute();
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    void shouldGetAnEmptyList(){
        List<AgendaModel> list = new ArrayList<>();

        Mockito.when(agendaRepository.findAllByOrderByIdAsc()).thenReturn(list);

        GetAllAgendaService service = new GetAllAgendaService(agendaRepository);
        ResponseEntity result = service.execute();
        assertEquals(result.getStatusCode().value(), 200);
    }
}