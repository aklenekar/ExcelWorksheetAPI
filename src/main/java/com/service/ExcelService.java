package com.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.model.Entity;
import com.result.ResultCode;
import com.result.ResultException;
import com.utility.DateUtil;

@Service
public class ExcelService {

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
}
