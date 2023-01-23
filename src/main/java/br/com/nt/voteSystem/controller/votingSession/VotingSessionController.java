package br.com.nt.voteSystem.controller.votingSession;

import br.com.nt.voteSystem.dto.votingSession.VotingSessionDto;
import br.com.nt.voteSystem.model.vote.VotingStatus;
import br.com.nt.voteSystem.service.votingSession.VotingSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votingSession")
public class VotingSessionController {
    private final VotingSessionService votingSessionService;

    public VotingSessionController(VotingSessionService votingSessionService) {
        this.votingSessionService = votingSessionService;
    }

    @PostMapping
    public ResponseEntity execute (@RequestParam(required = false) Long timeVoting,
                                   @RequestBody VotingSessionDto dto){
        return votingSessionService.execute(timeVoting, dto);
    }
}
