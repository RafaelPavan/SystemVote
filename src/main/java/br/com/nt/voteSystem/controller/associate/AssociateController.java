package br.com.nt.voteSystem.controller.associate;

import br.com.nt.voteSystem.dto.associate.SaveAssociateDto;
import br.com.nt.voteSystem.service.associate.AssociateService;
import br.com.nt.voteSystem.service.associate.GetAllAssociateService;
import br.com.nt.voteSystem.service.associate.GetOneAssociateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/associate")
public class AssociateController {

    private final AssociateService associateService;
    private final GetOneAssociateService getOneAssociateService;
    private final GetAllAssociateService getAllAssociateService;

    public AssociateController(AssociateService associateService,
                               GetOneAssociateService getOneAssociateService,
                               GetAllAssociateService getAllAssociateService) {
        this.associateService = associateService;
        this.getOneAssociateService = getOneAssociateService;
        this.getAllAssociateService = getAllAssociateService;
    }

    @PostMapping
    public ResponseEntity saveAssociate(@RequestBody SaveAssociateDto dto){
        return associateService.execute(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity findOneAssociate(@PathVariable Long id){
        return getOneAssociateService.execute(id);
    }

    @GetMapping
    public ResponseEntity findAllAssociates(){
        return getAllAssociateService.execute();
    }
}
