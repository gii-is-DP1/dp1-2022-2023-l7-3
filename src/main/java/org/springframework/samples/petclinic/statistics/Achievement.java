package org.springframework.samples.petclinic.statistics;

import javax.persistence.Entity;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Achievement extends NamedEntity {
    private String description;
    private String badgeImage;
    private double threshold;
}
