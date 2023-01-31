package br.com.nt.voteSystem.service.associate;

import br.com.nt.voteSystem.dto.associate.SaveAssociateDto;
import br.com.nt.voteSystem.dto.associate.UpdateAssociateDto;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import br.com.nt.voteSystem.repository.associate.AssociateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UpdateAssociateServiceTest {

    @InjectMocks
    UpdateAssociateService updateAssociateService;

    @Mock
    AssociateRepository associateRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.updateAssociateService = new UpdateAssociateService(associateRepository);
    }

    @Test
    void shouldUpdateAnAssociate(){
        Long id = 1L;
        UpdateAssociateDto dto = new UpdateAssociateDto("Rafael", "Pavan");

        Mockito.when(associateRepository.existsById(id)).thenReturn(true);
        Mockito.when(associateRepository.findById(id)).thenReturn(Optional.of(new AssociateModel()));
        Mockito.when(associateRepository.save(Mockito.any(AssociateModel.class))).thenReturn(new AssociateModel());

        ResponseEntity result = updateAssociateService.execute(id, dto);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    void shouldNotUpdateAnAssociateIfNotExists(){
        Long id = 1L;
        UpdateAssociateDto dto = new UpdateAssociateDto("Rafael", "Pavan");

        Mockito.when(associateRepository.existsById(id)).thenReturn(false);

        ResponseEntity result = updateAssociateService.execute(id, dto);
        assertEquals(result.getStatusCode().value(), 404);

    }
}