package com.play4fun.utils;

import cn.hutool.core.lang.Assert;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.Properties;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 从付款网关复制过来，后续将com.meituan.pay.operation.common.sftp.SFTPUtil替换掉
 *
 * @author zhengxiaoming on 2017/12/30.
 */
@Slf4j
public class SftpUtils {

    private static final int TIMEOUT = 10000;

    private static final JSch JSCH = new JSch();


    private SftpUtils() {

    }

    /**
     * 将对账文件推送到指定地址(Optional)
     */
    public static boolean pushBillFile(String sftpDir, String localDir, String localFile, String sftpIp, String sftpUser, String sftpPwd, String sftpPort) {
        Assert.notNull(sftpDir, "本地路径不能为空");
//        AssertUtil.notNull(sftpDir, "本地路径不能为空");
//        AssertUtil.notBlack(localFile, "文件名不能为空");
//        AssertUtil.notNull(sftpDir, "sftp目标文件目录不能为空");

        long startTime = System.currentTimeMillis();
        log.info("begin pushBillFile, localPath = {},localFile={}", localDir, localFile);

        //拷贝localFile到SFTP对应目录，并且备份到sftp的backup目录；
        try (SftpChannel channel = new SftpChannelBuilder()
                .setHost(sftpIp)
                .setPort(Integer.valueOf(sftpPort))
                .setUserName(sftpUser)
                .setPwd(sftpPwd)
                .build()) {

            localDir = localDir.endsWith(File.separator) ? localDir : localDir + File.separator;
            channel.putFile(localDir + localFile, sftpDir);
        } catch (IOException e) {
            log.info("SFTPUtils error:", e);
            return false;
        } finally {
            log.info("putFile : {} cost {}ms", localDir + localFile, System.currentTimeMillis() - startTime);
        }
        return true;
    }

    /**
     * 将对账文件推送到指定地址，当文件不存在时执行put操作
     *
     * @return 文件存在反馈false,
     */

    public static boolean pushBillFileNx(String sftpDir, String localDir, String localFile, String sftpIp, String sftpUser, String sftpPwd, String sftpPort) {

//        AssertUtil.notNull(localDir, "本地路径不能为空");
//        AssertUtil.notBlack(localFile, "文件名不能为空");
//        AssertUtil.notNull(sftpDir, "sftp目标文件目录不能为空");

        long startTime = System.currentTimeMillis();
        log.info("begin pushBillFileNx, localPath = {},localFile={}", localDir, localFile);

        //拷贝localFile到SFTP对应目录，并且备份到sftp的backup目录；
        try (SftpChannel channel = new SftpChannelBuilder()
                .setHost(sftpIp)
                .setPort(Integer.valueOf(sftpPort))
                .setUserName(sftpUser)
                .setPwd(sftpPwd)
                .build()) {

            localDir = localDir.endsWith(File.separator) ? localDir : localDir + File.separator;
            if (channel.exists(sftpDir, localFile)) {
                return false;
            }
            channel.putFile(localDir + localFile, sftpDir);
        } catch (IOException e) {
            log.info("SFTPUtils error:", e);
            return false;
        } finally {
            log.info("putFileNx : {} cost {}ms", localDir + localFile, System.currentTimeMillis() - startTime);
        }
        return true;
    }

    public static boolean getSftpFile(String remotePath, String localPath, String fileName, String sftpIp, String sftpUser, String sftpPwd, String sftpPort) {

//        AssertUtil.notNull(localPath, "本地路径不能为空");
//        AssertUtil.notBlack(fileName, "文件名不能为空");
//        AssertUtil.notNull(remotePath, "sftp目标文件目录不能为空");

        long startTime = System.currentTimeMillis();
        log.info("begin getFile, localPath = {},localFile={}", localPath, fileName);

        try (SftpChannel channel = new SftpChannelBuilder()
                .setHost(sftpIp)
                .setPort(Integer.valueOf(sftpPort))
                .setUserName(sftpUser)
                .setPwd(sftpPwd)
                .build()) {

            localPath = localPath.endsWith(File.separator) ? localPath : localPath + File.separator;
            remotePath = remotePath.endsWith(File.separator) ? remotePath : remotePath + File.separator;
            return channel.getFile(localPath + fileName, remotePath + fileName);
        } catch (IOException e) {
            log.info("SFTPUtils error:", e);
            return false;
        } finally {
            log.info("getFile : {} cost {}ms", remotePath + fileName, System.currentTimeMillis() - startTime);
        }
    }

