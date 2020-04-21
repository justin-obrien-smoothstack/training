package com.ss.training.librarymanager.test.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ss.training.librarymanager.entities.Publisher;

/**
 * @publisher Justin O'Brien
 *
 */
public class PublisherTest { // all passed
	static Publisher publisher;
	static Publisher equalPublisherA = new Publisher(2, "PublisherA", "AddressA");
	static Publisher equalPublisherB = new Publisher(2, "PublisherB", "AddressB");
	static Publisher unequalPublisherA = new Publisher(1, "Publisher", "Address");
	static Publisher unequalPublisherB = new Publisher(2, "Publisher", "Address");

	@Before
	public void before() {
		publisher = new Publisher(1, "Publisher", "Address");
	}

	@Test
	public void constructorTest() {
		Publisher newPublisher = new Publisher(4, "New Publisher", "New Address");
		assertEquals(newPublisher.getId(), 4);
		assertEquals(newPublisher.getName(), "New Publisher");
		assertEquals(newPublisher.getAddress(), "New Address");
	}

	@Test
	public void getIdTest() {
		assertEquals(publisher.getId(), 1);
	}

	@Test
	public void getNameTest() {
		assertEquals(publisher.getName(), "Publisher");
	}

	@Test
	public void setNameTest() {
		publisher.setName("Name");
		assertEquals(publisher.getName(), "Name");
	}
	
	@Test
	public void getAddressTest() {
		assertEquals(publisher.getAddress(), "Address");
	}

	@Test
	public void setAddressTest() {
		publisher.setAddress("The Address");
		assertEquals(publisher.getAddress(), "The Address");
	}

	@Test
	public void hashCodePosTest() {
		assertNotEquals(unequalPublisherA.hashCode(), unequalPublisherB.hashCode());
	}

	@Test
	public void hashCodeNegTest() {
		assertEquals(equalPublisherA.hashCode(), equalPublisherB.hashCode());
	}

	@Test
	public void equalsPosTest() {
		assertFalse(unequalPublisherA.equals(unequalPublisherB));
	}

	@Test
	public void equalsNegTest() {
		assertTrue(equalPublisherA.equals(equalPublisherB));
	}
}
