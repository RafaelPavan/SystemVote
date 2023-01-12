package br.com.nt.voteSystem.controller.associate;

import br.com.nt.voteSystem.dto.associate.SaveAssociateDto;
import br.com.nt.voteSystem.dto.associate.UpdateAssociateDto;
import br.com.nt.voteSystem.service.associate.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/associate")
public class AssociateController {

    private final SaveAssociateService saveAssociateService;
    private final GetOneAssociateService getOneAssociateService;
    private final GetAllAssociateService getAllAssociateService;
    private final UpdateAssociateService updateAssociateService;
    private final DeleteAssociateService deleteAssociateService;

    public AssociateController(SaveAssociateService saveAssociateService,
                               GetOneAssociateService getOneAssociateService,
                               GetAllAssociateService getAllAssociateService,
                               UpdateAssociateService updateAssociateService,
                               DeleteAssociateService deleteAssociateService) {
        this.saveAssociateService = saveAssociateService;
        this.getOneAssociateService = getOneAssociateService;
        this.getAllAssociateService = getAllAssociateService;
        this.updateAssociateService = updateAssociateService;
        this.deleteAssociateService = deleteAssociateService;
    }

    @PostMapping
    public ResponseEntity saveAssociate(@RequestBody SaveAssociateDto dto){
        return saveAssociateService.execute(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity findOneAssociate(@PathVariable Long id){
        return getOneAssociateService.execute(id);
    }

    @GetMapping
    public ResponseEntity findAllAssociates(){
        return getAllAssociateService.execute();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAssociate(@PathVariable Long id,
                                          @RequestBody  UpdateAssociateDto dto){
        return updateAssociateService.execute(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAssociate(@PathVariable Long id){
        return deleteAssociateService.execute(id);
    }
}
