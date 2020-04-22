package com.app.validation;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import com.app.exception.BookException;

public class ValidateBookDetails {

	public static void checkNullValue(Object obj,String field) throws BookException {
		Optional<Object> optional_obj = Optional.ofNullable(obj);
		optional_obj.orElseThrow(() -> new BookException(HttpStatus.UNPROCESSABLE_ENTITY ,field + " is null,not allowed"));
		if(obj instanceof String)
			checkZeroLength((String)obj,field);
		if(field == "name")
			validateName((String) obj);
		else if(field == "rating")
			validateRating((float) obj);
		else if(field=="price")
			validatePrice((double) obj);
		else if(field=="author")
			validateAuthor((String) obj);
		else if(field=="description")
			validateDescription((String) obj);
		else if(field=="publisher")
			validatePublisher((String) obj);
	} 

	public static void checkZeroLength(String obj,String field) throws BookException {
		if(obj.length()==0)
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY,"Zero length of "+field);	
	}

	public static void validateName(String name) throws BookException {
		if(name.length()<3)
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY, "Name length can not be less than 3");
		if(!name.matches("^[a-zA-Z]*$"))
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY,"Name contains only Alphabets");
	}
	public static void validateRating(float rating) throws BookException {
		if(rating <= 0 || rating > 5) 
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY,"Rate between 0 and 5");
	}
	
	public static void validatePrice(double price) throws BookException {
		if(price < 10 || price > 5000)
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY,"Invalid price of book");
	}
	public static void validateAuthor(String author) {
		if(author.length()<3)
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY, "Author name can not be less than 3 characters");
		if(!author.matches("^[a-zA-Z]*$"))
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY,"Name contains only Alphabets");	
	}
	public static void validateDescription(String description) {
		if(description.length()<10)
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY, "Descreption can not be less than 10 characters");	
	}
	public static void validatePublisher(String author) {
		if(author.length()<3)
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY, "Publisher name can not be less than 3 characters");
		if(!author.matches("^[a-zA-Z]*$"))
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY,"Publisher name contains only Alphabets");	

	}

	
}
