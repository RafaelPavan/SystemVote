package br.com.nt.voteSystem.dto.associate;

import br.com.nt.voteSystem.model.associate.AssociateModel;

public class GetAssociateDto {

    private Long Id;
    private String name;
    private String lastName;
    private String cpf;

    public GetAssociateDto() {
    }

    public GetAssociateDto(Long id, String name, String lastName, String cpf) {
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

   public static GetAssociateDto transform(AssociateModel model){
        return new GetAssociateDto(model.getId(), model.getName(), model.getLastName(),
                model.getCpf());
   }
}
