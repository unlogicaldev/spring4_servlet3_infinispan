package com.servlet3.sample.common.mongo;


import java.io.File;
import java.io.IOException;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.servlet3.sample.BaseTest;

public class MongoTest_04_GridFS extends BaseTest {

	@Test
	public void test() throws IOException {
		
		GridFS myFs = new GridFS(MongoTemplate.getDB());
		File file = new File("/Users/pgmnle/Documents/1.jpg");
		GridFSInputFile ifileHD = myFs.createFile(file);

		ifileHD.setContentType(file.getName());
		ifileHD.setFilename(file.getName());
		ifileHD.save();	
		
		System.out.println(ifileHD);
		

		GridFSDBFile ff = (GridFSDBFile) myFs.findOne((ObjectId) ifileHD.getId());
		System.out.println(ff);
		File file1 = new File("/Users/pgmnle/Documents/2.jpg");
		ff.writeTo(file1);

		myFs.remove((ObjectId) ifileHD.getId());

		ff = (GridFSDBFile) myFs.findOne((ObjectId) ifileHD.getId());
		System.out.println(ff);
	}

}
