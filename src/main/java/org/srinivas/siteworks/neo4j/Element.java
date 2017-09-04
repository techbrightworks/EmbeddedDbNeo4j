package org.srinivas.siteworks.neo4j;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Element extends AbstractEntity {

	/** The sub elements. */
	@Fetch
	@RelatedTo(type = "SubElement")
	private Set<Element> subElements = new HashSet<Element>();
	
	/** The base element. */
	private Element baseElement;
	
	/** The name. */
	@Indexed(unique = true)
	private String name;
	
	/** The value. */
	private String value;
	
	/** The description. */
	private String description;

	/**
	 * Sets the name.
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value.
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the description.
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the description.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the sub elements.
	 * @return the sub elements
	 */
	public Set<Element> getSubElements() {
		return subElements;
	}

	/**
	 * Sets the sub elements.
	 * @param subElements the new sub elements
	 */
	public void setSubElements(Set<Element> subElements) {
		this.subElements = subElements;
	}

	/**
	 * Gets the base element.
	 * @return the base element
	 */
	public Element getBaseElement() {
		return baseElement;
	}

	/**
	 * Sets the base element.
	 * @param baseElement the new base element
	 */
	public void setBaseElement(Element baseElement) {
		this.baseElement = baseElement;
	}

	/**
	 * Adds the sub element.
	 * @param element the element
	 */
	public void addSubElement(Element element) {

		if (null == getSubElements() || getSubElements().isEmpty()) {
			this.subElements = new LinkedHashSet<Element>();
			getSubElements().add(element);
		} else {
			getSubElements().add(element);
		}
	}

}
