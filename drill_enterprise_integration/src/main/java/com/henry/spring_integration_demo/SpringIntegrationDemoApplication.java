package com.henry.spring_integration_demo;

import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.file.Files;
import org.springframework.integration.dsl.mail.Mail;
import org.springframework.integration.feed.inbound.FeedEntryMessageSource;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.scheduling.PollerMetadata;

import java.io.File;
import java.io.IOException;

import static java.lang.System.getProperty;

@SpringBootApplication
public class SpringIntegrationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationDemoApplication.class, args);
    }


    /* 读取流程 👇 */
    @Value("https://spring.io/blog.atom") // 1 获取到要处理的网络资源  手段:@Value(<web_resources>)注解
    Resource resource;

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() { // 2 定义 以轮询的方式来获取消息    手段：Pollers + Fluent API
        return Pollers.fixedRate(500).get();
    }

    //3 FeedEntryMessageSource类 其实是一种类型Message EndPoint - 用于具体处理message
    // 具体来说，是Channel Adapter类型的 EndPoint
    // 方法的作用： 使用feed的入站通道适配器 来 作为数据输入
    @Bean
    public FeedEntryMessageSource feedMessageSource() throws IOException {
        FeedEntryMessageSource messageSource = new FeedEntryMessageSource(resource.getURL(), "news");
        return messageSource;
    }


    @Bean
    public IntegrationFlow myFlow() throws IOException {
        return IntegrationFlows.from(feedMessageSource()) //4 读取流程开始于 from方法
                .<SyndEntry, String> route( //5 选择路由 - 手段：route()方法
                        payload -> payload.getCategories().get(0).getName(),// 消息体payload的类型是 SyndEntry  注：这里有判断逻辑(Java8的语法) 但我完全看不出来
                        mapping -> mapping.channelMapping("releases", "releasesChannel") //6 由不同的分类，转向不同的消息通道 映射关系在代码中
                                .channelMapping("engineering", "engineeringChannel")
                                .channelMapping("news", "newsChannel")
                        )
                .get(); // 7 获取到IntegrationFlow实体(以将之配置成为一个Bean)    手段：get()方法
    }

    /* 读取流程 👆 */

    /* releases流程 👇*/
    @Bean
    public IntegrationFlow releasesFlow() {
        // 从消息通道 releasesChannel中开始获取数据
        return IntegrationFlows.from(MessageChannels.queue("releasesChannel", 10))
                .<SyndEntry, String> transform( // 进行数据转换 - 手段：transform()方法   payload的类型为SyndEntry，这里把它转化成为有格式的String类型
                        payload -> "《" + payload.getTitle() + "》 " + payload.getLink() + getProperty("line.separator"))
                .handle(Files.outboundAdapter(new File("e:/springblog")) // 处理file的出站适配器  - 手段：handle()方法
                        .fileExistsMode(FileExistsMode.APPEND) // Files 是一个适配器； 作用：用来够在文件输出；    特征：1 由SI Java DSL提供的； 2 由Fluent API使用；
                        .charset("UTF-8") //5
                        .fileNameGenerator(message -> "releases.txt")
                        .get())
                .get();
    }
    /* releases流程 👆*/

    /* engineering流程 - 与releases流程基本一致 👇 */
    @Bean
    public IntegrationFlow engineeringFlow() {
        return IntegrationFlows.from(MessageChannels.queue("engineeringChannel", 10))
                .<SyndEntry, String> transform(
                        payload -> "《" + payload.getTitle() + "》 " + payload.getLink() + getProperty("line.separator"))
                .handle(Files.outboundAdapter(new File("E:/develop/tryout_springBoot/drill_enterprise_integration/src/main/resources/springblog"))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .charset("UTF-8")
                        .fileNameGenerator(message -> "engineering.txt")
                        .get())
                .get();
    }
    /* engineering流程 👆 */

    /* news流程 👇 */
    @Bean
    public IntegrationFlow newsFlow() {
        return IntegrationFlows.from(MessageChannels.queue("newsChannel", 10))
                .<SyndEntry, String> transform(
                        payload -> "《" + payload.getTitle() + "》 " + payload.getLink() + getProperty("line.separator"))
                .enrichHeaders( // 增加消息头的信息 - 手段：enrichHeaders()
                        Mail.headers() // 构造邮件的相关信息 - 手段：使用DSL提供的Mail类型的header()方法
                                .subject("来自Spring的新闻")
                                .to("wisely-man@126.com")
                                .from("wisely-man@126.com"))
                .handle(Mail.outboundAdapter("smtp.126.com") // 定义邮件发送的出站适配器 - 手段：handle()方法    参数构造 - 使用DSL提供的Mail类的outboundAdapter()方法
                        .port(25)
                        .protocol("smtp")
                        .credentials("2291972433@qq.com", "LH20130610040112") // 邮箱认证 使用此邮箱向自己发邮件?
                        .javaMailProperties(p -> p.put("mail.debug", "false")), e -> e.id("smtpOut"))
                .get();
    }
    /* news流程 👆 */
}
