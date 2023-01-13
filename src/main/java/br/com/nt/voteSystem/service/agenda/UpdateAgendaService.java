package br.com.nt.voteSystem.service.agenda;

import br.com.nt.voteSystem.base.ErrorDto;
import br.com.nt.voteSystem.builder.BaseDtoErrorBuilder;
import br.com.nt.voteSystem.builder.BaseDtoSuccessBuilder;
import br.com.nt.voteSystem.dto.agenda.AgendaDto;
import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.repository.agenda.AgendaRepository;
import br.com.nt.voteSystem.validator.agenda.SaveAgendaValidator;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateAgendaService {

    private final AgendaRepository agendaRepository;

    public UpdateAgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }


    @Transactional
    public ResponseEntity execute(Long id, AgendaDto dto) {
        List<ErrorDto> errors = SaveAgendaValidator.execute(dto);
        if (!errors.isEmpty()){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.BAD_REQUEST);
            builder.addErrors(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builder.get());
        }

        if(!agendaRepository.existsById(id)){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError("id", "Pauta não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(builder.get());
        }

        AgendaModel model = AgendaDto.transform(dto);
        model.setId(agendaRepository.findById(id).get().getId());
        agendaRepository.save(model);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseDtoSuccessBuilder<>("Pauta atualizada com sucesso",
                        HttpStatus.OK).get());
    }
}
