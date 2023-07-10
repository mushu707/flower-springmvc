package com.ssm.util.time;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Time {
  /**
   * 获得字符串格式的时间
   * @return
   */
  public static String getTimeByString(){
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar calendar = Calendar.getInstance();
    return df.format(calendar.getTime());
  }

  /**
   * 获取时间戳格式的时间
   * @return
   */
  public static Timestamp getTimeByDate(){
    Date date = new Date();
    return new Timestamp(date.getTime());
  }
}
