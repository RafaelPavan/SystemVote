package br.com.nt.voteSystem.model.associate;

import jakarta.persistence.*;

@Entity
@Table(name = "Associate")
public class AssociateModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "cpf", length = 11)
    private String cpf;

    public AssociateModel() {
    }

    public AssociateModel(Long id, String name, String lastName, String cpf) {
        Id = id;
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
