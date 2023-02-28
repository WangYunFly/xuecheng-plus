package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author voler
 * @version 1.0
 * @description 课程管理Service
 * @create 2023-02-28 20:36
 */
@Slf4j
@Service
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {
    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Override
    public PageResult<CourseBase> querytCourseBaseList(PageParams params, QueryCourseParamsDto queryCourseParamsDto) {
        //分页查询
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //拼接查询条件
        //根据课程名称模糊查询
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()),CourseBase::getName,queryCourseParamsDto.getCourseName());
        //根据审核状态
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()),CourseBase::getAuditStatus,queryCourseParamsDto.getAuditStatus());
        //根据课程发布状态
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getPublishStatus()),CourseBase::getStatus,queryCourseParamsDto.getPublishStatus());


        //分页参数
        Page<CourseBase> page = new Page<>(params.getPageNo(), params.getPageSize());

        //分页查询E page分页参数
        Page<CourseBase> pageresult = courseBaseMapper.selectPage(page, queryWrapper);
        //数据
        List<CourseBase> items = pageresult.getRecords();
        //总记录数
        long total = pageresult.getTotal();
        //准备返回数据    List<T> items; long counts; long page; long pageSize;
        PageResult<CourseBase> courseBasePageResult = new PageResult<>(items, total, params.getPageNo(), params.getPageSize());
        return courseBasePageResult;
    }
}
