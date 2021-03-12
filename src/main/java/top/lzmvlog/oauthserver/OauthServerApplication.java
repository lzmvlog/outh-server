package top.lzmvlog.oauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 开启授权服务器功能
//@EnableAuthorizationServer
public class OauthServerApplication {

    /**
     * OAuth 授权模式
     * 1、授权码模式(authorization_code)
     * 2、密码模式(password)
     * 3、客户端模式(client_credentials)
     * 4、隐式授权模式(implicit)
     * 5、刷新token模式(refresh_token)
     * 默认 client_credentials
     */

    public static void main(String[] args) {
        SpringApplication.run(OauthServerApplication.class, args);
    }

}
