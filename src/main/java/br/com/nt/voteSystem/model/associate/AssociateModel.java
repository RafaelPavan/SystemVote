package br.com.nt.voteSystem.model.associate;


import javax.persistence.*;

@Entity
@Table(name = "associate")
public class AssociateModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(length = 11)
    private String cpf;

    public AssociateModel() {
    }

    public AssociateModel(Long id) {
        this.id = id;
    }

    public AssociateModel(Long id, String name, String lastName, String cpf) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

