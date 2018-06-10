package cn.los;

import java.util.Properties;

import cn.los.common.constant.Color;
import cn.los.common.constant.ConstantClassField;
import cn.los.common.constant.ConstantClassFunction;
import cn.los.common.constant.ConstantEnum;
import cn.los.common.constant.ConstantInterface;

public class TestConstant {

    static final String day = "saturday";

    public static void main(String[] args) {

        // 输出某一枚举的值
        System.out.println(Color.RED.getName());
        System.out.println(Color.RED.getIndex());
        // 遍历所有的枚举
        for (Color color : Color.values()) {
            System.out.println(color + "  name: " + color.getName() + "  index: " + color.getIndex());
        }

        System.out.println("是不是 saturday");
        System.out.println("接口" + day.equalsIgnoreCase(ConstantInterface.SATURDAY));
        System.out.println("枚举" + day.equalsIgnoreCase(ConstantEnum.SATURDAY.name()));
        System.out.println("枚举值" + ConstantEnum.SATURDAY.ordinal());
        System.out.println("字段" + day.equalsIgnoreCase(ConstantClassField.SATURDAY));
        System.out.println("方法" + day.equalsIgnoreCase(ConstantClassFunction.getSaturday()));

        Properties props = System.getProperties(); // 系统属性
        // 常用换行符, 路径分隔符,文件分隔符
        // 在unix系统中是"/"
        System.out.println("文件分隔符:" + props.getProperty("file.separator"));
        // 在unix系统中是":"
        System.out.println("路径分隔符:" + props.getProperty("path.separator"));
        // 在unix系统中是"/n"
        System.out.println("行分隔符:" + props.getProperty("line.separator"));
        // 不常用属性
        System.out.println("Java的运行环境版本:" + props.getProperty("java.version"));
        System.out.println("Java的运行环境供应商:" + props.getProperty("java.vendor"));
        System.out.println("Java供应商的URL:" + props.getProperty("java.vendor.url"));
        System.out.println("Java的安装路径:" + props.getProperty("java.home"));
        System.out.println("Java的虚拟机规范版本:" + props.getProperty("java.vm.specification.version"));
        System.out.println("Java的虚拟机规范供应商:" + props.getProperty("java.vm.specification.vendor"));
        System.out.println("Java的虚拟机规范名称:" + props.getProperty("java.vm.specification.name"));
        System.out.println("Java的虚拟机实现版本:" + props.getProperty("java.vm.version"));
        System.out.println("Java的虚拟机实现供应商:" + props.getProperty("java.vm.vendor"));
        System.out.println("Java的虚拟机实现名称:" + props.getProperty("java.vm.name"));
        System.out.println("Java运行时环境规范版本:" + props.getProperty("java.specification.version"));
        System.out.println("Java运行时环境规范供应商:" + props.getProperty("java.specification.vender"));
        System.out.println("Java运行时环境规范名称:" + props.getProperty("java.specification.name"));
        System.out.println("Java的类格式版本号:" + props.getProperty("java.class.version"));
        System.out.println("Java的类路径:" + props.getProperty("java.class.path"));
        System.out.println("加载库时搜索的路径列表:" + props.getProperty("java.library.path"));
        System.out.println("默认的临时文件路径:" + props.getProperty("java.io.tmpdir"));
        System.out.println("一个或多个扩展目录的路径:" + props.getProperty("java.ext.dirs"));
        System.out.println("操作系统的名称:" + props.getProperty("os.name"));
        System.out.println("操作系统的构架:" + props.getProperty("os.arch"));
        System.out.println("操作系统的版本:" + props.getProperty("os.version"));
        System.out.println("用户的账户名称:" + props.getProperty("user.name"));
        System.out.println("用户的主目录:" + props.getProperty("user.home"));
    }

}
