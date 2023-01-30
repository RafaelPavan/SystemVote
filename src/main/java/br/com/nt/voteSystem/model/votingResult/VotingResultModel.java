package br.com.nt.voteSystem.model.votingResult;

import br.com.nt.voteSystem.model.agenda.AgendaModel;
import javax.persistence.*;

@Entity
@Table(name = "votingResult")
public class VotingResultModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "fk_agenda", referencedColumnName = "id")
    private AgendaModel agendaId;

    private Long votesQuantity ;

    private Integer prosVotes ;

    private Integer consVotes ;

    @Enumerated(EnumType.STRING)
    private FinalResult finalResult;

    public VotingResultModel() {
    }

    public VotingResultModel(Long id, AgendaModel agendaId, Long votesQuantity, Integer prosVotes,
                             Integer consVotes, FinalResult finalResult) {
        this.id = id;
        this.agendaId = agendaId;
        this.votesQuantity = votesQuantity;
        this.prosVotes = prosVotes;
        this.consVotes = consVotes;
        this.finalResult = finalResult;
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

    public Long getVotesQuantity() {
        return votesQuantity;
    }

    public void setVotesQuantity(Long votesQuantity) {
        this.votesQuantity = votesQuantity;
    }

    public Integer getProsVotes() {
        return prosVotes;
    }

    public void setProsVotes(Integer prosVotes) {
        this.prosVotes = prosVotes;
    }

    public Integer getConsVotes() {
        return consVotes;
    }

    public void setConsVotes(Integer consVotes) {
        this.consVotes = consVotes;
    }

    public FinalResult getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(FinalResult finalResult) {
        this.finalResult = finalResult;
    }
}
