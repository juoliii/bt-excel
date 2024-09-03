import io.github.juoliii.excel.annotation.ImportExcel;
import lombok.Data;

/**
 * @author admin
 */
@Data
public class TestData {
    @ImportExcel(title = "LANE编号")
    private String lane;

    @ImportExcel(title="样本名称")
    private String sample;
}
