package br.com.nt.voteSystem.model.votingSession;

import br.com.nt.voteSystem.model.agenda.AgendaModel;
import br.com.nt.voteSystem.model.vote.VotingStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "votingSession")
public class VotingSessionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "FkAgenda", referencedColumnName = "id")
    private AgendaModel agendaId;

    private LocalDate dateVotingOpening = LocalDate.now();

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime timeVotingOpening ;

    private LocalTime timeVotingClosing;

    private Long timeVoting;

    @Enumerated(EnumType.STRING)
    private VotingStatus votingStatus = VotingStatus.EM_ANDAMENTO;

    public VotingSessionModel() {
    }

    public VotingSessionModel(Long id, AgendaModel agendaId, LocalDate dateVotingOpening,
                              LocalTime timeVotingOpening, LocalTime timeVotingClosing,
                              Long timeVoting, VotingStatus votingStatus) {
        this.id = id;
        this.agendaId = agendaId;
        this.dateVotingOpening = dateVotingOpening;
        this.timeVotingOpening = timeVotingOpening;
        this.timeVotingClosing = timeVotingClosing;
        this.timeVoting = timeVoting;
        this.votingStatus = votingStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AgendaModel getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(AgendaModel agendaId) {
        this.agendaId = agendaId;
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

    public LocalTime getTimeVotingClosing() {
        return timeVotingClosing;
    }

    public void setTimeVotingClosing(LocalTime timeVotingClosing) {
        this.timeVotingClosing = timeVotingClosing;
    }

    public Long getTimeVoting() {
        return timeVoting;
    }

    public void setTimeVoting(Long timeVoting) {
        this.timeVoting = timeVoting;
    }

    public VotingStatus getVotingStatus() {
        return votingStatus;
    }

    public void setVotingStatus(VotingStatus votingStatus) {
        this.votingStatus = votingStatus;
    }
}
