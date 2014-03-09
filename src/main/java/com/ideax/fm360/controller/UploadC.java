package com.ideax.fm360.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ideax.common.exception.IllegalException;

@Controller
public class UploadC {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${mp3.upload.path}")
    String uploadPath;

    @RequestMapping("upload")
    @ResponseBody
    public String upload(HttpServletRequest req, HttpServletResponse resp) {
        StringBuilder returnMsg = new StringBuilder();
        if (ServletFileUpload.isMultipartContent(req)) {
            resp.setContentType(MediaType.APPLICATION_JSON.toString() + ";charset=UTF-8");

            try {
                String fileName = null;
                FileOutputStream imageOs = null;
                int length = 0;
                String localFileName = "";
                DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 1024, null);
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setHeaderEncoding("UTF-8");
                upload.setSizeMax(1024 * 1024 * 10);
                List<FileItem> fileItems = null;
                try {
                    fileItems = upload.parseRequest(req);
                } catch (FileUploadException e) {
                    logger.error("", e);
                    throw new IllegalException(1001, "FileUploadException when parse request!");
                }
                if (fileItems == null) {
                    throw new IllegalException(1001, "no post args!");
                }
                Iterator<FileItem> iter = fileItems.iterator();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("form data : [" + item.getFieldName() + ":" + item.getString("UTF-8") + "]");
                        }
                    } else {
                        fileName = item.getName();
                        if (fileName != null) {
                            length = 0;
                            InputStream ips = null;
                            String imgtype = fileName.substring(fileName.lastIndexOf('.'));
                            try {
                                // length = (int) item.getSize();
                                localFileName = System.currentTimeMillis() + imgtype;
                                imageOs = new FileOutputStream(uploadPath + System.currentTimeMillis() + imgtype); // init
                                ips = item.getInputStream();
                                length = IOUtils.copy(ips, imageOs);
                                logger.info("upload file length " + length);
                            } catch (IOException e) {
                                logger.error("", e);
                                throw new IllegalException(1001, "IOException when getting file!");
                            } finally {
                                IOUtils.closeQuietly(ips);
                            }
                        }
                    }
                }
                if (length == 0 || localFileName.length() == 0)
                    throw new IllegalException(1001, "size is 0 ");
                returnMsg.append("{\"filename\":\"" + localFileName + "\"}");
            } catch (IllegalException e) {
                logger.error("errorcode exception !", e);
                returnMsg.append("{\"errorcode\": " + e.getErrorCode() + "}");
            } catch (Exception e) {
                logger.error("", e);
                returnMsg.append("{\"errorcode\":1000}");
            } finally {
            }
        } else {
            returnMsg.append("not muilti-part form!");
            resp.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        }
        return returnMsg.toString();
    }
}
