import cn.los.common.constant.ConstantClassField;
import cn.los.common.constant.ConstantClassFunction;
import cn.los.common.constant.ConstantEnum;
import cn.los.common.constant.ConstantInterface;

public class TestConstant {

    static final String day = "saturday";

    public static void main(String[] args) {
        System.out.println("是不是 saturday");
        System.out.println("接口" + day.equalsIgnoreCase(ConstantInterface.SATURDAY));
        System.out.println("枚举" + day.equalsIgnoreCase(ConstantEnum.SATURDAY.name()));
        System.out.println("字段" + day.equalsIgnoreCase(ConstantClassField.SATURDAY));
        System.out.println("方法" + day.equalsIgnoreCase(ConstantClassFunction.getSaturday()));
    }

}
