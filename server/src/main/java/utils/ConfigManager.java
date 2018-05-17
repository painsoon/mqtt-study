package utils;

import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.Properties;

/**
 * @program: mqtt-study
 * @description: 加载配置文件管理
 * @author: pansn
 * @create: 2018-05-17 14:26
 **/

public class ConfigManager {

    private static ConfigManager instance;
    private Properties conf;

    public static ConfigManager getInstance(){
        if(instance==null){
            instance = new ConfigManager();
        }
        return instance;
    }

    public ConfigManager(){
        loadConfig();
    }

    private void loadConfig(){
        try {
            conf  = new Properties();
            InputStream input = this.getClass().getResourceAsStream("/mqtt.properties");
            conf.load(input);
        }catch (Exception e){
            throw new RuntimeException("can`t load config file");
        }
    }

    public String getString(String key){
        return conf == null ? null : conf.getProperty(key);
    }

    public int getInt(String key){
        return conf == null ? null : (StringUtils.isNotBlank(conf.getProperty(key))
                ? Integer.valueOf(conf.getProperty(key)) : null);
    }
}
