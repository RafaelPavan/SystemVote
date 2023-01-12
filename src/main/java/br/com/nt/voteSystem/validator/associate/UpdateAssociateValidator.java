package br.com.nt.voteSystem.validator.associate;

import br.com.nt.voteSystem.base.ErrorDto;
import br.com.nt.voteSystem.dto.associate.UpdateAssociateDto;
import br.com.nt.voteSystem.validator.CpfValidator;

import java.util.ArrayList;
import java.util.List;

public class UpdateAssociateValidator {
    public static List<ErrorDto> execute(UpdateAssociateDto dto){
        List<ErrorDto> erros = new ArrayList<>();

        if(dto.getName() == null || dto.getName().trim().isBlank()){
            erros.add(new ErrorDto("name", "Campo Obrigatório"));
        }

        if(dto.getLastName() == null || dto.getLastName().trim().isBlank()){
            erros.add(new ErrorDto("lastName", "Campo Obrigatório"));
        }

        return erros;
    }

}
