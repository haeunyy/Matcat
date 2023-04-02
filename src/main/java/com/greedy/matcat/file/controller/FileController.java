package com.greedy.matcat.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileController {
	@RequestMapping(value="/summer", method=RequestMethod.POST)
	public ResponseEntity<?> summerimage(@RequestParam("file") MultipartFile img, HttpServletRequest request) throws IOException {
		//String path = request.getServletContext().getRealPath("resources/upload");
		
		String path = "C:/Semi/Matcat/summer";
		log.info("[FileController] path : {}", path);
		File dir1 = new File(path);
		if (!dir1.exists()) {
			dir1.mkdirs();
		}
		Random random = new Random();
	
		long currentTime = System.currentTimeMillis();
		int	randomValue = random.nextInt(100);
		String fileName = Long.toString(currentTime) + "_"+randomValue+"_a_"+img.getOriginalFilename();
		log.info("[FileController] fileName : {}", fileName);
		File file = new File(path , fileName);
	
		img.transferTo(file);
		return ResponseEntity.ok().body("http://localhost:8001/summer/"+fileName);

	}
}