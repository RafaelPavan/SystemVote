package br.com.nt.voteSystem.model.vote;

import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.*;

@Entity
@Table(name = "votes")
public class VoteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "FkAssociate", referencedColumnName = "id")
    private AssociateModel associateId;

    @OneToOne
    @JoinColumn(name = "FkAgenda", referencedColumnName = "id")
    private AgendaModel agendaId;

    @Enumerated(EnumType.STRING)
    private VoteEnum vote;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime voteTime;



    public VoteModel() {
    }


    public VoteModel(Long id, AssociateModel associateId, AgendaModel agendaId,
                     VoteEnum vote, LocalTime voteTime) {
        this.id = id;
        this.associateId = associateId;
        this.agendaId = agendaId;
        this.vote = vote;
        this.voteTime = voteTime;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AssociateModel getAssociateId() {
        return associateId;
    }

    public void setAssociateId(AssociateModel associateId) {
        this.associateId = associateId;
    }

    public AgendaModel getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(AgendaModel agendaId) {
        this.agendaId = agendaId;
    }

    public VoteEnum getVote() {
        return vote;
    }

    public void setVote(VoteEnum vote) {
        this.vote = vote;

    }

    public LocalTime getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(LocalTime voteTime) {
        this.voteTime = voteTime;
    }

}
