package info.idb.crud.repository;

import info.idb.crud.entity.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByRoll(String roll);

    @Query("select s from Student s where s.name like %?1% or s.roll like %?1% or s.address like %?1% or s.grade like %?1%")
    Page<Student> search(String searchKey, Pageable pageable);
    
}
