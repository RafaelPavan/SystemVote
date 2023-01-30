package br.com.nt.voteSystem.service.associate;

import br.com.nt.voteSystem.base.ErrorDto;
import br.com.nt.voteSystem.builder.BaseDtoErrorBuilder;
import br.com.nt.voteSystem.builder.BaseDtoSuccessBuilder;
import br.com.nt.voteSystem.dto.associate.SaveAssociateDto;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import br.com.nt.voteSystem.repository.associate.AssociateRepository;
import br.com.nt.voteSystem.validator.associate.SaveAssociateValidator;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveAssociateService {

    private final AssociateRepository associateRepository;

    public SaveAssociateService(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    @Transactional
    public ResponseEntity execute(SaveAssociateDto dto) {
        List<ErrorDto> errors = SaveAssociateValidator.execute(dto);

        if(!errors.isEmpty()){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.BAD_REQUEST);
            builder.addErrors(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builder.get());
        }

        if(associateRepository.existsByCpf(dto.getCpf())){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.CONFLICT);
            builder.addError(null, "CPF já está cadastrado");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(builder.get());
        }

        AssociateModel model = SaveAssociateDto.trasnform(dto);
        associateRepository.save(model);


        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseDtoSuccessBuilder<>(
                "Associado cadastrado com suceeso, id: " + model.getId(),
                HttpStatus.CREATED).get());
    }
}
