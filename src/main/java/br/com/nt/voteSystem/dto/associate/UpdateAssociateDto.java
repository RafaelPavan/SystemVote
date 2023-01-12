package br.com.nt.voteSystem.dto.associate;

import br.com.nt.voteSystem.model.associate.AssociateModel;

public class UpdateAssociateDto {

    private String name;
    private String lastName;

    public UpdateAssociateDto() {
    }

    public UpdateAssociateDto(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
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


    public static AssociateModel trasnform(UpdateAssociateDto dto){
        AssociateModel model = new AssociateModel();
        model.setName(dto.getName());
        model.setLastName(dto.getLastName());
        return model;
    }
}
