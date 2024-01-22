package com.example.BaiTest.responses;

import com.example.BaiTest.model.Course;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseTypeResponse {
    private String courseName;
    private String imageCourse;
    private String courseTypeName;
    private String lessonCount;
    private String timeLessonTotal;
}
