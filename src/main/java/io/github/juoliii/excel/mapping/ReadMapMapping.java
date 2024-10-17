package io.github.juoliii.excel.mapping;

import com.aspose.cells.Cell;
import com.aspose.cells.CellValueType;
import com.aspose.cells.Cells;
import com.bitian.common.exception.CustomException;
import com.bitian.common.util.NumberUtil;
import io.github.juoliii.excel.annotation.ImportExcel;
import io.github.juoliii.excel.dto.ReadObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author admin
 */
public class ReadMapMapping implements ReadColumnMapping {

    Map<Integer, String> columnMap = new HashMap<>();

    public ReadMapMapping(Cells cells){
        for (int i = 0; i <=cells.getMaxColumn(); i++) {
            columnMap.put(i, cells.get(0, i).getStringValue());
        }
    }

    @Override
    public ReadObject mappingObject(int line, Cells cells) throws Exception {
        Map<String,Object> map=new LinkedHashMap<>();
        for (int col = 0; col <= cells.getMaxColumn(); col++) {
            String key = columnMap.get(col);
            Object value = cells.get(line, col).getValue();
            map.put(key, value);
        }
        return new ReadObject(map,"");
    }

}
