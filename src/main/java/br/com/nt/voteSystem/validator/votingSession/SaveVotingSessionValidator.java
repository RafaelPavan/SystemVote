package br.com.nt.voteSystem.validator.votingSession;

import br.com.nt.voteSystem.base.ErrorDto;
import br.com.nt.voteSystem.dto.votingSession.SaveVotingSessionDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SaveVotingSessionValidator {

    public static List<ErrorDto> execute(SaveVotingSessionDto dto) {
        List<ErrorDto> error = new ArrayList<>();

        if(dto.getAssociateId() == null){
            error.add(new ErrorDto("associateModel_id", "Campo Obrigatório"));
        }
        if (dto.getAgendaId() == null){
            error.add(new ErrorDto("agendaModel_id", "Campo Obrigatório"));
        }
        if(dto.getVote() == null || dto.getVote().toString().isBlank()){
            error.add(new ErrorDto("vote", "Campo Obrigatório"));
        }


        return error;
    }
}
