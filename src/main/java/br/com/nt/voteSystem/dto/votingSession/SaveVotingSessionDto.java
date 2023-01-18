package br.com.nt.voteSystem.dto.votingSession;

import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import br.com.nt.voteSystem.model.votingSession.VotingSessionModel;
import br.com.nt.voteSystem.service.votingSession.VoteEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Arrays;

public class SaveVotingSessionDto {

    private Long associateId;

    private Long agendaId;

    private VoteEnum vote;

    public SaveVotingSessionDto() {
    }

    public SaveVotingSessionDto(Long associateId, Long agendaId, VoteEnum vote) {
        this.associateId = associateId;
        this.agendaId = agendaId;
        this.vote = vote;
    }

    public Long getAssociateId() {
        return associateId;
    }

    public void setAssociateId(Long associateId) {
        this.associateId = associateId;
    }

    public Long getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Long agendaId) {
        this.agendaId = agendaId;
    }

    public VoteEnum getVote() {
        return vote;
    }

    public void setVote(VoteEnum vote) {
        this.vote = vote;
    }

    public static VotingSessionModel transform (SaveVotingSessionDto dto){
        VotingSessionModel model = new VotingSessionModel();
        model.setAssociateId(new AssociateModel(dto.getAssociateId()));
        model.setAgendaId(new AgendaModel(dto.getAgendaId()));
        model.setVote(dto.getVote());
        return model;
    }
}
