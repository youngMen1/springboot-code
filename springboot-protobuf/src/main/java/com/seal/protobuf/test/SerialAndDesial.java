package com.seal.protobuf.test;

import com.seal.protobuf.msg.ProtoDemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * @author fengzhiqiang
 * @date-time 2020/4/20 9:49
 * 案例三
 * 编写序列化和反序列化代码如下
 **/
public class SerialAndDesial {
    public static void main(String[] args) {
        ProtoDemo.demo.Builder b = ProtoDemo.demo.newBuilder();
        b.setEmail("dave_peter@foxmail.com");
        b.setId(1);
        b.setName("lsh");

        ProtoDemo.demo protoDemo = b.build();

        FileOutputStream output;
        try {
            //write
            output = new FileOutputStream("proto.ser");
            protoDemo.writeTo(output);
            output.close();

            //read
            ProtoDemo.demo protoDemo2 = ProtoDemo.demo.parseFrom(new FileInputStream("proto.ser"));
            System.out.println(protoDemo2);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
