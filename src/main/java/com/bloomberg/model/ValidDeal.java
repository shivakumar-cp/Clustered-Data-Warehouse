package com.bloomberg.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Shivakumar C P
 *
 */
@Entity
@Table(name = "valid_deal")
public class ValidDeal extends DealModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

}
