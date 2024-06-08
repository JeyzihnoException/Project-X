//package com.social.social.config;
//
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> containerCustomizer() {
//        return factory -> {
//            if (factory instanceof TomcatServletWebServerFactory) {
//                TomcatServletWebServerFactory containerFactory = (TomcatServletWebServerFactory) factory;
//                containerFactory.addConnectorCustomizers(connector -> {
//                    connector.setScheme("https");
//                    connector.setSecure(true);
//                    connector.setProperty("sslProtocol", "TLS");
//                    connector.setProperty("keystoreFile", "server.jks"); // Путь к server.jks
//                    connector.setProperty("keystorePass", "password");
//                    connector.setProperty("keystoreType", "JKS");
//                    connector.setProperty("clientAuth", "want"); // Или "need", если требуется строгая аутентификация
//                    connector.setProperty("truststoreFile", "server.jks"); // Путь к server.jks (доверенные сертификаты)
//                    connector.setProperty("truststorePass", "password");
//                    connector.setProperty("truststoreType", "JKS");
//                });
//            }
//        };
//    }
//}
