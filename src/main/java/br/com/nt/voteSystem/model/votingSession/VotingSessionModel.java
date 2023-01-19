package br.com.nt.voteSystem.model.votingSession;

import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.associate.AssociateModel;
import br.com.nt.voteSystem.service.votingSession.VoteEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.time.*;

@Entity
@Table(name = "votingSession")
public class VotingSessionModel {
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


    private LocalDate dateVotingOpening = LocalDate.now();

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime timeVotingOpening = LocalTime.now(ZoneId.of("America/Sao_Paulo"));

    public VotingSessionModel() {
    }

    public VotingSessionModel(Long id, AssociateModel associateId, AgendaModel agendaId,
                              VoteEnum vote, LocalDate dateVotingOpening,
                              LocalTime timeVotingOpening) {
        this.id = id;
        this.associateId = associateId;
        this.agendaId = agendaId;
        this.vote = vote;
        this.dateVotingOpening = dateVotingOpening;
        this.timeVotingOpening = timeVotingOpening;
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

    public LocalDate getDateVotingOpening() {
        return dateVotingOpening;
    }

    public void setDateVotingOpening(LocalDate dateVotingOpening) {
        this.dateVotingOpening = dateVotingOpening;
    }

    public LocalTime getTimeVotingOpening() {
        return timeVotingOpening;
    }

    public void setTimeVotingOpening(LocalTime timeVotingOpening) {
        this.timeVotingOpening = timeVotingOpening;
    }

    @Override
    public String toString() {
        return "VotingSessionId=" + id +
                ", " + associateId +
                ", " + agendaId +
                ", vote=" + vote +
                ", dateVotingOpening=" + dateVotingOpening +
                ", timeVotingOpening=" + timeVotingOpening ;
    }
}
