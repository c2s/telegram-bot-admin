package cc.mrbird.system.service.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.system.domain.TelegramMessage;
import cc.mrbird.system.service.TelegramMessageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;


@Service("telegramService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TelegreamMessageServiceImpl extends BaseService<TelegramMessage> implements TelegramMessageService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public TelegramMessage findByName(String title) {
        Example example = new Example(TelegramMessage.class);
        example.createCriteria().andCondition("lower(title)=", title.toLowerCase());
        List<TelegramMessage> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<TelegramMessage> findAllMessage(TelegramMessage telegramMessage) {
        try {
            Example example = new Example(TelegramMessage.class);
            if (StringUtils.isNotBlank(telegramMessage.getTitle())) {
                example.createCriteria().andCondition("title=", telegramMessage.getTitle());
            }
            example.setOrderByClause("created");
            return this.selectByExample(example);
        } catch (Exception e) {
            log.error("获取角色信息失败", e);
            return new ArrayList<>();
        }
    }
}
