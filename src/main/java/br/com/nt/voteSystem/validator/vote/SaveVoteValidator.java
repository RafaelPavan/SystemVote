package br.com.nt.voteSystem.validator.vote;

import br.com.nt.voteSystem.base.ErrorDto;
import br.com.nt.voteSystem.dto.vote.VoteDto;

import java.util.ArrayList;
import java.util.List;

public class SaveVoteValidator {

    public static List<ErrorDto> execute(VoteDto dto) {
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