    public static boolean getSftpFiles(String remotePath, String localPath, String[] fileNames, String sftpIp, String sftpUser, String sftpPwd, String sftpPort) {
//        AssertUtil.notNull(localPath, "本地路径不能为空");
//        AssertUtil.notNull(fileNames, "文件名不能为空");
//        AssertUtil.notNull(remotePath, "sftp目标文件目录不能为空");

        long startTime = System.currentTimeMillis();
        log.info("begin getSftpFiles, localPath = {},localFile={}", localPath, fileNames);

        try (SftpChannel channel = new SftpChannelBuilder()
                .setHost(sftpIp)
                .setPort(Integer.valueOf(sftpPort))
                .setUserName(sftpUser)
                .setPwd(sftpPwd)
                .build()) {

            String path = localPath.endsWith(File.separator) ? localPath : localPath + File.separator;
            String sftpPath = remotePath.endsWith(File.separator) ? remotePath : remotePath + File.separator;
            return Stream.of(fileNames).noneMatch(t -> getFile(channel, sftpPath + t, path + t));
        } catch (IOException e) {
            log.info("SFTPUtils error:", e);
            return false;
        } finally {
            log.info("getSftpFiles :remotePath = {}, fileName = {} {} cost {}ms", remotePath, fileNames, System.currentTimeMillis() - startTime);
        }
    }

    public static String[] lsSftpFileName(String remotePath, String fileName, String sftpIp, String sftpUser, String sftpPwd, String sftpPort) throws IOException {
        Assert.notNull(remotePath, "sftp目标文件目录不能为空");
        try (SftpChannel channel = new SftpChannelBuilder()
                .setHost(sftpIp)
                .setPort(Integer.valueOf(sftpPort))
                .setUserName(sftpUser)
                .setPwd(sftpPwd)
                .build()) {
            remotePath = remotePath.endsWith(File.separator) ? remotePath : remotePath + File.separator;
            return channel.ls(remotePath, fileName);
        } catch (IOException e) {
            throw e;
        }
    }

    public static void deleteSftpFile(String remotePath, String fileName, String sftpIp, String sftpUser, String sftpPwd, String sftpPort) {
        deleteSftpFile(remotePath, new String[]{fileName}, sftpIp, sftpUser, sftpPwd, sftpPort);
    }

    public static void deleteSftpFile(String remotePath, String[] filesName, String sftpIp, String sftpUser, String sftpPwd, String sftpPort) {
        Assert.notNull(filesName, "文件名不能为空");
        Assert.notNull(remotePath, "sftp目标文件目录不能为空");

        long startTime = System.currentTimeMillis();
        log.info("begin deleteSftpFile, remotePath = {},fileName={}", remotePath, filesName);

        try (SftpChannel channel = new SftpChannelBuilder()
                .setHost(sftpIp)
                .setPort(Integer.valueOf(sftpPort))
                .setUserName(sftpUser)
                .setPwd(sftpPwd)
                .build()) {

            remotePath = remotePath.endsWith(File.separator) ? remotePath : remotePath + File.separator;
            for (String fileName : filesName) {
                channel.rm(remotePath, fileName);
            }
        } catch (IOException e) {
            log.info("SFTPUtils error:", e);
        } finally {
            log.info("deleteSftpFile : remotePath = {},fileName={} cost {}ms", remotePath, filesName, System.currentTimeMillis() - startTime);
        }
    }

    /**
     * 获取sftp服务器上的文件大小
     * 当出现异常时返回-2，当文件不存在是返回-1
     *
     * @return
     */
    public static long getRemoteFileSize(String remotePath, String filename, String sftpIp, String sftpUser, String sftpPwd, String sftpPort) {
//        AssertUtil.notBlack(filename, "远程文件名称不能为空");

        log.info("begin getRemoteFileSize, fileName= {}", filename);
        try (SftpChannel channel = new SftpChannelBuilder()
                .setHost(sftpIp)
                .setPort(Integer.valueOf(sftpPort))
                .setUserName(sftpUser)
                .setPwd(sftpPwd)
                .build()) {
            remotePath = remotePath.endsWith(File.separator) ? remotePath : remotePath + File.separator;
            return channel.getRemoteFileSize(remotePath + filename);
        } catch (IOException e) {
            log.info("SFTPUtils error:", e);
            return -2;
        }
    }


