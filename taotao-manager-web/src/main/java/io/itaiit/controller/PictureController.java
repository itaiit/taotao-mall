package io.itaiit.controller;

import io.itaiit.common.utils.JsonUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PictureController {

    @Value("${TAOTAO_IMAGE_SERVER_URL}")
    private String TAOTAO_IMAGE_SERVER_URL;

    @RequestMapping(value = "/pic/upload", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
    @ResponseBody
    public String fileUpload(MultipartFile uploadFile) {
        try {
            String oriFilename = uploadFile.getOriginalFilename();
            String extName = oriFilename.substring(oriFilename.lastIndexOf(".") + 1);
            ClientGlobal.init("resource/client.conf");
            StorageClient storageClient = new StorageClient();
            /*
             * results[0]: the group name to store the file 例如：group1
             * results[1]: the new created filename 例如：M00/00/00/wKg9iF3WrgmADflgABJXt_AS408859.jpg
             */
            String[] results = storageClient.upload_file(uploadFile.getBytes(), extName, null);
            String url = TAOTAO_IMAGE_SERVER_URL + results[0] + "/" + results[1];
            System.out.println("url = " + url);
            Map<String, Object> retMap = new HashMap<>();
            retMap.put("error", 0);
            retMap.put("url", url);
            return JsonUtils.objectToJson(retMap);
        } catch (Exception e) {
            e.printStackTrace();
            // 返回错误信息
            Map<String, Object> retMap = new HashMap<>();
            retMap.put("error", 1);
            retMap.put("message", "图片上传失败");
            return JsonUtils.objectToJson(retMap);
        }
    }
}
