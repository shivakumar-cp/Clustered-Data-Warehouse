package com.bloomberg.dao;

import java.util.Collection;
import java.util.List;

import com.bloomberg.model.AccumulativeDeal;
import com.bloomberg.model.InValidDeal;
import com.bloomberg.model.ValidDeal;

/**
 * 
 * @author Shivakumar C P
 *
 */
public interface IFileUploadDAO {

	boolean fileExists(String fileName);

	public <T extends ValidDeal> Collection<T> bulkValidSave(Collection<T> entities,
			List<AccumulativeDeal> accumulativeDeals);

	public <T extends InValidDeal> Collection<T> bulkInvalidSave(Collection<T> entities);

}