    /**
     * 修改SFTP服务器文件名
     *
     * @param directory
     * @param oldName
     * @param newName
     * @throws SftpException
     */
    public boolean rename(String directory, String oldName, String newName, String sftpIp, String sftpUser, String sftpPwd, String sftpPort) {

        Assert.notNull(directory, "远程文件目录不能为空");
//        AssertUtil.notBlack(oldName, "远程文件名称不能为空");
//        AssertUtil.notBlack(newName, "远程文件名称不能为空");

        log.info("begin rename, fileName= {}", oldName);
        try (SftpChannel channel = new SftpChannelBuilder()
                .setHost(sftpIp)
                .setPort(Integer.valueOf(sftpPort))
                .setUserName(sftpUser)
                .setPwd(sftpPwd)
                .build()) {

            channel.rename(directory, oldName, newName);
        } catch (IOException e) {
            log.info("SFTPUtils rename error:", e);
            return false;
        }
        return true;

    }

    private static boolean getFile(SftpChannel channel, String remoteFile, String localFile) {
        try {
            return !channel.getFile(localFile, remoteFile);
        } catch (IOException e) {
            log.info("SFTPUtils error:", e);
            return true;
        }
    }


    @Slf4j
    private static class SftpChannelBuilder {

        private String host;
        private Integer port = 2222;
        private String userName;
        private String pwd;
        private int timeOut = TIMEOUT;


        public SftpChannelBuilder setHost(String host) {
            this.host = host;
            return this;
        }

        public SftpChannelBuilder setPort(Integer port) {
            if (port != null) {
                this.port = port;
            }
            return this;
        }

        public SftpChannelBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public SftpChannelBuilder setPwd(String pwd) {
            this.pwd = pwd;
            return this;
        }

        public SftpChannelBuilder setTimeOut(Integer timeOut) {
            if (timeOut == null) {
                this.timeOut = TIMEOUT;
            } else {
                this.timeOut = timeOut;
            }
            return this;
        }

        public SftpChannel build() {
//            AssertUtil.notBlack(host, "host is null");
//            AssertUtil.notBlack(userName, "userName is null");
            SftpChannel channel = new SftpChannel();
            try {
                channel.init(host, port, userName, pwd, timeOut);
            } catch (IOException e) {
                log.info("build channel error", e);
            }
            return channel;
        }
    }


    private static class SftpChannel implements Closeable {

        private static final Logger LOGGER = LoggerFactory.getLogger(SftpChannel.class);

        private Session session;

        private ChannelSftp channel;

        public void init(String host, Integer port, String userName, String pwd, int timeout) throws IOException {
            try {
                // 根据用户名，主机ip，端口获取一个Session对象
                session = JSCH.getSession(userName, host, port);
                // 设置密码
                if (pwd != null) {
                    session.setPassword(pwd);
                }
                Properties config = new Properties();
                config.put("StrictHostKeyChecking", "no");
                // 为Session对象设置properties
                session.setConfig(config);
                // 设置timeout时间
                session.setTimeout(timeout);
                // 通过Session建立链接
                session.connect();
                // 打开SFTP通道
                channel = (ChannelSftp) session.openChannel("sftp");
                // 建立SFTP通道的连接
                channel.connect();
            } catch (JSchException e) {
                throw new IOException(e);
            }
        }

        void putFile(String srcFileNameWithPath, String remotePath) throws IOException {
            try {
                long fileSize = this.getLocalFileSize(srcFileNameWithPath);
                channel.put(srcFileNameWithPath, remotePath, new FileProgressMonitor(fileSize), ChannelSftp.OVERWRITE);
            } catch (SftpException e) {
                throw new IOException(e);
            }
        }

