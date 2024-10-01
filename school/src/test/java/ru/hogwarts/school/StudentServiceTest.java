package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student();
        student.setId(1L);
        student.setName("Harry Potter");
        student.setAge(15);
    }

    @Test
    void testCreateStudent() {
        when(studentRepository.save(student)).thenReturn(student);

        Student createdStudent = studentService.createStudent(student);

        assertNotNull(createdStudent);
        assertEquals(student.getName(), createdStudent.getName());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetStudentById() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student foundStudent = studentService.getStudentById(1L);

        assertNotNull(foundStudent);
        assertEquals(student.getName(), foundStudent.getName());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateStudent() {
        when(studentRepository.existsById(1L)).thenReturn(true);
        when(studentRepository.save(student)).thenReturn(student);

        Student updatedStudent = studentService.uptadeStudent(1L, student);

        assertNotNull(updatedStudent);
        assertEquals(student.getName(), updatedStudent.getName());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testDeleteStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student deletedStudent = studentService.deleteStudent(1L);

        assertNotNull(deletedStudent);
        assertEquals(student.getName(), deletedStudent.getName());
        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetStudentsByAge() {
        when(studentRepository.findByAge(15)).thenReturn(List.of(student));

        List<Student> studentsByAge = studentService.getStudentsByAge(15);

        assertEquals(1, studentsByAge.size());
        assertEquals(student.getName(), studentsByAge.get(0).getName());
        verify(studentRepository, times(1)).findByAge(15);
    }
}