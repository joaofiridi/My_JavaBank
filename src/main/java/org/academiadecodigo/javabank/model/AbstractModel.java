package org.academiadecodigo.javabank.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * A generic model entity to be used as a base for concrete types of models
 */
@MappedSuperclass
public abstract class AbstractModel implements Model {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * @see Model#getId()
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * @see Model#setId(Integer)
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
