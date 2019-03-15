package cc.mrbird.system.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.system.domain.TelegramMessage;
import cc.mrbird.system.service.TelegramMessageService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
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

    @Log("发送消息")
    @RequestMapping("telegram/send-message")
    @RequiresPermissions("telegram:message-list")
    @ResponseBody
    public Boolean sendTelegramMessage(QueryRequest request, TelegramMessage telegramMessage) {
        TelegramBot bot = new TelegramBot("725048263:AAGCrp94qJmDNRzee7f4dJcHl6RkbIiVSq0");

        // Send messages

        bot.execute(new SendMessage("-230360657", "重要警示：\n" +
                "KuCoin客服和管理员不会主动向用户发起私聊索取账户相关信息和发布虚拟活动信息，如遇此类诈骗请立即@管理员或客服举报，并参阅KuCoin安全提示：https://bit.ly/2U9eVc6\n" +
                "客服: @Michelle_KuCoin_Support @KuCoin_Support_Emilia\n" +
                "管理员：@Panda_Lee_KuCoin_43 @Larry_He @Dorischencd @kc_kent\n" +
                "\n" +
                "KuCoin官方提供7X24小时全天候的贴心客服服务，用户如遇忘记密码、忘记安全问题等问题可通过以下路径快速寻求客服服务：\n" +
                "【手机App-我的-帮助中心】\n" +
                "【网页端-顶部-帮助中心】\n" +
                "【快速提交工单: https://bit.ly/2zwGR1a】\n" +
                "\n" +
                "如果无法正常访问KuCoin国际站点: https://www.kucoin.com，可直接访问KuCoin官方为大陆用户提供的中文站点: https://www.kcs.top 体验超流畅的登录、交易体验。\n" +
                "\n" +
                "KuCoin官方APP最新下载地址：\n" +
                "【iOS: https://www.kucoin.com/download/ios】\n" +
                "【安卓: https://www.kucoin.com/download/android】\n" +
                "\n" +
                "本群的建立是为了给大家提供一个自由集中、有价值的讨论空间，请大家保持平常心，怀揣正能量，尊重你我他。群内禁止传播广告、禁止恶意攻击、不涉政、不涉黄、不传谣。"));

        return true;
    }

}
