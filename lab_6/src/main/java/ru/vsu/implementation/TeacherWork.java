package ru.vsu.implementation;

import ru.vsu.entity.Student;
import ru.vsu.entity.Subject;
import ru.vsu.entity.Teacher;
import ru.vsu.logic.TeacherService;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class TeacherWork implements TeacherService {
    @Override
    public List<String> getSingleSubjectLecturerFio(Collection<Teacher> teachers) {
        return teachers.stream()
                .filter(teacher -> teacher.getSubjects().size() == 1)
                .map(Teacher::getFullName)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Student>> getTeacherNameToSupervisedStudentsMap(Collection<Student> students) {
        Map<String, List<Student>> teacherStudents = new HashMap<>();
        students.stream()
                .filter(student -> student.getSupervisor() != null)
                .forEach(student -> {
                    if (!teacherStudents.containsKey(student.getSupervisor().getFullName())) {
                        List<Student> listOfStudents = new ArrayList<>();
                        listOfStudents.add(student);
                        teacherStudents.put(student.getSupervisor().getFullName(), listOfStudents);
                    } else {
                        teacherStudents.get(student.getSupervisor().getFullName())
                                .add(student);
                    }
                });
        teacherStudents.values().stream()
                .forEach(list ->
                        list.sort(Comparator.comparing(Student::getLastName)));
        return teacherStudents;
    }

    @Override
    public BigDecimal getTeachersSalarySum(Collection<Teacher> teachers) {
        return teachers.stream()
                .map(Teacher::getSalary)
                .reduce(BigDecimal::add)
                .orElseThrow();
    }

    @Override
    public String findTeacherBySubject(Collection<Teacher> teachers, Subject subject) {
        return teachers.stream()
                .filter(teacher1 -> teacher1.getSubjects().contains(subject))
                .findFirst()
                .map(Teacher::getFullName)
                .orElse(null);
    }
}
