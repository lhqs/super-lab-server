package cn.lhqs.lab.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Properties;
import java.util.UUID;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-08-25 9:54
 * description : QiniuStorage.class
 * version : 1.0
 */
public class QiniuStorage {

    private static final Logger logger = LoggerFactory.getLogger(QiniuStorage.class);

    private static final String CONFIG_BUCKET="qiniu.bucket";
    private static final String CONFIG_URL="qiniu.fileUrl";

    private static final String CONFIG_AK="qiniu.accesskey";
    private static final String CONFIG_SK="qiniu.secretkey";
    private static final String CONFIG_CDN="qiniu.cdn";
    private static final Auth auth;
    private static final UploadManager uploadManager;
    private static final String bucketName;
    private static final String cdn;
    private static final String fileURL;

    static{
        Properties properties = PropertiesUtil.getDefaultProperties();
        auth = Auth.create(properties.getProperty(CONFIG_AK), properties.getProperty(CONFIG_SK));
        Configuration cfg = new Configuration(Zone.zone1());
        uploadManager = new UploadManager(cfg);
        bucketName=properties.getProperty(CONFIG_BUCKET);
        cdn = properties.getProperty(CONFIG_CDN);
        fileURL = properties.getProperty(CONFIG_URL);
    }

    /**
     * 上传单个文件；返回上传key
     * @param buff
     */
    public static String uploadFile(byte[] buff, String fileName){
        String key = generateKey(fileName);
        key = upload(buff, key,false);
        return fileURL + key;
    }

    private static String generateKey(String fileName) {
        String KEY = "{0}/{1}/{2}";// 可以按照：/表名/字段名/业务值(refId) 处理
        return MessageFormat.format(KEY, "file", "lab", fileName);
    }


    /**
     * 上传文件
     * @param data 二进制格式的文件内容
     * @param key 文件在七牛中的key
     * @param update 是否是更新
     * @return
     */
    public static String upload(byte[] data,String key,boolean update){
        try {
            String token = update?auth.uploadToken(bucketName,key):auth.uploadToken(bucketName);
            Response response = uploadManager.put(data, key, token);
            DefaultPutRet ret = response.jsonToObject(DefaultPutRet.class);
            return ret.key;
        } catch (QiniuException e) {
            logger.info("upload failed--->",e);
        }
        return null;
    }

    /**
     * 获取文件后缀名
     * @param data
     * @param key
     * @return
     */
    private static String getFullKey(byte[] data,String key){
        FileType type= FileTypeHelper.getType(data);
        if(type!=null){
            return key+"."+type.name().toLowerCase();
        }else{
            return key;
        }
    }
}
