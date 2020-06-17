package net.tce.app.aws;

import java.io.File;
import java.io.InputStream;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;

public interface ClientAWSServer {

	PutObjectResult putObject(String path, File file);
	PutObjectResult putObject(String path, InputStream  inputStream, ObjectMetadata objectMetadata);
	void deleteObject(String path);
	void deleteObjectsInFolder(String folderPath);
	
}
