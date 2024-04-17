package org.project.spring_mini_project.features.category;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.category.dto.CategoryRequest;
import org.project.spring_mini_project.features.category.dto.CategoryResponse;
import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryRestController {
    private final CategoryService categoryService;

    @PostMapping
    @Operation(summary = "Create a category")
    public BaseResponse<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return BaseResponse.<CategoryResponse>createSuccess()
                .setPayload(categoryService.createCategory(categoryRequest));
    }

    @GetMapping
    @Operation(summary = "Get all categories")
    public BaseResponse<List<CategoryResponse>> findAllCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return BaseResponse.<List<CategoryResponse>>ok()
                .setPayload(categoryService.findAllCategories(page, size));
    }
    @GetMapping("/parent")
    @Operation(summary = "Get all parent categories")
    public BaseResponse<List<CategoryResponse>> findParentCategories() {
        return BaseResponse.<List<CategoryResponse>>ok()
                .setPayload(categoryService.findParentCategories());
    }
    @PutMapping("/{alias}")
    @Operation(summary = "Update a category")
    public BaseResponse<CategoryResponse> updateCategory(@PathVariable String alias, @RequestBody CategoryRequest categoryRequest) {
        return BaseResponse.<CategoryResponse>updateSuccess()
                .setPayload(categoryService.updateCategory(alias, categoryRequest));
    }
    @GetMapping("/{alias}")
    @Operation(summary = "Get category by alias")
    public BaseResponse<CategoryResponse> findCategoryByAlias(@PathVariable String alias) {
        return BaseResponse.<CategoryResponse>ok()
                .setPayload(categoryService.findCategoryByAlias(alias));
    }
    @PatchMapping("/{alias}/disable")
    @Operation(summary = "Disable a category")
    public BaseResponse<CategoryResponse> disableCategory(@PathVariable String alias) {
        return BaseResponse.<CategoryResponse>updateSuccess()
                .setPayload(categoryService.disableCategory(alias));
    }
    @PatchMapping("/{alias}/enable")
    @Operation(summary = "Enable a category")
    public BaseResponse<CategoryResponse> enableCategory(@PathVariable String alias) {
        return BaseResponse.<CategoryResponse>updateSuccess()
                .setPayload(categoryService.enableCategory(alias));
    }
}
