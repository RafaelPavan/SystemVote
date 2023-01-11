package br.com.nt.voteSystem.validator.associate;

import br.com.nt.voteSystem.base.ErrorDto;
import br.com.nt.voteSystem.dto.associate.SaveAssociateDto;
import br.com.nt.voteSystem.validator.CpfValidator;

import java.util.ArrayList;
import java.util.List;

public class SaveAssociateValidator {
    public static List<ErrorDto> execute(SaveAssociateDto dto){
        List<ErrorDto> erros = new ArrayList<>();

        if(dto.getName() == null || dto.getName().trim().isBlank()){
            erros.add(new ErrorDto("name", "Campo Obrigatório"));
        }

        if(dto.getLastName() == null || dto.getLastName().trim().isBlank()){
            erros.add(new ErrorDto("lastName", "Campo Obrigatório"));
        }

        if (dto.getCpf() == null || dto.getCpf().trim().isBlank()){
            erros.add(new ErrorDto("cpf", "Campo Obrigatório"));
        } else if (!CpfValidator.validateCpf(dto.getCpf())){
            erros.add(new ErrorDto("cpf", "CPF inválido."));
        }

        return erros;
    }

}
