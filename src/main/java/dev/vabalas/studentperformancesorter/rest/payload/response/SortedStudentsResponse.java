package dev.vabalas.studentperformancesorter.rest.payload.response;

import dev.vabalas.studentperformancesorter.rest.model.Student;

import java.util.List;

public class SortedStudentsResponse {
  public long timeTakenMillis;
  public int size;
  public List<Student> students;

  public SortedStudentsResponse(long timeTakenMillis, int size, List<Student> students) {
    this.timeTakenMillis = timeTakenMillis;
    this.size = size;
    this.students = students;
  }

  public long getTimeTakenMillis() {
    return timeTakenMillis;
  }

  public int getSize() {
    return size;
  }

  public List<Student> getStudents() {
    return students;
  }

  @Override
  public String toString() {
    return "SortedStudentsResponse{" +
           "timeTakenMillis=" + timeTakenMillis +
           ", size=" + size +
           ", students=" + students +
           '}';
  }
}
