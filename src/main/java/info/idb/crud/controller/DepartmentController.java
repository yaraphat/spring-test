package info.idb.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.idb.crud.dto.Response;
import info.idb.crud.entity.Department;
import info.idb.crud.service.DepartmentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/department")
public class DepartmentController implements BaseController<Department, Long> {
   
    private final DepartmentService departmentService;

    @Override
    public ResponseEntity<Response<?>> save(Department data) {
        if(data.hasId()) {
            return ResponseEntity.ok(departmentService.merge(data));
        } else {
            return ResponseEntity.ok(departmentService.persist(data));
        }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Long id) {
        return ResponseEntity.ok(departmentService.deleteById(id));
    }

    @Override
    public ResponseEntity<Response<Department>> findById(Long id) {
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @Override
    public ResponseEntity<Response<List<Department>>> findAll() {
        return ResponseEntity.ok(departmentService.findAll());
    }
}
