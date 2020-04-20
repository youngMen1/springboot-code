package com.seal.protobuf.server;

import com.google.protobuf.ByteString;
import com.seal.protobuf.constants.Constants;
import com.seal.protobuf.msg.PersonBean;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/18 18:20
 * 案例一
 **/
public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(Constants.PORT);
        System.out.println("server started...");
        Socket socket = ss.accept();
        System.out.println("a client connected!");
        //从输入流中解析出Person对象
        PersonBean.Person person = PersonBean.Person.parseFrom(ByteString.readFrom(socket.getInputStream()));
        if (person != null) {
            System.out.println("server received data:\n" + person.toString());
        }
    }
}
