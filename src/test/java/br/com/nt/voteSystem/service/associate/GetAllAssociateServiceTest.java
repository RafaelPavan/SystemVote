package br.com.nt.voteSystem.service.associate;

import br.com.nt.voteSystem.dto.associate.GetAssociateDto;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import br.com.nt.voteSystem.repository.associate.AssociateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetAllAssociateServiceTest {
    @InjectMocks
    GetAllAssociateService getAllAssociateService;

    @Mock
    AssociateRepository associateRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.getAllAssociateService = new GetAllAssociateService(associateRepository);
    }

    @Test
    void shouldGetAllAssociates(){
        List<AssociateModel> modelList = new ArrayList<>();
        AssociateModel model = new AssociateModel(1L, "Rafael", "Pavan", "87463605906");
        modelList.add(model);

        Mockito.when(associateRepository.findAll()).thenReturn(modelList);

        ResponseEntity result = getAllAssociateService.execute();
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    void shouldGetAnEmptyList(){
        List<AssociateModel> modelList = new ArrayList<>();

        Mockito.when(associateRepository.findAll()).thenReturn(modelList);

        ResponseEntity result = getAllAssociateService.execute();
        assertEquals(result.getStatusCode().value(), 200);
    }

}