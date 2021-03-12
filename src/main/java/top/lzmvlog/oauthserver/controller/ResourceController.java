package top.lzmvlog.oauthserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2020年11月07日 23:27
 * @Description:
 */
@Slf4j
@Profile("simple")
@RestController
public class ResourceController {

    @RequestMapping("/me")
    public Principal me(Principal principal) {
        log.debug(principal.toString());
        return principal;
    }
}