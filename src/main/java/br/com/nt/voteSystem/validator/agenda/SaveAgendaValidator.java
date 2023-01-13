package br.com.nt.voteSystem.validator.agenda;

import br.com.nt.voteSystem.base.ErrorDto;
import br.com.nt.voteSystem.dto.agenda.SaveAgendaDto;

import java.util.ArrayList;
import java.util.List;

public class SaveAgendaValidator {
    public static List<ErrorDto> execute(SaveAgendaDto dto) {
        List<ErrorDto> errors = new ArrayList<>();

        if (dto.getDescription() == null || dto.getDescription().isBlank()){
            errors.add(new ErrorDto("description", "Campo obrigatório."));
        }

        return errors;
    }
}
