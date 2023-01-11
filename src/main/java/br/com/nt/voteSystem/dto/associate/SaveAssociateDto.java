package br.com.nt.voteSystem.dto.associate;

import br.com.nt.voteSystem.model.associate.AssociateModel;

public class SaveAssociateDto {

    private String name;
    private String lastName;
    private String cpf;

    public SaveAssociateDto() {
    }

    public SaveAssociateDto(String name, String lastName, String cpf) {
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
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

    public static AssociateModel trasnform(SaveAssociateDto dto){
        AssociateModel model = new AssociateModel();
        model.setName(dto.getName());
        model.setLastName(dto.getLastName());
        model.setCpf(dto.getCpf());

        return model;
    }
}
