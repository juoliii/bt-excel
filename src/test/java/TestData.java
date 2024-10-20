import io.github.juoliii.excel.annotation.ImportExcel;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author admin
 */
@Data
public class TestData {
    @ImportExcel(title = "LANE编号")
    private String lane;

    @ImportExcel(title="样本名称")
    private String sample;

    public static void main(String[] args) {
        List s=new ArrayList();
        s.add("wef1");
        s.add("wef2");
        s.remove(s.size()-1);
        System.out.println(s);
    }
}
