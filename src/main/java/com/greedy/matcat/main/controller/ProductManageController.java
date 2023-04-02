/* 관리자 상품 관리 컨트롤러 */
package com.greedy.matcat.main.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.matcat.main.dto.FilesDTO;
import com.greedy.matcat.main.dto.ProductDTO;
import com.greedy.matcat.main.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductManageController {
	private final ProductService prdMngService;
	private final MessageSourceAccessor messageSourceAccessor;
	
	@Value("${image.image-dir}")
	private String IMAGE_DIR;
	
	public ProductManageController (ProductService prdMngService,
			 						MessageSourceAccessor messageSourceAccessor) {
		this.prdMngService = prdMngService;
		this.messageSourceAccessor = messageSourceAccessor;
	}
	
	
	/* 상품 리스트 */
	@GetMapping("/list")
	public String productManageList(@RequestParam(defaultValue="1") int page, Model model) {
		
		Map<String, Object> productListAndPaging = prdMngService.productManageList(page);
		model.addAttribute("paging", productListAndPaging.get("paging"));
		model.addAttribute("productList", productListAndPaging.get("productList"));
		
		return "product/productMain";
	}
	
	// 상품추가
	@GetMapping("/regist")
	public String goProductRegist() {
		return "product/productRegist";
	}
	

	
// 나중에 관리자만 할 수 있도록 제한 걸기!
	// 상품 등록
	@PostMapping("/regist")
	public String productRegist(ProductDTO product, 
								List<MultipartFile> attachImage,
								RedirectAttributes rttr) {
		
		log.info("[ProductManageController] product request : {} ", product);
		log.info("[ProductManageController] attachImage request : {} ", attachImage);
		
		
		String fileUploadDir = IMAGE_DIR + "product";
		String thumbnailDir = IMAGE_DIR + "thumbnail";
		
		File dir1 = new File(fileUploadDir);
		File dir2 = new File(thumbnailDir);
		
		log.info("[ProductManageController] dir1 : {} ", dir1);
		log.info("[ProductManageController] dir2 : {} ", dir2);
		
		/* 디렉토리가 없을 경우에 생성! */
		if(!dir1.exists() || !dir2.exists()) {
			dir1.mkdirs();
			dir2.mkdirs();
		}
		
		List<FilesDTO> filesList = new ArrayList<>();
		
		try {
			/*  */
			for(int i = 0 ; i < attachImage.size(); i++) {
				/* 첨부파일이 있는 경우에 로직 수행 */
				if(attachImage.get(i).getSize() > 0) {
					
					String originalFileName = attachImage.get(i).getOriginalFilename();
					log.info("[ProductManageController] originalFileName : {} ", originalFileName);
					
					String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
					String savedFileName = UUID.randomUUID().toString() + ext;
					
					log.info("[ProductManageController] savedFileName : {} ", savedFileName);
					
					/* 서버의 설정 디렉토리에 파일 저장하기 */
					attachImage.get(i).transferTo(new File(fileUploadDir + "/" + savedFileName));
					
					/* DB에 저장할 파일의 정보 처리 */
					FilesDTO fileInfo = new FilesDTO();
					fileInfo.setOriginName(originalFileName);
					fileInfo.setSavedName(savedFileName);
					fileInfo.setFilePath("/upload/product/");
					fileInfo.setFileDiv("상품");
					
					if(i == 0) {
						fileInfo.setThumbYN("Y");
						/* 대표 사진에 대한 썸네일을 가공해서 저장 */
						Thumbnails.of(fileUploadDir + "/" + savedFileName).size(300, 300)
						.toFile(thumbnailDir + "/thumbnail_" + savedFileName);
						fileInfo.setThumbPath("/upload/thumbnail/thumbnail_" + savedFileName);
					} else {
						fileInfo.setThumbYN("N");
					}
					filesList.add(fileInfo);
				}
			}
			
			log.info("[ProductManageController] filesList : {} ", filesList);
			
			product.setFilesList(filesList);
			
			prdMngService.productRegistImg(product);
			
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("product.rgtFailed"));
			/* 실패 시 이미 저장된 파일을 삭제함 */
			for(FilesDTO files : filesList) {
				File deleteFile = new File(files.getFilePath() + "/" + files.getSavedName());
				File deleteThumbnail = new File(thumbnailDir + "/thumbnail_" + files.getSavedName());
				
				deleteFile.delete();
				deleteThumbnail.delete();
			}
			return "redirect:/product/list";
		}
		
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("product.regist"));
		return "redirect:/product/list";
	}
	

	
	// 상품 수정
	@GetMapping("/product/modify")
	public String modify() {
		return "product/productModify";
	}
	
	

}
	
	



