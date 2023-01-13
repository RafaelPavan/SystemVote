package br.com.nt.voteSystem.controller.agenda;

import br.com.nt.voteSystem.dto.agenda.SaveAgendaDto;
import br.com.nt.voteSystem.dto.associate.SaveAssociateDto;
import br.com.nt.voteSystem.service.agenda.SaveAgendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final SaveAgendaService saveAgendaService;

    public AgendaController(SaveAgendaService saveAgendaService) {
        this.saveAgendaService = saveAgendaService;
    }

    @PostMapping
    public ResponseEntity saveAgenda(@RequestBody SaveAgendaDto dto){
        return saveAgendaService.execute(dto);
    }
}
