package br.com.nt.voteSystem.controller.votingSession;

import br.com.nt.voteSystem.dto.votingSession.SaveVotingSessionDto;
import br.com.nt.voteSystem.service.votingSession.SaveVotingSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voting")
public class VotingSessionController {
    private final SaveVotingSessionService saveVotingSessionService;

    public VotingSessionController(SaveVotingSessionService saveVotingSessionService) {
        this.saveVotingSessionService = saveVotingSessionService;
    }

    @PostMapping
    public ResponseEntity saveVotingSession(@RequestBody SaveVotingSessionDto dto){
        return saveVotingSessionService.execute(dto);
    }

}
