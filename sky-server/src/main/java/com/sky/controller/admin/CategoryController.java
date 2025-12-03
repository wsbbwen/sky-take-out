package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("新增分类")
    @PostMapping
    public Result save(CategoryDTO categoryDTO){
        log.info("新增分类:{}",categoryDTO);

        categoryService.save(categoryDTO);
        return  Result.success();
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分页查询");

        PageResult pageResult = categoryService.page(categoryPageQueryDTO);
        return   Result.success(pageResult);
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        log.info("删除分类:{}",id);

        categoryService.deleteById(id);
        return Result.success();
    }

    @ApiOperation("修改分类")
    @PutMapping
    public Result<String> update(@RequestBody CategoryDTO categoryDTO){
        log.info("修改分类：{}",categoryDTO);

        categoryService.update(categoryDTO);
        return Result.success();
    }

    @ApiOperation("启用禁用分类")
    @PostMapping("status/{status}")
    public Result updateStatus(@PathVariable Integer status,Long id){
        log.info("启用禁用分类");

        categoryService.updateStatus(status, id);
        return Result.success();
    }

    public Result<List<Category>> list(Integer type){
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}
