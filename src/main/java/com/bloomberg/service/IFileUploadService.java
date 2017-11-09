package com.bloomberg.service;

import java.util.List;

import com.bloomberg.model.CSVRecord;

/**
 * @author Shivakumar C P
 *
 */
public interface IFileUploadService {

	void saveValidData(List<CSVRecord> dealDetails);

	void saveInValidData(List<CSVRecord> dealDetails);

	boolean checkFileExist(String fileName);

}
