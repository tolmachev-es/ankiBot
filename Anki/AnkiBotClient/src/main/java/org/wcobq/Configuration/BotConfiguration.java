package org.wcobq.Configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class BotConfiguration {
    @Value("${bot.name}")
    private String name;
    @Value("${bot.token}")
    private String token;
}
