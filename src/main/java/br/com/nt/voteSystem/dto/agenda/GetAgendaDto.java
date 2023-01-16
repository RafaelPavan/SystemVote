package br.com.nt.voteSystem.dto.agenda;

import br.com.nt.voteSystem.model.agenda.AgendaModel;

public class GetAgendaDto {
    private Long id;
    private String description;

    public GetAgendaDto() {
    }

    public GetAgendaDto(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static GetAgendaDto transform(AgendaModel model){
        return new GetAgendaDto(model.getId(), model.getDescription());
    }
}
