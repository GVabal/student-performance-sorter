package dev.vabalas.studentperformancesorter.rest.model;

import com.opencsv.bean.CsvBindByPosition;

public class Student implements Comparable<Student> {
  @CsvBindByPosition(position = 0)
  public String name;
  @CsvBindByPosition(position = 1)
  public double grade;

  public String getName() {
    return name;
  }

  public double getGrade() {
    return grade;
  }

  @Override
  public int compareTo(Student o) {
    if (this.grade == o.grade) {
      return 0;
    }
    return this.grade < o.grade ? 1 : -1;
  }

  @Override
  public String toString() {
    return "Student{" +
           "name='" + name + '\'' +
           ", grade=" + grade +
           '}';
  }
}
