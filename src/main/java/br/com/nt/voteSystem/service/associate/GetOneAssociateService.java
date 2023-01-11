package br.com.nt.voteSystem.service.associate;

import br.com.nt.voteSystem.builder.BaseDtoErrorBuilder;
import br.com.nt.voteSystem.builder.BaseDtoSuccessBuilder;
import br.com.nt.voteSystem.dto.associate.GetAssociateDto;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import br.com.nt.voteSystem.repository.associate.AssociateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetOneAssociateService {
    private final AssociateRepository associateRepository;

    public GetOneAssociateService(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    public ResponseEntity execute(Long id) {
        if (!associateRepository.existsById(id)){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError(null, "Associado não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(builder.get());
        }

        AssociateModel model = associateRepository.findById(id).get();
        GetAssociateDto dto = GetAssociateDto.transform(model);

        return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoSuccessBuilder<>(dto,
                HttpStatus.OK).get());
    }
}
