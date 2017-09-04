package org.srinivas.siteworks.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.neo4j.support.Neo4jTemplate;


/**
 * Spring configuration class to setup a Spring container,GraphDB and Neo4JTemplate.
 */
@Configuration
@ImportResource("classpath:spring/spring-data-context.xml")
public class ApplicationConfig {

	/**
	 * Graph database service.
	 * @return the graph database service
	 */
	@Bean(destroyMethod = "shutdown")
	public GraphDatabaseService graphDatabaseService() {
		return new EmbeddedGraphDatabase("graph.db");
	}

	/**
	 * Template.
	 * @return the neo4j template
	 */
	@Bean
	public Neo4jTemplate template() {
		return new Neo4jTemplate(graphDatabaseService());
	}

}
