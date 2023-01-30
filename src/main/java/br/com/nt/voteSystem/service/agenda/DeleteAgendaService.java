package br.com.nt.voteSystem.service.agenda;

import br.com.nt.voteSystem.builder.BaseDtoErrorBuilder;
import br.com.nt.voteSystem.builder.BaseDtoSuccessBuilder;
import br.com.nt.voteSystem.repository.agenda.AgendaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DeleteAgendaService {

    private final AgendaRepository agendaRepository;

    public DeleteAgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Transactional
    public ResponseEntity execute(Long id) {

        if (!agendaRepository.existsById(id)){
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError("id", "Pauta não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(builder.get());
        }

        agendaRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseDtoSuccessBuilder<>("Pauta excluída com sucesso",
                        HttpStatus.OK).get());
    }
}
