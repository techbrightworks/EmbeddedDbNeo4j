package org.srinivas.siteworks.neo4j.data;

import junit.framework.TestCase;

import org.junit.Test;
import org.srinivas.siteworks.neo4j.Element;


public class ElementDAOImplTest extends TestCase {

	private ElementDAOImpl elementDAOImpl;
	private Element elementOne;
	private Element elementTwo;
	private Element elementThree;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp() {
		elementOne = new Element();
		elementOne.setName("elementOne");
		elementOne.setDescription("descriptionOne");
		elementTwo = new Element();
		elementTwo.setName("elementTwo");
		elementTwo.setDescription("descriptionTwo");
		elementTwo.setBaseElement(elementOne);
		elementOne.addSubElement(elementTwo);
		elementThree = new Element();
		elementThree.setName("elementThree");
		elementThree.setDescription("descriptionThree");
		elementThree.setBaseElement(elementTwo);
		elementTwo.addSubElement(elementThree);
		elementDAOImpl = new ElementDAOImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	public void tearDown() {
	 elementDAOImpl.clearElements();
	}

	/**
	 * Test save Element.
	 */
	@Test
	public void testSaveElement() {
		Element result = elementDAOImpl.saveElement(elementOne);
		assertEquals("descriptionOne", result.getDescription());
	}

	/**
	 * Test retrieve Element.
	 */
	@Test
	public void testRetrieveElement() {
		elementDAOImpl.saveElement(elementOne);
		Element result = elementDAOImpl.retrieveElement("elementThree");
		assertEquals("descriptionThree", result.getDescription());
	}

	/**
	 * Test delete Element.
	 */
	@Test
	public void testDeleteElement() {
		elementDAOImpl.saveElement(elementOne);
		Element toBeDeletedElement = elementDAOImpl.retrieveElement("elementThree");
		elementDAOImpl.deleteElement(toBeDeletedElement);
		Element result = elementDAOImpl.retrieveElement("elementThree");
		assertNull(result);
	}

	/**
	 * Test update Element.
	 */
	@Test
	public void testUpdateElement() {
		elementDAOImpl.saveElement(elementOne);
		Element elementFour = new Element();
		elementFour.setName("elementFour");
		elementFour.setDescription("descriptionFour");
		Element changedElement = elementDAOImpl.retrieveElement("elementThree");
		changedElement.addSubElement(elementFour);
		elementDAOImpl.saveElement(changedElement);
		Element result = elementDAOImpl.retrieveElement("elementFour");
		assertEquals("descriptionFour", result.getDescription());
	}

	/**
	 * Test query Element.
	 */
	@Test
	public void testQueryElement() {
		elementDAOImpl.saveElement(elementOne);
		Element element = elementDAOImpl.queryElements("elementTwo");
		assertEquals("descriptionTwo", element.getDescription());

	}

}
