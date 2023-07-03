package com.play4fun.utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CleanQueryCacheJob {
    private static final String CACHE_FILE_PATH_PREFIX = "/users/leoyao/efs/cdp/data";
    private static final String DATE_PATTERN = "yyyyMMdd";

    public static void main(String[] args) throws ParseException {
        System.out.println(System.getProperty("user.dir"));
        cleanExpiredQueryCacheTask("111222333");
    }

//    @Crane("com.sankuai.sjstcdp.rms.ups.clean-expired-query-cache-task")
    public static void cleanExpiredQueryCacheTask(String tenantId) throws ParseException {
        //queryClient cache过期时间
        int queryCacheExpiredTime = 30;

        //当前租户文件路径
        String filePath = CACHE_FILE_PATH_PREFIX + File.separator + tenantId;

        //删除文件截止时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -queryCacheExpiredTime); // 将日期减去指定天
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        String deleteTime = sdf.format(date);

        //追溯时间
        String queryCacheExpireStartTime = "20220101";

        Date deleteEndTime = sdf.parse(deleteTime);
        Date deleteStartTime = sdf.parse(queryCacheExpireStartTime);

        //找出所有满足条件的文件夹
        File userFolder = new File(filePath);
        if (userFolder.isDirectory()) {
            File[] files = userFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        String folderName = file.getName();
                        Date folderTime = sdf.parse(folderName);
                        if (folderTime.compareTo(deleteStartTime) >= 0 && folderTime.compareTo(deleteEndTime) <= 0) {
                            deleteFolder(file);
                        }
                    }
                }
            }
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
        folder.delete();
    }
}
