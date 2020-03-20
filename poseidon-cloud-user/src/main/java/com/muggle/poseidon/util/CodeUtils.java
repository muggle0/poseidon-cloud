//package com.muggle.poseidon.util;
//
//import com.muggle.code.SimpleCodeTemplate;
//import com.muggle.code.TableMessage;
//
//import java.util.Arrays;
//
///**
// * @program: poseidon-cloud-user
// * @description:
// * @author: muggle
// * @create: 2019-12-06
// **/
//
//public class CodeUtils {
//
//    public static void main(String[] args) {
//        TableMessage tableMessage = new TableMessage();
//        tableMessage.setUsername("root");
//        tableMessage.setSwagger(false);
//        tableMessage.setTableName(Arrays.asList("user_sign","user_role","user_info","user_authority","system_menu"));
//
//        tableMessage.setAuthor("muggle");
//        tableMessage.setBasePack("com.muggle.poseidonbase");
//        tableMessage.setDriver("com.mysql.jdbc.Driver");
//        tableMessage.setJdbcUrl("jdbc:mysql://49.233.148.110/poseidon_cloud_a?useUnicode=true&characterEncoding=utf8");
//        tableMessage.setModule("poseidon");
//        tableMessage.setPassword("root");
//        tableMessage.setSwagger(true);
//        SimpleCodeTemplate simpleCodeTemplate = new SimpleCodeTemplate(tableMessage);
//        simpleCodeTemplate.createCode();
//    }
//}
