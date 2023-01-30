package br.com.nt.voteSystem.model.agenda;

import javax.persistence.*;

@Entity
@Table(name = "agenda")
public class AgendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public AgendaModel() {
    }

    public AgendaModel(Long id) {
        this.id = id;
    }

    public AgendaModel(Long id, String description) {
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

    @Override
    public String toString() {
        return "AgendaId=" + id + ", description='" + description + '\'' ;
    }
}
