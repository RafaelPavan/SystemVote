package br.com.nt.voteSystem.validator;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;

import java.util.List;

public class CpfValidator {
    public static boolean validateCpf(String cpf){
        CPFValidator cpfValidator = new CPFValidator();
        List<ValidationMessage> errors = cpfValidator.invalidMessagesFor(cpf);
        return errors.isEmpty();
    }
}
