package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author:voler
 * @create: 2023-02-28 19:58
 * @version: 1.0
 */
public interface CourseBaseInfoService {
    public PageResult<CourseBase> querytCourseBaseList(PageParams params, QueryCourseParamsDto queryCourseParamsDto);
}
