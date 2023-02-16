package ru.vsu.implementation;

import ru.vsu.entity.ExamResult;
import ru.vsu.entity.Student;
import ru.vsu.logic.StudentService;

import java.util.Collection;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentWork implements StudentService {
    @Override
    public List<String> getAdultStudentsLastNameSorted(Collection<Student> students) {
        return students.stream()
                .filter(student -> student.getAge() >= 18)
                .map(Student::getLastName)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Set<Student> getExcellentStudents(Collection<Student> students) {
        return students.stream()
                .filter(student -> student.getExamResults()
                        .stream()
                        .allMatch(examResult -> examResult.getMark() == 5))
                .collect(Collectors.toSet());
    }

    @Override
    public Double getAverageMark(Collection<Student> students) {
        return students.stream()
                .map(Student::getExamResults)
                .map(elem -> elem.stream()
                        .map(ExamResult::getMark)
                        .reduce(Integer::sum)
                        .orElse(0)/ (double) elem.size()
                )
                .reduce(Double::sum)
                .orElse(0.0) / students.size();
    }

    @Override
    public Student findYoungestStudent(Collection<Student> students) {
        return students.stream()
                .min(Comparator.comparing(Student::getAge))
                .orElse(null);
    }
}
