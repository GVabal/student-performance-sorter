package dev.vabalas.studentperformancesorter.rest.service;

import com.opencsv.bean.CsvToBeanBuilder;
import dev.vabalas.studentperformancesorter.rest.payload.response.SortedStudentsResponse;
import dev.vabalas.studentperformancesorter.rest.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentSortService {
  public static final String BUBBLE_SORT_TYPE = "BUBBLE";
  public static final String HEAP_SORT_TYPE = "HEAP";
  public static final String MERGE_SORT_TYPE = "MERGE";

  public SortedStudentsResponse getSortedStudentsResponse(MultipartFile file, String sortType) {
    long begin = System.currentTimeMillis();
    List<Student> sortedEntries = sortStudents(file, sortType);
    long end = System.currentTimeMillis();
    return new SortedStudentsResponse(end - begin, sortedEntries.size(), sortedEntries);
  }

  private List<Student> sortStudents(MultipartFile file, String algo) {
    try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      var csvToBean = new CsvToBeanBuilder<Student>(reader)
        .withType(Student.class)
        .withIgnoreLeadingWhiteSpace(true)
        .build();
      List<Student> studentEntries = csvToBean.parse();
      if (BUBBLE_SORT_TYPE.equals(algo)) {
        return bubbleSortStudents(studentEntries);
      }
      else if (HEAP_SORT_TYPE.equals(algo)) {
        return heapSortStudents(studentEntries);
      }
      else if (MERGE_SORT_TYPE.equals(algo)) {
        return mergeSortStudents(studentEntries);
      }
      throw new RuntimeException("Should never throw");
    }
    catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  // TODO :: implement actually
  private List<Student> mergeSortStudents(List<Student> studentEntries) {
    return studentEntries.stream().sorted().collect(Collectors.toList());
  }

  // TODO :: implement actually
  private List<Student> heapSortStudents(List<Student> studentEntries) {
    return studentEntries.stream().sorted().collect(Collectors.toList());
  }

  // TODO :: implement actually
  private List<Student> bubbleSortStudents(List<Student> studentEntries) {
    return studentEntries.stream().sorted().collect(Collectors.toList());
  }
}
