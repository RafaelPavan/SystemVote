package br.com.nt.voteSystem.service.associate;

import br.com.nt.voteSystem.builder.BaseDtoSuccessBuilder;
import br.com.nt.voteSystem.dto.associate.GetAssociateDto;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import br.com.nt.voteSystem.repository.associate.AssociateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllAssociateService {

    private final AssociateRepository associateRepository;

    public GetAllAssociateService(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    public ResponseEntity execute() {
        List<AssociateModel> associateModels = associateRepository.findAll();
        List<GetAssociateDto> listDto = new ArrayList<>();

        if (associateModels.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new BaseDtoSuccessBuilder<>("Não há associados cadastrados no sistema",
                            HttpStatus.OK).get());
        }

        for (AssociateModel model: associateModels){
            GetAssociateDto dto = GetAssociateDto.transform(model);
            listDto.add(dto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoSuccessBuilder<>(listDto,
                HttpStatus.OK).get());
    }
}
