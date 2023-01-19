package br.com.nt.voteSystem.controller.resultVoting;

import br.com.nt.voteSystem.service.resultVoting.SaveVotingResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
