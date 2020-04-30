package com.ss.training.lms.versiontwo.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.AdminService;
import com.ss.training.lms.versiontwo.object.Author;
import com.ss.training.lms.versiontwo.object.Genre;
import com.ss.training.lms.versiontwo.object.Publisher;

/**
 * @author Justin O'Brien
 */
public class AdminServiceTest {

	/**
	 * 
	 */
	boolean containsAuthor(ArrayList<Author> authors, Author author) {
		for (Author thisAuthor : authors)
			if (thisAuthor.getName().equals(author.getName()) && thisAuthor.getBookIds().equals(author.getBookIds()))
				return true;
		return false;
	}

	boolean containsGenre(ArrayList<Genre> genres, Genre genre) {
		for (Genre thisGenre : genres)
			if (thisGenre.getName() != null && thisGenre.getName().equals(genre.getName())
					&& thisGenre.getBookIds().equals(genre.getBookIds()))
				return true;
		return false;
	}

	boolean containsPublisher(ArrayList<Publisher> publishers, Publisher publisher) {
		for (Publisher thisPublisher : publishers)
			if (thisPublisher.getName().equals(publisher.getName()) && thisPublisher.getAddress() != null
					&& thisPublisher.getAddress().equals(publisher.getAddress())
					&& thisPublisher.getBookIds().equals(publisher.getBookIds()) && thisPublisher.getPhone() != null
					&& thisPublisher.getPhone().equals(publisher.getPhone())
					&& thisPublisher.getBookIds().equals(publisher.getBookIds()))
				return true;
		return false;
	}

	@Test
	public void authorTest() {
		String testName = "Test Name";
		Author author = new Author();
		ArrayList<Author> allAuthors;
		AdminService adminService = new AdminService();
		author.setName(testName);
		author.getBookIds().add(1);
		adminService.createAuthor(author);
		allAuthors = (ArrayList<Author>) adminService.getAllObjects(LMS.author);
		assertTrue(containsAuthor(allAuthors, author));
		for (Author thisAuthor : allAuthors)
			if (thisAuthor.getName().equals(testName)) {
				adminService.deleteAuthor(thisAuthor);
			}
		allAuthors = (ArrayList<Author>) adminService.getAllObjects(LMS.author);
		assertFalse(containsAuthor(allAuthors, author));
	}

	@Test
	public void genreTest() {
		String testName = "Test Name";
		Genre genre = new Genre();
		ArrayList<Genre> allGenres;
		AdminService adminService = new AdminService();
		genre.setName(testName);
		genre.getBookIds().add(1);
		adminService.createGenre(genre);
		allGenres = (ArrayList<Genre>) adminService.getAllObjects(LMS.genre);
		assertTrue(containsGenre(allGenres, genre));
		for (Genre thisGenre : allGenres)
			if (thisGenre.getName() != null && thisGenre.getName().equals(testName)) {
				adminService.deleteGenre(thisGenre);
			}
		allGenres = (ArrayList<Genre>) adminService.getAllObjects(LMS.genre);
		assertFalse(containsGenre(allGenres, genre));
	}

	@Test
	public void publisherTest() {
		String testName = "Test Name";
		String testAddress = "Test Address";
		String testPhone = "Test Phone";
		Publisher publisher = new Publisher();
		ArrayList<Publisher> allPublishers;
		AdminService adminService = new AdminService();
		publisher.setName(testName);
		publisher.setAddress(testAddress);
		publisher.setPhone(testPhone);
		publisher.getBookIds().add(1);
		adminService.createPublisher(publisher);
		allPublishers = (ArrayList<Publisher>) adminService.getAllObjects(LMS.publisher);
		assertTrue(containsPublisher(allPublishers, publisher));
		for (Publisher thisPublisher : allPublishers)
			if (thisPublisher.getName().equals(testName)) {
				adminService.deletePublisher(thisPublisher);
			}
		allPublishers = (ArrayList<Publisher>) adminService.getAllObjects(LMS.publisher);
		assertFalse(containsPublisher(allPublishers, publisher));
	}

}
