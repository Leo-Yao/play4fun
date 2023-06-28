package com.play4fun.utils;

import cn.hutool.core.io.IoUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class RemoteFileUtil {

    public File getRemoteUrl2File(String path, String remoteUrl) throws IOException {
        URL url = new URL(remoteUrl);
        File file = new File(path, url.getPath());
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            IoUtil.copy(url.openStream(), new FileOutputStream(file));
        }
        return file;
    }
}
