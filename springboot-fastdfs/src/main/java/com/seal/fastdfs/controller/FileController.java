package com.seal.fastdfs.controller;


import com.seal.fastdfs.fastdfsclient.FastdfsClient;
import com.seal.fastdfs.fastdfsclient.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
public class FileController {

    @Autowired
    FastdfsClient fastDFSClient;

    @Autowired
    private Environment environment;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 上传到服务器
     *
     * @param file
     * @return
     */
    @RequestMapping("/upload/file")
    public String upload(MultipartFile file) {
        ResultData responseData = new ResultData();
        try {
            // 上传到服务器
            String filepath = fastDFSClient.uploadFileWithMultipart(file);

            // 设置访文件的Http地址. 有时效性.
            String token = FastdfsClient.getToken(filepath, environment.getProperty("fastdfs.http_secret_key"));
            return "redirect:http://" + environment.getProperty("file_server_addr") + "/" + filepath + "?token=" + token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下载文件
     *
     * @param filePath
     * @param response
     */
    @RequestMapping("/download/file")
    public void downloadFile(String filePath, HttpServletResponse response) throws Exception {
        try {
            fastDFSClient.downloadFile(filePath, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 删除服务器文件
     *
     * @param filePath
     */
    @RequestMapping("/delete/file")
    public ResultData deleteFile(String filePath) throws Exception {
        ResultData responseData = new ResultData();
        try {
            fastDFSClient.deleteFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setSuccess(false);
            responseData.setCode("下载文件失败！");
            responseData.setMessage(e.getMessage());
        }
        return responseData;
    }

    /**
     * 获取访问文件的token
     *
     * @param filePath
     * @return
     */
    @RequestMapping("/get/token")
    @ResponseBody
    public ResultData getToken(String filePath) throws Exception {
        ResultData responseData = new ResultData();
        // 设置访文件的Http地址. 有时效性.
        String token = FastdfsClient.getToken(filePath, environment.getProperty("fastdfs.http_secret_key"));
        responseData.setToken(token);
        responseData.setHttpUrl(environment.getProperty("file_server_addr") + "/" + filePath + "?" + token);

        return responseData;
    }


}
