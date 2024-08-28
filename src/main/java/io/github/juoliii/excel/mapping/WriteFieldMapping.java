package io.github.juoliii.excel.mapping;

import com.aspose.cells.Cells;
import com.bitian.common.dto.ExportParams;

import java.util.List;

/**
 * @author admin
 */
public interface WriteFieldMapping<T>{

    void handleHeader(Cells cells, List<ExportParams.Column> columns);

    void mapping(Cells cells, List<ExportParams.Column> columns, int row, T t);
}
