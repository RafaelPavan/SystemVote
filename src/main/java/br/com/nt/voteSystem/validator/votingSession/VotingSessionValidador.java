package br.com.nt.voteSystem.validator.votingSession;

import br.com.nt.voteSystem.base.ErrorDto;
import br.com.nt.voteSystem.dto.votingSession.VotingSessionDto;

import java.util.ArrayList;
import java.util.List;

public class VotingSessionValidador {
    public static List<ErrorDto> execute(VotingSessionDto dto) {
        List<ErrorDto> error = new ArrayList<>();

        if (dto.getAgendaId() == null){
            error.add(new ErrorDto("agendaId", "Campo Obrigatório"));
        }

        return error;
    }
}
