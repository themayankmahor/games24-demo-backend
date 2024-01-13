package com.mm.games24.services;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;


public interface FileService {
	
	///Upload Image
	String uploadImage(String path, MultipartFile file) throws java.io.IOException;
	
	///Get Image
	InputStream getResource(String path, String fileName) throws FileNotFoundException;
	
}
