package com.example.demo.service.category;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.response.BaseResponse;

@Service
public class UpdateCategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public BaseResponse updateCategory(Category category) {
//		validate(category);
		BaseResponse baseresponse = new BaseResponse();
		Optional<Category> dbcategory = categoryRepository.findById(category.getCategoryid());
		if (dbcategory.isPresent()) {
			categoryRepository.save(category);
		} else {
			baseresponse.setStatus(HttpStatus.BAD_REQUEST.value());
			baseresponse.setMessage("your category id do not exist in database");
			return baseresponse;
		}
		baseresponse.setStatus(HttpStatus.ACCEPTED.value());
		baseresponse.setMessage("your room has been successfully registered");
		return baseresponse;
	}

//	private void validate(Category category) {
//		if (category.getCategoryType() == null || category.getCategoryType().isBlank()
//				|| category.getCategoryType().isEmpty()) {
//			throw new ValidationException("FV001", " category type cannot be null or empty");
//		} else if (category.getNoOfBed() <= 0) {
//			throw new ValidationException("FV002", " no fo rooms cannot be 0 or empty");
//		} else {
//
//		}
//	}

}
