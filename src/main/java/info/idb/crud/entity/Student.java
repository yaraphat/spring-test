package info.idb.crud.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student extends BaseEntity {
    @Column(name = "student_name")
    private String name;
    @Column(name = "roll", unique = true, nullable = false)
    private String roll;
    @Column(name = "address")
    private String address;
    @Column(name = "grade")
    private String grade;

    // nested form
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    // Dropdown multiple choice / Checkbox
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "student_courses", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

    // Dropdown single choice / Radio button
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "department_id")
    private Department dept;

    public Student() {
        this.dept = null;
    }

    public Student(String name, String roll, String address, String grade, User user, List<Course> courses, Department dept) {
        this.name = name;
        this.roll = roll;
        this.address = address;
        this.grade = grade;
        this.user = user;
        this.courses = courses;
        this.dept = dept;
    }

}
