package dev.vabalas.studentperformancesorter.rest;

import dev.vabalas.studentperformancesorter.rest.payload.response.SortedStudentsResponse;
import dev.vabalas.studentperformancesorter.rest.service.StudentSortService;
import dev.vabalas.studentperformancesorter.rest.validator.StudentSortValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class StudentSortController {
  private final StudentSortService studentSortService;
  private final StudentSortValidator studentSortValidator;

  public StudentSortController(StudentSortService studentSortService, StudentSortValidator studentSortValidator) {
    this.studentSortService = studentSortService;
    this.studentSortValidator = studentSortValidator;
  }

  @Operation(summary = "Sort students by grade")
  @Parameter(description = "Algorithm which will be used for sorting",
    name ="sortType", schema = @Schema(type = "string", allowableValues = {"BUBBLE", "HEAP", "MERGE"}))
  @PostMapping(path = "sort/students", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public SortedStudentsResponse sortStudents(@RequestParam MultipartFile file, @RequestParam String sortType) {
    studentSortValidator.validateSortStudentsRequest(file, sortType);
    return studentSortService.getSortedStudentsResponse(file, sortType);
  }
}
