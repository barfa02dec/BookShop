package com.app.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.controller.BookController;
import com.app.exception.BookException;
import com.app.model.Book;

@SpringBootTest
public class TestAddBook {

	@Autowired
	BookController bookController;
	
	private Book actualBook;
	private Book expectedBook;
	
	public TestAddBook() {
		this.actualBook = new Book();
		this.expectedBook = new Book();
	}
	
	@Test
	public void testWriteInput() throws BookException {

		//Create Actual book object and set values
		//Book actualBook = new Book();
		actualBook.setName("Book");
		actualBook.setAuthor("Author");
		actualBook.setDescription("description");
		actualBook.setPrice(111.1);
		actualBook.setPublisher("publisher");
		actualBook.setRating(1.1f);
	
		//Create expected book object and set expected values
		//Book expectedBook = new Book();
		expectedBook.setBookId(1);
		expectedBook.setName("Book");
		expectedBook.setAuthor("Author");
		expectedBook.setDescription("description");
		expectedBook.setPrice(111.1);
		expectedBook.setPublisher("publisher");
		expectedBook.setRating(1.1f);
		
		//Test case for correct input correct Input
		assertEquals(expectedBook, bookController.addBook(actualBook),"Successfully add book");		
	}
	
	@Test
	public void testNullfield() {
		//Test  case for null values of name
		assertThrows(BookException.class, () -> actualBook.setName(null),"Name can not be null");
		assertThrows(BookException.class, () -> actualBook.setAuthor(null),"Author name can not be null");
		assertThrows(BookException.class, () -> actualBook.setDescription(null),"Description can not be null");
		assertThrows(BookException.class, () -> actualBook.setPublisher(null),"Publisher name can not be null");
	}
	
	@Test
	public void testDuplicateName() {
		actualBook.setName("Book");
		actualBook.setAuthor("Author");
		actualBook.setDescription("description");
		actualBook.setPrice(111.1);
		actualBook.setPublisher("publisher");
		actualBook.setRating(1.1f);
		assertThrows(BookException.class, () -> bookController.addBook(actualBook));
	}
	
	@Test
	public void testFieldLengthZero() {
		assertThrows(BookException.class, ()-> actualBook.setName(""));
		assertThrows(BookException.class, ()-> actualBook.setAuthor(""));
		assertThrows(BookException.class, ()-> actualBook.setDescription(""));
		assertThrows(BookException.class, ()-> actualBook.setPublisher(""));		
	}
	
	@Test
	public void testInvalidLengthOfFiled() {
		assertThrows(BookException.class, ()-> actualBook.setName("ab"));
		assertThrows(BookException.class, ()-> actualBook.setAuthor("ab"));
		assertThrows(BookException.class, ()-> actualBook.setDescription("ab"));
		assertThrows(BookException.class, ()-> actualBook.setPublisher("ab"));		
	}
	
	@Test
	public void testNoNumberContainsField() {
		assertThrows(BookException.class, ()-> actualBook.setName("Name1"));
		assertThrows(BookException.class, ()-> actualBook.setAuthor("Author1"));
		assertThrows(BookException.class, ()-> actualBook.setPublisher("Publisher1"));		
	}
	
	@Test
	public void testRatingValue() {
		assertThrows(BookException.class,() -> actualBook.setRating(-1));
		assertThrows(BookException.class,() -> actualBook.setRating(0));
		assertThrows(BookException.class,() -> actualBook.setRating(6));
		actualBook.setRating(1);
	}
	
	@Test
	public void testPriceValue() {
		assertThrows(BookException.class,() -> actualBook.setPrice(-1));
		assertThrows(BookException.class,() -> actualBook.setPrice(0));
		assertThrows(BookException.class,() -> actualBook.setPrice(9));
		assertThrows(BookException.class,() -> actualBook.setPrice(5001));
		actualBook.setPrice(10);
		actualBook.setPrice(5000);	
	}
}

