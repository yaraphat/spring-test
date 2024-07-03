package info.idb.crud.service;

import info.idb.crud.constants.ResponseStatus;
import info.idb.crud.dto.Response;
import info.idb.crud.entity.Student;
import info.idb.crud.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final UserService userService;

    @Override
    public Response<?> persist(Student student) {
        if (student.hasId()) {
            student.setId(null);
        }
        if (studentRepository.existsByRoll(student.getRoll())) {
            return new Response<>(ResponseStatus.ERROR, "Student ID already exists");
        }
        Response<?> response = userService.persist(student.getUser());
        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {
            studentRepository.save(student);
            return new Response<>(ResponseStatus.SUCCESS, "Student saved successfully");
        } else {
            return response;
        }
    }

    @Override
    public Response<?> merge(Student student) {
        if (!student.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "ID required");
        }
        Student olStudent = studentRepository.findById(student.getId()).orElse(null);
        if (olStudent == null) {
            return new Response<>(ResponseStatus.ERROR, "Student not found");
        }
        if (!olStudent.getRoll().equals(student.getRoll())) {
            if (studentRepository.existsByRoll(student.getRoll())) {
                return new Response<>(ResponseStatus.ERROR, "Student ID already exists");
            }
        }
        Response<?> response = userService.merge(student.getUser());
        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {
            studentRepository.save(student);
            return new Response<>(ResponseStatus.SUCCESS, "Student updated successfully");
        } else {
            return response;
        }
    }

    @Override
    public Response<List<Student>> findAll() {
        List<Student> students = studentRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, students);
    }

    @Override
    public Response<Page<Student>> findAll(Pageable pageable, String searchKey) {
        Page<Student> students;
        if (searchKey == null || searchKey.equals("*")) {
            students = studentRepository.findAll(pageable);
        } else {
            students = studentRepository.search(searchKey, pageable);
        }
        return new Response<>(ResponseStatus.SUCCESS, null, students);
    }

    @Override
    public Response<?> deleteById(Long id) {
        studentRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Student deleted successfully");
    }

    @Override
    public Response<Student> findById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        return new Response<>(ResponseStatus.SUCCESS, null, student);
    }

}
