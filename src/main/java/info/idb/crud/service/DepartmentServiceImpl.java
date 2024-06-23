package info.idb.crud.service;

import info.idb.crud.constants.ResponseStatus;
import info.idb.crud.dto.Response;
import info.idb.crud.entity.Department;
import info.idb.crud.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Response<?> persist(Department department) {
        if (department.hasId()) {
            department.setId(null);
        }
        if (departmentRepository.existsByName(department.getName())) {
            return new Response<>(ResponseStatus.ERROR, "Department name already exists");
        }
        if (departmentRepository.existsByCode(department.getCode())) {
            return new Response<>(ResponseStatus.ERROR, "Department code already exists");
        }
        departmentRepository.save(department);
        return new Response<>(ResponseStatus.SUCCESS, "Department saved successfully");
    }

    @Override
    public Response<?> merge(Department department) {
        if (!department.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "ID required");
        }
        Department olDepartment = departmentRepository.findById(department.getId()).orElse(null);
        if (olDepartment == null) {
            return new Response<>(ResponseStatus.ERROR, "Department not found");
        }
        if (!olDepartment.getName().equals(department.getName())) {
            if (departmentRepository.existsByName(department.getName())) {
                return new Response<>(ResponseStatus.ERROR, "Department name already exists");
            }
        }
        if (!olDepartment.getCode().equals(department.getCode())) {
            if (departmentRepository.existsByCode(department.getCode())) {
                return new Response<>(ResponseStatus.ERROR, "Department code already exists");
            }
        }

        departmentRepository.save(department);
        return new Response<>(ResponseStatus.SUCCESS, "Department updated successfully");
    }

    @Override
    @Transactional
    public Response<List<Department>> findAll() {
        List<Department> departments = departmentRepository.findAll();
        departments.forEach((dept) -> dept.getStudents().size());
        return new Response<>(ResponseStatus.SUCCESS, null, departments);
    }

    @Override
    public Response<?> deleteById(Long id) {
        departmentRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Department deleted successfully");
    }

    @Override
    public Response<Department> findById(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        return new Response<>(ResponseStatus.SUCCESS, null, department);
    }

}
