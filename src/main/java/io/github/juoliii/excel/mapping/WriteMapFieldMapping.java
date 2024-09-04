package io.github.juoliii.excel.mapping;

import com.aspose.cells.Cells;
import com.bitian.common.dto.ExportParams;

import java.util.List;
import java.util.Map;

/**
 * 类型是map的excel导出渲染
 * @author admin
 */
public class WriteMapFieldMapping implements WriteFieldMapping<Map<String,Object>>{

    @Override
    public void handleHeader(Cells cells, List<ExportParams.Column> columns) {
        for (int i = 0; i < columns.size(); i++) {
            cells.get(0,i).setValue(columns.get(i).getTitle());
        }
    }

    @Override
    public void mapping(Cells cells, List<ExportParams.Column> columns, int row, Map<String,Object> object) {
        for (int i = 0; i < columns.size(); i++) {
            cells.get(row,i).setValue(object.get(columns.get(i).getKey()));
        }
    }
}
