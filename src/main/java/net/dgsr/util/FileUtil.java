package net.dgsr.util;

import net.dgsr.comment.ServiceResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FileUtil {

    /**
     * 文件下载
     * @param requestUrl 请求路径
     * @param filePath   目录
     * @param fileName   文件id
     * @return
     */
    public static ServiceResponse<?> downloadTempFileByMediaId(String requestUrl, String filePath, String fileName) {

        StringBuffer buffer = new StringBuffer();

        try {
            //创建连接地址
            URL url = new URL(requestUrl);

            //创建连接对象
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //设置请求参数
            conn.setDoInput(true);  //设置是否开启输入输出
            conn.setRequestMethod("GET");  //提交模式

            // 从请求头中获取内容类型  通过split分解后获取后缀
            String suffix = conn.getHeaderField("Content-Type").split("/")[1];

            // 从请求头中获取内容大小
            int length = Integer.parseInt(conn.getHeaderField("Content-Length"));

            //拼接文件名称
            fileName = fileName + "." + suffix;

            File fileDir = new File(filePath);

            // 如果目标文件夹不存在 则创建
            if (!fileDir.exists()) {
                fileDir.setWritable(true);  //允许写访问权限
                fileDir.mkdirs();           //创建多级目录
            }

            //构造输入流，指向本地文件
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());

            //构造输出流，指向本地文件
            FileOutputStream fos = new FileOutputStream(new File(filePath + fileName));

            byte[] buf = new byte[length];
            int size = 0;

            //判断是否有数据读入
            while ((size = bis.read(buf)) != -1) {
                //将数据写入文件
                fos.write(buf, 0, size);
            }

            //关流
            fos.close();
            bis.close();

            //断开连接
            conn.disconnect();

        } catch (Exception e) {
            filePath = null;
            e.printStackTrace();
        }

        //如果发生了异常 filePath定义为null 返回code和自定义提示
        if (filePath == null) {
            return ServiceResponse.createByErrorMessage("下载文件失败");
        }

        //获取文件的存放位置
        filePath = filePath.substring(filePath.lastIndexOf("upload") - 1);

        //返回文件的存放位置
        return ServiceResponse.createBySuccessMessage(filePath.replaceAll("\\\\", "/") + fileName);
    }


    /**
     * @param requestUrl 微信上传临时素材的接口url
     * @param file       要上传的文件 上传成功后，微信服务器返回的消息
     */
    public static String uploadWxTempFile(String requestUrl, MultipartFile file) {

        StringBuffer buffer = new StringBuffer();

        try {
            // 1.建立连接
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection(); // 打开链接

            // 1.1输入输出设置
            httpUrlConn.setDoInput(true);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setUseCaches(false); // post方式不能使用缓存
            // 1.2设置请求头信息
            httpUrlConn.setRequestProperty("Connection", "Keep-Alive");
            httpUrlConn.setRequestProperty("Charset", "UTF-8");
            // 1.3设置边界
            String BOUNDARY = "----------" + System.currentTimeMillis();
            httpUrlConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

            // 请求正文信息
            // 第一部分：
            // 2.将文件头输出到微信服务器
            StringBuilder sb = new StringBuilder();
            sb.append("--"); // 必须多两道线
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"media\";filelength=\"" + file.getSize() + "\";filename=\"" + file.getOriginalFilename() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] head = sb.toString().getBytes("utf-8");
            // 获得输出流
            OutputStream outputStream = new DataOutputStream(httpUrlConn.getOutputStream());
            // 将表头写入输出流中：输出表头
            outputStream.write(head);

            // 3.将文件正文部分输出到微信服务器
            // 把文件以流文件的方式 写入到微信服务器中
            DataInputStream in = new DataInputStream(file.getInputStream());
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                outputStream.write(bufferOut, 0, bytes);
            }
            in.close();
            // 4.将结尾部分输出到微信服务器
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
            outputStream.write(foot);
            outputStream.flush();
            outputStream.close();

            // 5.将微信服务器返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();

        } catch (IOException e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        return buffer.toString();
    }



}
