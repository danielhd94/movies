package com.danielhd.movies.interfaces;

import java.io.InputStream;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;


public interface IFileService {

	public void save(String file, InputStream bytes);
	public void deleteFile(String file);
	public ResponseEntity<Resource> get(String file);
}
