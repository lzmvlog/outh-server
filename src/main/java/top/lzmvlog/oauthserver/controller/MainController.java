package top.lzmvlog.oauthserver.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2020年11月07日 23:24
 * @Description:
 */
@Profile("simple")
@RestController
public class MainController {

    @GetMapping("/")
    public String email() {
        return "这里是主页";
    }

    @GetMapping("/admin")
    public String admin() {
        return "这里是 admin 页";
    }

    @GetMapping("/user")
    public String user() {
        return "这里是 user 页";
    }
}
