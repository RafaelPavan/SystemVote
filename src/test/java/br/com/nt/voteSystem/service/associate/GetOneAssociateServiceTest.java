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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GetOneAssociateServiceTest {
    @InjectMocks
    GetOneAssociateService getOneAssociateService;

    @Mock
    AssociateRepository associateRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.getOneAssociateService = new GetOneAssociateService(associateRepository);
    }

    @Test
    void shouldGetOneAssociate(){
        Long id = 1L;

        Mockito.when(associateRepository.existsById(id)).thenReturn(true);
        Mockito.when(associateRepository.findById(id)).thenReturn(Optional.of(new AssociateModel()));

        ResponseEntity result = getOneAssociateService.execute(id);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    void shouldNotGetOneAssociateIfNotExists(){
        Long id = 1L;

        Mockito.when(associateRepository.existsById(id)).thenReturn(false);

        ResponseEntity result = getOneAssociateService.execute(id);
        assertEquals(result.getStatusCode().value(), 404);
    }

}