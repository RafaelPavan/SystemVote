package br.com.nt.voteSystem.service.associate;

import br.com.nt.voteSystem.builder.BaseDtoErrorBuilder;
import br.com.nt.voteSystem.builder.BaseDtoSuccessBuilder;
import br.com.nt.voteSystem.repository.associate.AssociateRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteAssociateService {

    private final AssociateRepository associateRepository;

    public DeleteAssociateService(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    @Transactional
    public ResponseEntity execute(Long id) {

        if (!associateRepository.existsById(id)){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError(null, "Associado não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(builder.get());
        }

        associateRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseDtoSuccessBuilder<>("Associado excluído com sucesso",
                        HttpStatus.OK).get());

    }
}
