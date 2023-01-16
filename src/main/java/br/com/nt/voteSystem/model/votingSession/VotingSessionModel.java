package br.com.nt.voteSystem.model.votingSession;

import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "votingSession")
public class VotingSessionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "FkAssociate", referencedColumnName = "id")
    private AssociateModel associateModel;

    @OneToOne
    @JoinColumn(name = "FkAgenda", referencedColumnName = "id")
    private AgendaModel agendaModel;

    private Boolean vote;

    private LocalDate votingOpening;

    public VotingSessionModel() {
    }

    public VotingSessionModel(Long id, AssociateModel associateModel, AgendaModel agendaModel,
                              Boolean vote, LocalDate votingOpening) {
        this.id = id;
        this.associateModel = associateModel;
        this.agendaModel = agendaModel;
        this.vote = vote;
        this.votingOpening = votingOpening;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AssociateModel getAssociateModel() {
        return associateModel;
    }

    public void setAssociateModel(AssociateModel associateModel) {
        this.associateModel = associateModel;
    }

    public AgendaModel getAgendaModel() {
        return agendaModel;
    }

    public void setAgendaModel(AgendaModel agendaModel) {
        this.agendaModel = agendaModel;
    }

    public Boolean getVote() {
        return vote;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }

    public LocalDate getVotingOpening() {
        return votingOpening;
    }

    public void setVotingOpening(LocalDate votingOpening) {
        this.votingOpening = votingOpening;
    }
}
