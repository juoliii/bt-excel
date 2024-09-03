package io.github.juoliii.excel.mapping;

import com.aspose.cells.Cells;
import io.github.juoliii.excel.dto.ReadObject;

/**
 * @author admin
 */
public interface ReadColumnMapping {
    ReadObject mappingObject(int line, Cells cells) throws Exception;

}
