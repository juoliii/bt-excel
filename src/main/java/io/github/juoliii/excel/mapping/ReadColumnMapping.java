package io.github.juoliii.excel.mapping;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import io.github.juoliii.excel.dto.ReadResult;

/**
 * @author admin
 */
public interface ReadColumnMapping {
    ReadResult mappingObject(int line, Cells cells) throws Exception;

}
