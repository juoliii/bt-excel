import com.bitian.common.util.CommonUtil;
import com.bitian.common.util.NumberUtil;
import io.github.juoliii.excel.dto.ExcelResult;
import io.github.juoliii.excel.util.ExcelUtil;
import javassist.*;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static com.aspose.cells.b.a.b.zk.cc;

/**
 * @author admin
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ClassPool.getDefault().insertClassPath("D://aspose-words-24.6-jdk17.jar");
        CtClass clazz=ClassPool.getDefault().getCtClass("com.aspose.words.zzVTV");
        clazz.getDeclaredConstructors()[0].setModifiers(Modifier.PUBLIC);
        clazz.getDeclaredConstructors()[0].insertAfter("this.zzXAm=1;this.zzYXJ=1;");
        clazz.writeFile("D://");


        CtClass clazz1=ClassPool.getDefault().getCtClass("com.aspose.words.zzWst");
        System.out.println(clazz1.getClassInitializer());
        clazz1.getClassInitializer().insertAfter("{" +
                "zzZXG = new java.util.ArrayList(); " +
                "com.aspose.words.zzVTV tmp=new com.aspose.words.zzVTV(null,null);" +
                "zzZXG.add(tmp);" +
                "zzZVe = new Object();zzZcF = new Object();zzYQo = 0L;zzXXZ = 7133812862090241280L;"+
                "zzVQH = com.aspose.words.internal.zzYea.zzWkC().zzWd(new byte[]{121, 121, 121, 121, 77, 77, 100, 100});"+
                "}"
        );
//        clazz1.getDeclaredMethod("zzWJL").setBody("{return true;}");
        clazz1.writeFile("D://");

        CtClass clazz2=ClassPool.getDefault().getCtClass("com.aspose.words.zzYfR");
        clazz2.getDeclaredMethod("zzYqm").setBody("{return 256;}");
        clazz2.getDeclaredMethod("zzWGj").setBody("{return 128;}");
        clazz2.writeFile("D:/");

//        ClassPool.getDefault().insertClassPath("D://aspose-words-24.6-jdk17.jar");
//        CtClass clazz=ClassPool.getDefault().getCtClass("com.aspose.words.zzYzt");
//        CtMethod[] methodA=clazz.getDeclaredMethods();
//        for (CtMethod ctMethod : methodA) {
//            CtClass[] ps= ctMethod.getParameterTypes();
//            if(ps.length==4 && ctMethod.getName().equals("zzWdb")
//            && ps[0].isArray() && ps[1].isArray() && ps[2].isArray() && ps[3].isArray()
//            && ps[0].getComponentType()==CtClass.byteType
//            && ps[1].getComponentType()==CtClass.byteType
//            && ps[2].getComponentType()==CtClass.byteType
//            && ps[3].getComponentType()==CtClass.byteType){
//                ctMethod.setBody("return true;");
//            }
//        }
//        clazz.writeFile("D://");
//
//        CtClass zz2OClazz=ClassPool.getDefault().getCtClass("com.aspose.words.zz2O");
//        CtMethod[] zz2OClazzMethod=zz2OClazz.getDeclaredMethods();
//        for (CtMethod ctMethod : zz2OClazzMethod) {
//            CtClass[] ps= ctMethod.getParameterTypes();
//            if (ps.length==0 && ctMethod.getName().equals("zzWlq")){
//                ctMethod.setBody("return 256;");
//            }
//        }
//        zz2OClazz.writeFile("D://");
//
//        CtClass zzL7Clazz=ClassPool.getDefault().getCtClass("com.aspose.words.zzL7");
//        CtMethod[] zzL7ClazzMethod=zz2OClazz.getDeclaredMethods();
//        for (CtMethod ctMethod : zzL7ClazzMethod) {
//            CtClass[] ps= ctMethod.getParameterTypes();
//            if (ps.length==0 && ctMethod.getName().equals("zzX30")){
//                ctMethod.setBody("{if(zzXbU==0L){ zzXbU^=zzW7B;} return com.aspose.words.zzW4v.zzZ4h; }");
//            }
//        }
//        zzL7Clazz.writeFile("D://");


    }
}
