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
public class DeleteCategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public BaseResponse deleteCategory(Long id) {
		validate(id);
		BaseResponse baseresponse = new BaseResponse();
		Optional<Category> dbCategory = categoryRepository.findById(id);
		if (dbCategory.isPresent()) {
			categoryRepository.deleteById(id);

			baseresponse.setStatus(HttpStatus.ACCEPTED.value());
			baseresponse.setMessage("your category has been successfully deleted");
			return baseresponse;
		}
		baseresponse.setStatus(HttpStatus.BAD_REQUEST.value());
		baseresponse.setMessage("your id is incorrect");
		return baseresponse;
	}

	private void validate(Long id) {
		if (id <= 0 || id != (Long) id) {
			throw new ValidationException("FV001", "category id cannot be 0 or empty");
		}
	}
}
