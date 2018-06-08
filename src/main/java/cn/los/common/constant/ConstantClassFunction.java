package cn.los.common.constant;

//但是通过函数来获取常量 和constantClassField 类似
public class ConstantClassFunction {
    private static final String SUNDAY = "SUNDAY";
    private static final String MONDAY = "MONDAY";
    private static final String TUESDAY = "TUESDAY";
    private static final String WEDNESDAY = "WEDNESDAY";
    private static final String THURSDAY = "THURSDAY";
    private static final String FRIDAY = "FRIDAY";
    private static final String SATURDAY = "SATURDAY";

    public static String getSunday() {
        return SUNDAY;
    }

    public static String getMonday() {
        return MONDAY;
    }

    public static String getTuesday() {
        return TUESDAY;
    }

    public static String getWednesday() {
        return WEDNESDAY;
    }

    public static String getThursday() {
        return THURSDAY;
    }

    public static String getFriday() {
        return FRIDAY;
    }

    public static String getSaturday() {
        return SATURDAY;
    }

}
