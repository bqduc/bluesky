/**
 * 
 */
package net.paragon.pmsp.i18n;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @author ducbui
 *
 * Implements an Enumeration that combines elements from a Set and an
 * Enumeration. Used by {@link MultiplePropertiesResourceBundle}.
 */
class ResourceBundleEnumeration implements Enumeration<String> {

	private Set<String> set;
	private Iterator<String> iterator;
	private Enumeration<String> enumeration;
	private String next = null;

	/**
	 * Constructs a resource bundle enumeration.
	 * 
	 * @param set
	 *          a set providing some elements of the enumeration
	 * @param enumeration
	 *          an enumeration providing more elements of the enumeration.
	 *          enumeration may be null.
	 */
	ResourceBundleEnumeration(Set<String> set, Enumeration<String> enumeration) {
		this.set = set;
		this.iterator = set.iterator();
		this.enumeration = enumeration;
	}


	public boolean hasMoreElements() {
		if (next == null) {
			if (iterator.hasNext()) {
				next = iterator.next();
			} else if (enumeration != null) {
				while (next == null && enumeration.hasMoreElements()) {
					next = enumeration.nextElement();
					if (set.contains(next)) {
						next = null;
					}
				}
			}
		}
		return next != null;
	}

	public String nextElement() {
		if (hasMoreElements()) {
			String result = next;
			next = null;
			return result;
		} else {
			throw new NoSuchElementException();
		}
	}
}
