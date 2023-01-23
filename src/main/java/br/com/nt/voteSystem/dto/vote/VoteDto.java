package br.com.nt.voteSystem.dto.vote;

import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import br.com.nt.voteSystem.model.vote.VoteModel;
import br.com.nt.voteSystem.model.vote.VoteEnum;

public class VoteDto {

    private Long associateId;

    private Long agendaId;

    private VoteEnum vote;

    public VoteDto() {
    }

    public VoteDto(Long associateId, Long agendaId, VoteEnum vote) {
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

    public static VoteModel transform (VoteDto dto){
        VoteModel model = new VoteModel();
        model.setAssociateId(new AssociateModel(dto.getAssociateId()));
        model.setAgendaId(new AgendaModel(dto.getAgendaId()));
        model.setVote(dto.getVote());
        return model;
    }
}
