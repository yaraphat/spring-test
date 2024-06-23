package info.idb.crud.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department extends BaseEntity {
    @Column(name = "department_name", unique = true, nullable = false)
    private String name;

    @Column(name = "department_code", unique = true, nullable = false)
    private String code;

    @JsonManagedReference
    @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)    
    private List<Student> students;
}
