package br.com.nt.voteSystem.service.agenda;

import br.com.nt.voteSystem.builder.BaseDtoErrorBuilder;
import br.com.nt.voteSystem.builder.BaseDtoSuccessBuilder;
import br.com.nt.voteSystem.dto.agenda.GetAgendaDto;
import br.com.nt.voteSystem.dto.associate.GetAssociateDto;
import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.repository.agenda.AgendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetOneAgendaService {

    private final AgendaRepository agendaRepository;

    public GetOneAgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }


    public ResponseEntity execute(Long id) {

        if (!agendaRepository.existsById(id)){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError("id", "Agenda não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(builder.get());
        }

        AgendaModel model = agendaRepository.findById(id).get();
        GetAgendaDto dto = GetAgendaDto.transform(model);

        return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoSuccessBuilder<>(dto,
                HttpStatus.OK).get());
    }
}
