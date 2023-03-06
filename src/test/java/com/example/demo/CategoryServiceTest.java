package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.response.BaseResponse;
import com.example.demo.service.category.CreateCategoryService;
import com.example.demo.service.category.DeleteCategoryService;
import com.example.demo.service.category.GetAllCategoryService;
import com.example.demo.service.category.GetCategoryByIdService;
import com.example.demo.service.category.UpdateCategoryService;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
	@MockBean
	private CategoryRepository categoryRepository;

	@InjectMocks
	private CreateCategoryService createCategoryService;

	@InjectMocks
	private DeleteCategoryService deleteCategoryService;

	@InjectMocks
	private GetAllCategoryService getAllCategoryService;

	@InjectMocks
	private GetCategoryByIdService getCategoryByIdService;

	@InjectMocks
	private UpdateCategoryService updateCategoryService;

	@Test
	public void createCategory() {
		Category category = new Category(1L, "Single Bed", 1);
		BaseResponse baseResponse = createCategoryService.addCategory(category);
		assertNotNull(baseResponse);

	}	

	@Test
	public void getAllCategory() {
		List<Category> categoryList = new ArrayList<>();
		categoryList.add(new Category(1L, "Single Bed", 1));
		categoryList.add(new Category(2L, "Double Bed", 2));
		Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);
		assertThat(getAllCategoryService.getAllCategory()).isEqualTo(categoryList);
	}

	@Test
	public void getCategoryById() {
		Category category = new Category(1L, "Single Bed", 1);
		Optional<Category> userOptional = Optional.of(category);
		Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(userOptional);
		assertThat(getCategoryByIdService.getCategoryById(1L)).isEqualTo(userOptional);
	}

	@Test
	public void testUpdateCategory() {
		Category category = new Category(1L, "Single Bed", 1);
		Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(category));
		BaseResponse baseResponse = updateCategoryService.updateCategory(category);
		assertNotNull(baseResponse);

	}
	
	@Test
	public void deleteCategory() {
		Category category = new Category(1L, "Single Bed", 1);
		deleteCategoryService.deleteCategory(category.getCategoryid());
		verify(categoryRepository, times(1)).deleteById(1L);
	}

}
