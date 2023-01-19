package br.com.nt.voteSystem.controller.resultVoting;

import br.com.nt.voteSystem.dto.resultVoting.SaveVotingResultDto;
import br.com.nt.voteSystem.service.resultVoting.SaveVotingResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/result")
public class resultVotingController {

    private final SaveVotingResultService saveVotingResultService;

    public resultVotingController(SaveVotingResultService saveVotingResultService) {
        this.saveVotingResultService = saveVotingResultService;
    }

    @PostMapping("/voting/{id}")
    public ResponseEntity findResultByAgenda(@PathVariable Long id){
       return saveVotingResultService.execute(id);
    }
}
