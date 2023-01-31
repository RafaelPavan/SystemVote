package br.com.nt.voteSystem.service.associate;

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

class DeleteAssociateServiceTest {
    @InjectMocks
    DeleteAssociateService deleteAssociateService;

    @Mock
    AssociateRepository associateRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.deleteAssociateService = new DeleteAssociateService(associateRepository);
    }

    @Test
    void shouldDeleteAnAssociate(){

        Long id = 1L;

        Mockito.when(associateRepository.existsById(id)).thenReturn(true);
        Mockito.doNothing().when(associateRepository).deleteById(id);

        ResponseEntity result = deleteAssociateService.execute(id);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    void shouldNotDeleteAnAssociateIfNotExists(){
        Long id = 1L;

        Mockito.when(associateRepository.existsById(id)).thenReturn(false);

        ResponseEntity result = deleteAssociateService.execute(id);
        assertEquals(result.getStatusCode().value(), 404);
    }
}