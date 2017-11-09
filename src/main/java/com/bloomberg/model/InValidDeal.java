package com.bloomberg.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Shivakumar CP
 *
 */
@Entity
@Table(name = "invalid_deal")
public class InValidDeal extends DealModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

}
