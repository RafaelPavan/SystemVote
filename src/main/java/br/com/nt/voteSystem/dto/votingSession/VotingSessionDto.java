package br.com.nt.voteSystem.dto.votingSession;

import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.votingSession.VotingSessionModel;

public class VotingSessionDto {

    private AgendaModel agendaId;

    public VotingSessionDto() {
    }

    public VotingSessionDto(AgendaModel agendaId) {
        this.agendaId = agendaId;
    }

    public AgendaModel getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(AgendaModel agendaId) {
        this.agendaId = agendaId;
    }

    public static VotingSessionModel trasform (VotingSessionDto dto){
        VotingSessionModel model = new VotingSessionModel();
        model.setAgendaId(dto.getAgendaId());
        return model;
    }
}
