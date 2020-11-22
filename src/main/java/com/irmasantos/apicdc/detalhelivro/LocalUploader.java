package com.irmasantos.apicdc.detalhelivro;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class LocalUploader implements Uploader {

	@Override
	public String upload(@NotBlank MultipartFile capa) {
		//todo implement this
		return "http://s3/bucket/" + (capa != null ? capa.getOriginalFilename(): "url fake");
	}

}
