package dev.vabalas.studentperformancesorter.rest.validator;

import dev.vabalas.studentperformancesorter.rest.common.Error;
import dev.vabalas.studentperformancesorter.rest.exception.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static dev.vabalas.studentperformancesorter.rest.service.StudentSortService.BUBBLE_SORT_TYPE;
import static dev.vabalas.studentperformancesorter.rest.service.StudentSortService.HEAP_SORT_TYPE;
import static dev.vabalas.studentperformancesorter.rest.service.StudentSortService.MERGE_SORT_TYPE;
import static java.lang.String.*;

@Component
public class StudentSortValidator {
  private static final List<String> ALLOWED_SORT_TYPES = List.of(BUBBLE_SORT_TYPE, HEAP_SORT_TYPE, MERGE_SORT_TYPE);

  public void validateSortStudentsRequest(MultipartFile file, String sortType) {
    var errors = new ArrayList<Error>();
    if (!ALLOWED_SORT_TYPES.contains(sortType)) {
      errors.add(new Error(format("Sort type %s is not allowed.", sortType)));
    }
    if (file.isEmpty()) {
      errors.add(new Error("File is empty."));
    }
    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }
}
