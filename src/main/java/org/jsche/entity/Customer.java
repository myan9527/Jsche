package org.jsche.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jsche.common.validation.constraints.NotEmpty;
import org.jsche.common.validation.constraints.Range;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4283143640676498306L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String firstName;
    @Range(min = 2, max = 10)
    private String lastName;

    protected Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}
