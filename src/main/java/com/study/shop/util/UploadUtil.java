package com.study.shop.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.study.shop.item.vo.ImgVO;

public class UploadUtil {
	//단일 업로드 메소드
	public static ImgVO uploadFile(MultipartFile img) {
		ImgVO imgVO = null;
		if(!img.isEmpty()) {
			imgVO = new ImgVO();
			
			String originFileName = img.getOriginalFilename();
			String uuid = UUID.randomUUID().toString();
			String extension = originFileName.substring(originFileName.lastIndexOf("."));
			String attchedFileName = uuid + extension;
			try {
				File file = new File(ConstVariable.UPLOAD_PATH + attchedFileName);
				img.transferTo(file);
				
				imgVO.setOriginFileName(originFileName);
				imgVO.setAttachedFileName(attchedFileName);
				imgVO.setIsMain("Y");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return imgVO;
	}
	
	//다중 파일 업로드 메소드
	public static List<ImgVO> multiFileUpload(MultipartFile[] imgs) {
		
		//첨부된 파일 정보를 다 담을수 있는 통
		List<ImgVO> result = new ArrayList<>();
		
		for(MultipartFile img : imgs) {
			if(!img.isEmpty()) {
					ImgVO vo =  uploadFile(img);
					vo.setIsMain("N");
					result.add(vo);
				}
			}
		return result;
		}
}
