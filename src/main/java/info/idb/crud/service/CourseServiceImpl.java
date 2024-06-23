package info.idb.crud.service;

import info.idb.crud.constants.ResponseStatus;
import info.idb.crud.dto.Response;
import info.idb.crud.entity.Course;
import info.idb.crud.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public Response<?> persist(Course course) {
        if (course.hasId()) {
            course.setId(null);
        }
        if (courseRepository.existsByName(course.getName())) {
            return new Response<>(ResponseStatus.ERROR, "Course name already exists");
        }
        courseRepository.save(course);
        return new Response<>(ResponseStatus.SUCCESS, "Course saved successfully");
    }

    @Override
    public Response<?> merge(Course course) {
        if (!course.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "ID required");
        }
        Course olCourse = courseRepository.findById(course.getId()).orElse(null);
        if (olCourse == null) {
            return new Response<>(ResponseStatus.ERROR, "Course not found");
        }
        if (!olCourse.getName().equals(course.getName())) {
            if (courseRepository.existsByName(course.getName())) {
                return new Response<>(ResponseStatus.ERROR, "Course name already exists");
            }
        }

        courseRepository.save(course);
        return new Response<>(ResponseStatus.SUCCESS, "Course updated successfully");
    }

    @Override
    public Response<List<Course>> findAll() {
        List<Course> courses = courseRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, courses);
    }

    @Override
    public Response<?> deleteById(Long id) {
        courseRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Course deleted successfully");
    }

    @Override
    public Response<Course> findById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        return new Response<>(ResponseStatus.SUCCESS, null, course);
    }

}
