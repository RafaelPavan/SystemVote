package br.com.nt.voteSystem.service.associate;

import br.com.nt.voteSystem.dto.associate.SaveAssociateDto;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import br.com.nt.voteSystem.repository.associate.AssociateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class SaveAssociateServiceTest {

    @InjectMocks
    SaveAssociateService saveAssociateService;

    @Mock
    AssociateRepository associateRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.saveAssociateService = new SaveAssociateService(associateRepository);
    }

    @Test
    void shouldSaveAnAssociate(){
        SaveAssociateDto dto = new SaveAssociateDto("Rafael", "Pavan", "87463605906");

        Mockito.when(associateRepository.existsByCpf(dto.getCpf())).thenReturn(false);
        Mockito.when(associateRepository.save(Mockito.any(AssociateModel.class))).thenReturn(new AssociateModel());

        ResponseEntity result = saveAssociateService.execute(dto);
        assertEquals(result.getStatusCode().value(), 201);
    }

    @Test
    void shouldNotSaveAnAssociateIfCpfAlreadyExists(){

        SaveAssociateDto dto = new SaveAssociateDto("Rafael", "Pavan", "87463605906");

        Mockito.when(associateRepository.existsByCpf(dto.getCpf())).thenReturn(true);

        ResponseEntity result = saveAssociateService.execute(dto);
        assertEquals(result.getStatusCode().value(), 409);
    }
}