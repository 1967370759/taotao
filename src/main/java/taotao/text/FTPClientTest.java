package taotao.text;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FTPClientTest {
    public void testFTP() throws Exception {
        //连接ftp服务器
        FTPClient ftpClient=new FTPClient();
        ftpClient.connect("192.168.36.1",21);
        //登陆ftp服务器
        ftpClient.login("zzw","123");
        //读取本地文件
        FileInputStream inputStream=new FileInputStream(new File(
                "D:\\SteamLibrary\\steamapps\\workshop\\content\\431960\\1704283051\\preview.jpg"));
        //上传文件
        //指定上传目录
        ftpClient.changeWorkingDirectory("E:\\nginx-1.14.2 (1)\\taotaoFTP");
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        //第一个参数 文件在远程服务器的名称
        //第二个参数 文件流
        ftpClient.storeFile("hollo.jpg",inputStream);
        //退出登陆
        ftpClient.logout();
    }

    public static void main(String[] args) {
        try {
            new FTPClientTest().testFTP();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
