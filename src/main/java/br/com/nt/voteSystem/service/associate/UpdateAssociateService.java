package br.com.nt.voteSystem.service.associate;

import br.com.nt.voteSystem.base.ErrorDto;
import br.com.nt.voteSystem.builder.BaseDtoErrorBuilder;
import br.com.nt.voteSystem.builder.BaseDtoSuccessBuilder;
import br.com.nt.voteSystem.dto.associate.UpdateAssociateDto;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import br.com.nt.voteSystem.repository.associate.AssociateRepository;
import br.com.nt.voteSystem.validator.associate.UpdateAssociateValidator;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateAssociateService {

    private final AssociateRepository associateRepository;

    public UpdateAssociateService(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }


    @Transactional
    public ResponseEntity execute(Long id, UpdateAssociateDto dto) {

        List<ErrorDto> errors = UpdateAssociateValidator.execute(dto);

        if(!errors.isEmpty()){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.BAD_REQUEST);
            builder.addErrors(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builder.get());
        }

        if (!associateRepository.existsById(id)){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError(null, "Associado não encotrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(builder.get());
        }

        AssociateModel model = UpdateAssociateDto.trasnform(dto);
        model.setId(associateRepository.findById(id).get().getId());
        model.setCpf(associateRepository.findById(id).get().getCpf());
        associateRepository.save(model);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseDtoSuccessBuilder<>("Associado atualizado com sucesso!",
                        HttpStatus.OK).get());
    }
}
