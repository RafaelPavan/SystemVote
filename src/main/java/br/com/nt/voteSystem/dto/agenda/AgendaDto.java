package br.com.nt.voteSystem.dto.agenda;

import br.com.nt.voteSystem.model.agenda.AgendaModel;

public class AgendaDto {

    private String description;

    public AgendaDto() {
    }

    public AgendaDto(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static AgendaModel transform(AgendaDto dto){
        AgendaModel model = new AgendaModel();
        model.setDescription(dto.getDescription());
        return model;
    }

}
