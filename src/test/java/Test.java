import com.bitian.common.util.CommonUtil;
import com.bitian.common.util.NumberUtil;
import io.github.juoliii.excel.dto.ExcelResult;
import io.github.juoliii.excel.util.ExcelUtil;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.Map;

/**
 * @author admin
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ClassPool.getDefault().insertClassPath("D://aspose-words-24.3-jdk17.jar");
        CtClass clazz=ClassPool.getDefault().getCtClass("com.aspose.words.zzYzt");
        CtMethod[] methodA=clazz.getDeclaredMethods();
        for (CtMethod ctMethod : methodA) {
            CtClass[] ps= ctMethod.getParameterTypes();
            if(ps.length==4 && ctMethod.getName().equals("zzWdb")
            && ps[0].isArray() && ps[1].isArray() && ps[2].isArray() && ps[3].isArray()
            && ps[0].getComponentType()==CtClass.byteType
            && ps[1].getComponentType()==CtClass.byteType
            && ps[2].getComponentType()==CtClass.byteType
            && ps[3].getComponentType()==CtClass.byteType){
                ctMethod.setBody("return true;");
            }
        }
        clazz.writeFile("D://");

        CtClass zz2OClazz=ClassPool.getDefault().getCtClass("com.aspose.words.zz2O");
        CtMethod[] zz2OClazzMethod=zz2OClazz.getDeclaredMethods();
        for (CtMethod ctMethod : zz2OClazzMethod) {
            CtClass[] ps= ctMethod.getParameterTypes();
            if (ps.length==0 && ctMethod.getName().equals("zzWlq")){
                ctMethod.setBody("return 256;");
            }
        }
        zz2OClazz.writeFile("D://");

        CtClass zzL7Clazz=ClassPool.getDefault().getCtClass("com.aspose.words.zzL7");
        CtMethod[] zzL7ClazzMethod=zz2OClazz.getDeclaredMethods();
        for (CtMethod ctMethod : zzL7ClazzMethod) {
            CtClass[] ps= ctMethod.getParameterTypes();
            if (ps.length==0 && ctMethod.getName().equals("zzX30")){
                ctMethod.setBody("{if(zzXbU==0L){ zzXbU^=zzW7B;} return com.aspose.words.zzW4v.zzZ4h; }");
            }
        }
        zzL7Clazz.writeFile("D://");


    }
}
