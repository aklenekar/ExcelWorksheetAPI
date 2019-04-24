package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.model.Entity;
import com.result.GetResult;
import com.result.Result;
import com.result.ResultCode;
import com.service.ExcelService;

@Controller
public class TestFile {
	
	private ExcelService excelService;
	
	@Autowired
	public TestFile(ExcelService excelService) {
		this.excelService = excelService;
	}
	
	@GetMapping(value = "/healthcheck")
	@ResponseBody
	public Result healthCheck() {
		return new GetResult("Success", ResultCode.SUCCESS);
	}
	
	@PostMapping("/uploadFile")
	@ResponseBody
	public Result readFile(@RequestParam("file") MultipartFile file) throws IOException {
		File excel = new File("temp");
	    FileOutputStream o = new FileOutputStream(excel);
	    IOUtils.copy(file.getInputStream(), o);
	    o.close();
	    
	    List<Entity> entities = excelService.getListOfEntities(excel);
	    Result<List<Entity>> result = new Result<List<Entity>>("Success", ResultCode.SUCCESS);
	    result.setData(entities);
		return result;
	}
}
