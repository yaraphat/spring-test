package info.idb.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    @Column(name = "course_name")
    private String name;
    
}
