package info.idb.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.idb.crud.dto.Response;
import info.idb.crud.entity.Course;
import info.idb.crud.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController implements BaseController<Course, Long> {
   
    private final CourseService courseService;

    @Override
    public ResponseEntity<Response<?>> save(Course data) {
        if(data.hasId()) {
            return ResponseEntity.ok(courseService.merge(data));
        } else {
            return ResponseEntity.ok(courseService.persist(data));
        }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Long id) {
        return ResponseEntity.ok(courseService.deleteById(id));
    }

    @Override
    public ResponseEntity<Response<Course>> findById(Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @Override
    public ResponseEntity<Response<List<Course>>> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }
}
