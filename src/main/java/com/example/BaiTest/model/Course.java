package com.example.BaiTest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String nameOfCourse;

    private String courseSubTile;

    private String imageCourse;

    private int lessonCount;

    private int chapterCount;

    private String timeLessonTotal;

    private int registerCount;

    private int doneCount;

}
