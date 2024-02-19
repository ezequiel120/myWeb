package com.example.myWeb.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Service
public class CloudinaryService {


    Cloudinary cloudinary;

    private Map<String, String> valuesMap = new HashMap<>();

    public CloudinaryService() {
        valuesMap.put("cloud_name", "di1l9fdja");
        valuesMap.put("api_key", "836656322868689");
        valuesMap.put("api_secret", "ld36cqb-4WQ2xZy7V-Z-wBDFPG0");
        cloudinary = new Cloudinary(valuesMap);

     /*   Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dn3yyeopk",
                "api_key", "523737456587762",
                "api_secret", "4LytpXz8mmmPrfYrtQft8mA2Ssg"));
    */}



    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;
    }

    public Map delete(String id) throws IOException {
        Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
