package taotao.Util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;

/**
 * ftp上传下载工具类
 */
public class FtpUtil {

    /**
     * Description: 向FTP服务器上传文件
     *
     * @param host     FTP服务器hostname
     * @param port     FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param basePath FTP服务器基础目录
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input    输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String host, int port, String username, String password, String basePath,
                                     String filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();

        try {
            int reply;
//            ftp.connect(host, port);// 连接FTP服务器
//            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
//            ftp.login(username,password);// 登录
            ftp=getFTPClient(host,username,password,port);
            reply = ftp.getReplyCode();
            System.out.println(reply);
            if (!FTPReply.isPositiveCompletion(reply)){
                ftp.disconnect();
                return result;
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(basePath+filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "\\" + dir;

                   if (!ftp.changeWorkingDirectory(tempPath)) {
                       //因为用以下方法不能创建文件夹，所以用这个方法创建
                       File file=new File(tempPath);
                       if (!file.exists()){
                           result=  file.mkdir();
                       }else {System.out.println(tempPath);
                           ftp.changeWorkingDirectory(tempPath);
                       }
//                        if (!ftp.makeDirectory(tempPath)) {
//                            System.out.printf(String.valueOf(result));
//                            return result;
//                        } else {
//
//                        }
                   }


                }

            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            if (!ftp.storeFile(filename, input)) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * Description: 从FTP服务器下载文件
     *
     * @param host       FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return
     */
    public static boolean downloadFile(String host, int port, String username, String password, String remotePath,
                                       String fileName, String localPath) {
        boolean result = false;
        FTPClient ftp = new FTPClient();

        try {
            int reply;
//            ftp.connect(host, port);
//            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
////            ftp.login(username, password);// 登录
            ftp=getFTPClient(host,username,password,port);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }
            ftp.logout();
            result = true;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (Exception ioe) {
                }
            }
        }
        return result;
    }

    public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                         String ftpPassword, int ftpPort) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                System.out.println("FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }

    /**
     * Description: 向FTP服务器上传文件
     * @param ftpHost FTP服务器hostname
     * @param ftpUserName 账号
     * @param ftpPassword 密码
     * @param ftpPort 端口
     * @param ftpPath  FTP服务器中文件所在路径 格式： ftptest/aa
     * @param fileName ftp文件名称
     * @param input 文件流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile1(String ftpHost, String ftpUserName,
                                     String ftpPassword, int ftpPort, String ftpPath,
                                     String fileName,InputStream input) {
        boolean success = false;
        FTPClient ftpClient = null;
        try {
            int reply;
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            reply = ftpClient.getReplyCode();
            System.out.println(reply);
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return success;
            }
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);

            ftpClient.storeFile(fileName, input);

            input.close();
            ftpClient.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }




    public static void main(String[] args) {
        try {
            FileInputStream in = new FileInputStream(new  File("D:\\SteamLibrary\\steamapps\\workshop\\content\\431960\\1704283051\\preview.jpg"));
            boolean flag = uploadFile("192.168.36.1", 21, "zzw", "123", "E:\\nginx-1.14.2 (1)\\taotaoFTP",
                    "/2019/2/13", "gaigeming.jpg", in);
            System.out.printf(String.valueOf(flag));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
   //     getFTPClient("192.168.36.1","zzw","123",21);

//        try {
//            FileInputStream in = new FileInputStream(new  File("D:\\SteamLibrary\\steamapps\\workshop\\content\\431960\\1704283051\\preview.jpg"));
//            boolean flag = uploadFile1("192.168.36.1",  "zzw", "123", 21,"E:\\nginx-1.14.2 (1)\\taotaoFTP",
//                    "gaigeming.jpg", in);
//            System.out.printf(String.valueOf(flag));
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}