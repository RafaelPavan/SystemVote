package br.com.nt.voteSystem.service.agenda;

import br.com.nt.voteSystem.builder.BaseDtoSuccessBuilder;
import br.com.nt.voteSystem.dto.agenda.GetAgendaDto;
import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.repository.agenda.AgendaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllAgendaService {

    private final AgendaRepository agendaRepository;

    public GetAllAgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public ResponseEntity execute() {

        List<AgendaModel> agendaModels = agendaRepository.findAllByOrderByIdAsc();
        List<GetAgendaDto> listDto = new ArrayList<>();

        if (agendaModels.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new BaseDtoSuccessBuilder<>("Não há pautas cadastradas",
                            HttpStatus.OK).get());
        }

        for (AgendaModel model : agendaModels){
            GetAgendaDto dto = GetAgendaDto.transform(model);
            listDto.add(dto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoSuccessBuilder<>(listDto,
                        HttpStatus.OK).get());
    }
}