        boolean getFile(String localFile, String remoteFile) throws IOException {
            try {
                long fileSize = this.getRemoteFileSize(remoteFile);
                channel.get(remoteFile, localFile, new FileProgressMonitor(fileSize));
            } catch (SftpException e) {
                if (!e.getMessage().contains("No such file")) {
                    LOGGER.info("findRemoteFile error：", e);
                    throw new IOException(e);
                }
                LOGGER.info(e.getMessage());
                return false;
            }
            return true;
        }

        void rename(String remotePath, String oldName, String newName) throws IOException {
            try {
                channel.cd(remotePath);
                channel.rename(oldName, newName);
            } catch (SftpException e) {
                throw new IOException(e);
            }

        }

        @SuppressWarnings("unchecked")
        boolean exists(String remotePath, String fileName) throws IOException {
            try {
                Vector<ChannelSftp.LsEntry> entries = channel.ls(remotePath + fileName);
                Optional<ChannelSftp.LsEntry> optional = entries.stream()
                        .filter(t -> t.getFilename().equals(fileName))
                        .findAny();
                return optional.isPresent();

            } catch (SftpException e) {
                //文件不存在报：com.jcraft.jsch.SftpException: No such file,除此之外的其它错误打印出来
                if (!e.getMessage().contains("No such file")) {
                    LOGGER.info("findRemoteFile error：", e);
                    throw new IOException(e);
                }
                return false;
            }
        }

        @SuppressWarnings("unchecked")
        String[] ls(String remotePath, String fileName) throws IOException {
            try {
                Vector<ChannelSftp.LsEntry> entries = channel.ls(remotePath + fileName);
                /*if (CollectionUtils.isEmpty(entries)) {
                    return new String[0];
                }*/
                return entries.parallelStream().map(ChannelSftp.LsEntry::getFilename).collect(Collectors.toList()).toArray(new String[entries.size()]);
            } catch (SftpException e) {
                if (!e.getMessage().contains("No such file")) {
                    LOGGER.info("findRemoteFile error：", e);
                    throw new IOException(e);
                }
                return new String[0];
            }
        }

        void rm(String remotePath, String fileName) throws IOException {
            try {
                channel.cd(remotePath);
                channel.rm(fileName);
            } catch (SftpException e) {
                if (!e.getMessage().contains("No such file")) {
                    throw new IOException(e);
                }
                LOGGER.info(e.getMessage());
            }
        }

        long getRemoteFileSize(String filename) throws IOException {
            long fileSize;
            try {
                fileSize = channel.stat(filename).getSize();
            } catch (SftpException e) {
                if (!e.getMessage().contains("No such file")) {
                    LOGGER.info("getRemoteFileSize error：", e);
                    throw new IOException(e);
                } else {
                    LOGGER.info(e.getMessage());
                    fileSize = -1;
                }
            }
            return fileSize;
        }


        @Override
        public void close() throws IOException {
            try {
                if (channel != null && !channel.isClosed()) {
                    channel.disconnect();
                }
                if (session != null) {
                    session.disconnect();
                }
            } catch (Exception e) {
                LOGGER.error("SftpChannel.close error", e);
            }
        }

        private long getLocalFileSize(String fileName) {
            File f = new File(fileName);
            return f.length();
        }
    }


    private static class FileProgressMonitor implements SftpProgressMonitor {

        private static final Logger LOGGER = LoggerFactory.getLogger(FileProgressMonitor.class);
        /**
         * 记录已传输的数据总大小
         */
        private long transfered;
        /**
         * 记录文件总大小
         */
        private long fileSize;

        FileProgressMonitor(long fileSize) {
            this.fileSize = fileSize;
        }

        @Override
        public void init(int i, String s, String s1, long l) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("sftp process start.......");
            }
        }

        @Override
        public boolean count(long l) {
            transfered += l;
            if (fileSize != 0) {
                if (LOGGER.isDebugEnabled()) {
                    double d = ((double) transfered * 100) / (double) fileSize;
                    DecimalFormat df = new DecimalFormat("#.##");
                    LOGGER.debug("Sending progress message: {}%", df.format(d));
                }
            } else {
                LOGGER.info("Sending progress message: {}", transfered);
            }
            return transfered != fileSize;
        }

        @Override
        public void end() {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("sftp process end.......");
            }
        }
    }
}