package info.idb.crud.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.idb.crud.dto.Response;
import info.idb.crud.entity.Student;
import info.idb.crud.service.StudentService;
import info.idb.crud.utils.PageUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController implements BaseController<Student, Long> {

    private final StudentService studentService;

    @Override
    public ResponseEntity<Response<?>> save(Student data) {
        if (data.hasId()) {
            return ResponseEntity.ok(studentService.merge(data));
        } else {
            return ResponseEntity.ok(studentService.persist(data));
        }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Long id) {
        return ResponseEntity.ok(studentService.deleteById(id));
    }

    @Override
    public ResponseEntity<Response<Student>> findById(Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @Override
    public ResponseEntity<Response<List<Student>>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @Override
    public ResponseEntity<Response<Page<Student>>> findAllByPage(
            Integer pageNumber, Integer pageSize,
            String sortColumn, String sortOrder, String searchKey) {
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize, sortColumn, sortOrder);
        return ResponseEntity.ok(studentService.findAll(pageable, searchKey));
    }
}
