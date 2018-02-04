package com.herokuapp.mivoto.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    @NotBlank
    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 30)
    protected String name;

    protected AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Integer id){
        super(id);
    }

    protected AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
