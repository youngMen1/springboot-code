package com.seal.protobuf.client;

import com.seal.protobuf.constants.Constants;
import com.seal.protobuf.msg.PersonBean;

import java.io.OutputStream;
import java.net.Socket;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/20 9:39
 * 案例一
 **/
public class Client {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", Constants.PORT);
        //构造一个Person对象
        PersonBean.Person person = PersonBean.Person.newBuilder().setName("zhangsan")
                .setAge(20).setGender("male").build();
        OutputStream os = socket.getOutputStream();
        //将Person对象写到输出流中
        os.write(person.toByteArray());
        os.flush();
        //这里注意一定要关闭流，否则服务端会报错
        os.close();
        System.out.println("client send person");
    }

}
