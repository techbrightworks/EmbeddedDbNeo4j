package org.srinivas.siteworks.neo4j.data;

import java.util.HashMap;
import java.util.Map;
import org.neo4j.graphdb.Transaction;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.srinivas.siteworks.neo4j.ApplicationConfig;
import org.srinivas.siteworks.neo4j.Element;

@EnableTransactionManagement
public class ElementDAOImpl {


	private static ConfigurableApplicationContext context;

	private Transaction tx;

	private Neo4jTemplate template;

	private GraphRepository<Element> repository;

	/**
	 * Instantiates a new ElementDAOImpl.
	 */
	public ElementDAOImpl() {
		context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		template = (Neo4jTemplate) context.getBean("template");
		repository = template.repositoryFor(org.srinivas.siteworks.neo4j.Element.class);
	}

	/**
	 * Save element.	
	 * @param element the element
	 * @return the element
	 */
	public Element saveElement(Element element) {

		tx = template.getGraphDatabase().beginTx();
		Element result = repository.save(element);
		tx.success();
		tx.finish();
		tx = null;

		return result;
	}

	/**
	 * Retrieve element.
	 * @param name the name
	 * @return the element
	 */
	public Element retrieveElement(String name) {

		tx = template.getGraphDatabase().beginTx();
		Element result = repository.findByPropertyValue("name", name);
		tx.success();
		tx.finish();
		tx = null;

		return result;
	}

	/**
	 * Delete element.	
	 * @param element the element
	 */
	public void deleteElement(Element element) {

		tx = template.getGraphDatabase().beginTx();
		Element ele = repository.findByPropertyValue("name", element.getName());
		repository.delete(ele.getId());
		tx.success();
		tx.finish();
		tx = null;
	}

	/**
	 * Update element.
	 * @param name the name
	 * @return the element
	 */
	public Element updateElement(String name) {

		tx = template.getGraphDatabase().beginTx();
		Element result = repository.findByPropertyValue("name", name);
		tx.success();
		tx.finish();
		tx = null;

		return result;
	}

	/**
	 * Clear elements.	
	 */
	public void clearElements() {

		tx = template.getGraphDatabase().beginTx();
		repository.deleteAll();
		tx.success();
		tx.finish();
		tx = null;
		template.getGraphDatabaseService().shutdown();
	}

	/**
	 * Query elements.
	 * @param name the name
	 * @return the element
	 */
	public Element queryElements(String name) {
		tx = template.getGraphDatabase().beginTx();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("param", name);
		String query = "START Element=node:Element(name={param}) RETURN Element";
		EndResult<Element> result = repository.query(query, parameterMap);
		tx.success();
		tx.finish();
		tx = null;
		return result.singleOrNull();
	}

	/**
	 * Gets the template.	
	 * @return the template
	 */
	public Neo4jTemplate getTemplate() {
		return template;
	}

	/**
	 * Sets the template.	 
	 * @param template the new template
	 */
	public void setTemplate(Neo4jTemplate template) {
		this.template = template;
	}

}
