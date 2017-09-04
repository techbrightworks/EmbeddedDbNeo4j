package org.srinivas.siteworks.neo4j;

import org.springframework.data.neo4j.annotation.GraphId;


public abstract class AbstractEntity {

	@GraphId
	private Long id;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (id == null || obj == null || !getClass().equals(obj.getClass())) {
			return false;
		}
		return id.equals(((AbstractEntity) obj).id);

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}
}
