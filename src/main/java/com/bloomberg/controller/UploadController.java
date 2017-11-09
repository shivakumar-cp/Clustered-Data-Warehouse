package com.bloomberg.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bloomberg.model.CSVRecord;
import com.bloomberg.service.FileUploadService;
import com.bloomberg.util.Logger;
import com.opencsv.CSVReader;

/**
 * This class is the main controller for the application
 * 
 * @author Shivakumar CP
 */

@Controller
public class UploadController {

	private final Logger logger = Logger.getLogger(UploadController.class);

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	FileUploadService service;

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/*")
	public String homePage() {
		logger.logInfo("Stepped into the homePage method");
		return "fileUpload";
	}

	@PostMapping(value = "/upload")
	public String uploadFile(ModelMap model, @RequestParam("file") MultipartFile file, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		String METHOD_NAME = "uploadFile";

		Map<String, String> messages = new HashMap<String, String>();
		logger.logInfo(METHOD_NAME + "started");
		if (file.isEmpty()) {
			messages.put("alert-danger", messageSource.getMessage("missing.file", null, Locale.getDefault()));
			model.put("messages", messages);
			logger.logInfo("Validation failed file is empty");
			return "fileUpload";
		} else if (service.checkFileExist(file.getOriginalFilename())) {
			messages.put("alert-danger", "File already exist");
			model.put("messages", messages);
			logger.logInfo("File already exist");
			return "fileUpload";
		}
		String fileName = file.getOriginalFilename();
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		File dir = new File(rootPath + File.separator + "uploadedfile");
		if (!dir.exists()) {
			dir.mkdirs();
		}

		File serverFile = new File(dir.getAbsolutePath() + File.separator + (fileName));

		try {
			try (InputStream is = file.getInputStream();
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
				int i;
				// write file to server
				while ((i = is.read()) != -1) {
					stream.write(i);
				}
				stream.flush();
			}
		} catch (IOException ex) {
			messages.put("alert-danger",
					messageSource.getMessage("failed.msg", new Object[] { ex }, Locale.getDefault()));
			model.put("messages", messages);
			logger.LogException(METHOD_NAME + " crashed ", ex);
			return "fileUpload";

		}

		try {
			logger.logInfo(" reading CSV file");
			List<CSVRecord> validDeals = new ArrayList<>();
			List<CSVRecord> inValidDeals = new ArrayList<>();
			for (String[] line : readCSVFile(serverFile, fileName)) {
				CSVRecord csvRecord = extractData(line);
				csvRecord.setFileName(fileName);

				if (StringUtils.isEmpty(csvRecord.getFromCurrency()) || StringUtils.isEmpty(csvRecord.getToCurrency())
						|| StringUtils.isEmpty(csvRecord.getDealDate()) || StringUtils.isEmpty(csvRecord.getAmount())) {
					inValidDeals.add(csvRecord);
				} else {
					validDeals.add(csvRecord);
				}

			}
			if (validDeals.size() > 0) {
				service.saveValidData(validDeals);
			}
			if (inValidDeals.size() > 0) {
				service.saveInValidData(inValidDeals);
			}

		} catch (Exception e) {
			logger.LogException(METHOD_NAME + " crashed ", e);
		}

		messages.put("success", messageSource.getMessage("success.msg", new Object[] { file.getOriginalFilename() },
				Locale.getDefault()));
		model.put("messages", messages);
		logger.logInfo(METHOD_NAME + " completed successfully!!!");
		return "fileUpload";
	}

	/**
	 * This method extract the data and return the CVSRecord class
	 * 
	 * @param line
	 * 
	 */
	CSVRecord extractData(String[] line) {

		CSVRecord dealModel = new CSVRecord();
		dealModel.setToCurrency(line[0]);
		dealModel.setFromCurrency(line[1]);

		try {
			dealModel.setDealDate(formatter.parse(line[2]));
		} catch (ParseException e) {
			logger.LogException("Exception while parsing CSV columns: ", e);
		}

		dealModel.setAmount(new BigInteger(line[3]));
		return dealModel;
	}

	List<String[]> readCSVFile(File serverFile, String fileName) {
		String METHOD_NAME = "readCSVFile";
		List<String[]> lines = null;
		try {
			logger.logInfo(" reading CSV file");
			FileReader fileReader = new FileReader(serverFile);
			CSVReader reader = new CSVReader(fileReader, ',');
			lines = reader.readAll();

		} catch (IOException e) {
			logger.LogException(METHOD_NAME + " crashed ", e);
		}

		return lines;
	}

}