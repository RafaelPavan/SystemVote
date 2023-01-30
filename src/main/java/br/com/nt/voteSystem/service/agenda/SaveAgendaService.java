package br.com.nt.voteSystem.service.agenda;

import br.com.nt.voteSystem.base.ErrorDto;
import br.com.nt.voteSystem.builder.BaseDtoErrorBuilder;
import br.com.nt.voteSystem.builder.BaseDtoSuccessBuilder;
import br.com.nt.voteSystem.dto.agenda.AgendaDto;
import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.repository.agenda.AgendaRepository;
import br.com.nt.voteSystem.validator.agenda.SaveAgendaValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SaveAgendaService {

    private final AgendaRepository agendaRepository;

    public SaveAgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Transactional
    public ResponseEntity execute(AgendaDto dto) {
        List<ErrorDto> errors = SaveAgendaValidator.execute(dto);

        if(!errors.isEmpty()){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.BAD_REQUEST);
            builder.addErrors(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builder.get());
        }

        AgendaModel model = AgendaDto.transform(dto);
        agendaRepository.save(model);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseDtoSuccessBuilder<>("Pauta criada com suscesso, id: " + model.getId(),
                        HttpStatus.CREATED).get());
    }
}
