package com.example.BaiTest.services.iservices;


import com.example.BaiTest.dtos.CourseTypeDto;

import java.util.List;

public interface CourseTypeService {
    CourseTypeDto createCourseType(CourseTypeDto courseTypeDto);
    CourseTypeDto updateCourseType(CourseTypeDto courseLevelDto, int courseTypeId);
    void deletedCourseType(int courseTypeId);
    CourseTypeDto getCourseTypeDto(int courseTypeId);

    List<CourseTypeDto> findByNameContaining(String keyWord);

}
