package com.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.model.Entity;
import com.result.ResultCode;
import com.result.ResultException;
import com.utility.AWSUtil;
import com.utility.DateUtil;

@Service
public class ExcelService {

	@Autowired
	private AWSUtil awsUtil;

	public List<Entity> getListOfEntities(File uploadedFile) {
		List<Entity> entities = new ArrayList<>();
		try (FileInputStream file = new FileInputStream(uploadedFile);
				XSSFWorkbook workbook = new XSSFWorkbook(file);) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Entity entity = new Entity();
				entity.setItems(row.getCell(0).getStringCellValue());
				entity.setQuantity(row.getCell(1).getNumericCellValue());
				entity.setSpend(row.getCell(2).getNumericCellValue());
				entity.setSpentPerDay(row.getCell(3).getNumericCellValue());
				entity.setTotalPerMonth(entity.getSpentPerDay() * DateUtil.currentMonthDays());
				entities.add(entity);
			}
		} catch (Exception e) {
			throw new ResultException("Failed", ResultCode.ERROR);
		}
		return entities;
	}

	public String uploadFileToS3(MultipartFile multipartFile) {
		String fileName = multipartFile.getOriginalFilename();
		try {
			// creating the file in the server (temporarily)
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(multipartFile.getBytes());
			fos.close();

			PutObjectRequest putObjectRequest = new PutObjectRequest(awsUtil.bucketName + "/" + "ExcelFiles", fileName, file);

			awsUtil.getS3Client().putObject(putObjectRequest);
			// removing the file created in the server
			file.delete();
		} catch (IOException | AmazonServiceException ex) {
			throw new ResultException("Failed to upload file", ResultCode.ERROR);
		}
		return "Successfully uploaded";
	}
}
