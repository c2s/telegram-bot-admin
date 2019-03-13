package cc.mrbird.system.service;

import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.TelegramMessage;

import java.util.List;

public interface TelegramMessageService extends IService<TelegramMessage> {

    TelegramMessage findByName(String title);

    List<TelegramMessage> findAllMessage(TelegramMessage telegramMessage);

}
