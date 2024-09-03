import io.github.juoliii.excel.dto.ExcelResult;
import io.github.juoliii.excel.util.ExcelUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;

/**
 * @author admin
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        ExcelResult<TestData> result=ExcelUtil.readExcel(new FileInputStream("D://test.xlsx"),TestData.class);
//        System.out.println(result.getDatas());
        String [] array=StringUtils.split("/Business/NMUjuncheng_CGP/240819_A00133_1017_BHCTJKDSXC","/");
        System.out.println(array[array.length-1]);
    }
}
