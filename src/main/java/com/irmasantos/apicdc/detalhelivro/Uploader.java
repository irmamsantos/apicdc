package com.irmasantos.apicdc.detalhelivro;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader {

	String upload(@NotBlank MultipartFile capa);

}
