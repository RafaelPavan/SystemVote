package br.com.nt.voteSystem.controller.voteController;

import br.com.nt.voteSystem.dto.vote.VoteDto;
import br.com.nt.voteSystem.service.vote.SaveVoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoteController {
    private final SaveVoteService saveVoteService;

    public VoteController(SaveVoteService saveVoteService) {
        this.saveVoteService = saveVoteService;
    }

    @PostMapping
    public ResponseEntity saveVotingSession(@RequestParam(required = false) Long timeVoting,
                                            @RequestBody VoteDto dto){
        return saveVoteService.execute(dto, timeVoting);
    }

}
