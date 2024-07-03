package info.idb.crud.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import info.idb.crud.dto.Response;
import info.idb.crud.entity.Student;

public interface BaseController<ENTITY, ID> {

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Response<?>> save(@RequestBody ENTITY data);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Response<?>> deleteById(@PathVariable("id") ID id);

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Response<ENTITY>> findById(@PathVariable("id") ID id);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Response<List<ENTITY>>> findAll();

    @GetMapping(value = "/{pageNumber}/{pageSize}/{sortColumn}/{sortOrder}/{searchKey}", produces = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<Response<Page<Student>>> findAllByPage(
            @PathVariable(value = "pageNumber", required = false) Integer pageNumber,
            @PathVariable(value = "pageSize", required = false) Integer pageSize,
            @PathVariable(value = "sortColumn", required = false) String sortColumn,
            @PathVariable(value = "sortOrder", required = false) String sortOrder,
            @PathVariable(value = "searchKey", required = false) String searchKey) {
        return null;
    }

}
