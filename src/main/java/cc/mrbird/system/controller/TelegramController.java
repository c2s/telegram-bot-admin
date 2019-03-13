package cc.mrbird.system.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.system.domain.TelegramMessage;
import cc.mrbird.system.service.TelegramMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TelegramController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TelegramMessageService telegramMessageService;

    @RequestMapping("telegram")
    @RequiresPermissions("telegram:message-list")
    public String index() {
        return "system/telegram/message";
    }

    @Log("获取用户信息")
    @RequestMapping("telegram/message")
    @RequiresPermissions("telegram:message-list")
    @ResponseBody
    public Map<String, Object> telegramMessageList(QueryRequest request, TelegramMessage telegramMessage) {
        return super.selectByPageNumSize(request, () -> this.telegramMessageService.findAllMessage(telegramMessage));
    }

}
