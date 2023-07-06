package com.play4fun.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class CleanQueryCacheJob {
    private static final String CACHE_FILE_PATH = "/users/leoyao/efs/cdp/data";
    private static final String DATE_PATTERN = "yyyyMMdd";

    public static void main(String[] args) throws ParseException {
        System.out.println(System.getProperty("user.dir"));
        cleanExpiredQueryCacheTask();
    }


//    @Crane("com.sankuai.sjstcdp.rms.ups.clean-expired-query-cache-task")
    public static void cleanExpiredQueryCacheTask() {
        try {
            //queryClient cache过期时间
            int queryCacheExpiredTime = 7;

            //删除文件截止时间
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -queryCacheExpiredTime);
            Date deleteEndTime = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

            //追溯时间
            String queryCacheExpireStartTime = "20220101";
            Date deleteStartTime = sdf.parse(queryCacheExpireStartTime);

            File dataFolder = new File(CACHE_FILE_PATH);
            File[] dataFiles = dataFolder.listFiles();
            if (dataFiles == null) {
                log.info(CACHE_FILE_PATH + "路径下无子文件");
                return;
            }
            //删除过期文件夹
            for (File dataFile : dataFiles) {
//                if (dataFile.isDirectory()) {
//                    File[] files = dataFile.listFiles();
//                    if (files != null) {
                        for (File file : dataFiles) {
                            if (file.isDirectory()) {
                                String folderName = file.getName();
                                Date folderTime = sdf.parse(folderName);
                                if (folderTime.compareTo(deleteStartTime) >= 0 && folderTime.compareTo(deleteEndTime) <= 0) {
                                    deleteFolder(file);
                                }
                            }
                        }
//                    }
//                }
            }
        } catch (ParseException e) {
            log.error("sdf.parse failed", e);
        } catch (Exception e) {
            log.error("CacheCleanJob failed", e);
            throw e;
        }
    }

    private static void deleteFolder(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteFolder(file);
                }
            }
        }
        log.info("删除文件: " + folder.getAbsolutePath());
        folder.delete();
    }
}
