package br.com.nt.voteSystem.controller.agenda;

import br.com.nt.voteSystem.dto.agenda.AgendaDto;
import br.com.nt.voteSystem.service.agenda.GetAllAgendaService;
import br.com.nt.voteSystem.service.agenda.GetOneAgendaService;
import br.com.nt.voteSystem.service.agenda.SaveAgendaService;
import br.com.nt.voteSystem.service.agenda.UpdateAgendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final SaveAgendaService saveAgendaService;
    private final UpdateAgendaService updateAgendaService;
    private final GetOneAgendaService getOneAgendaService;
    private final GetAllAgendaService getAllAgendaService;

    public AgendaController(SaveAgendaService saveAgendaService,
                            UpdateAgendaService updateAgendaService,
                            GetOneAgendaService getOneAgendaService,
                            GetAllAgendaService getAllAgendaService) {
        this.saveAgendaService = saveAgendaService;
        this.updateAgendaService = updateAgendaService;
        this.getOneAgendaService = getOneAgendaService;
        this.getAllAgendaService = getAllAgendaService;
    }

    @PostMapping
    public ResponseEntity saveAgenda(@RequestBody AgendaDto dto){
        return saveAgendaService.execute(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAgenda(@PathVariable Long id, @RequestBody AgendaDto dto){
        return updateAgendaService.execute(id, dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity findOneAgenda(@PathVariable Long id){
        return getOneAgendaService.execute(id);
    }

    @GetMapping
    public ResponseEntity findAllAgenda(){
        return getAllAgendaService.execute();
    }

}
