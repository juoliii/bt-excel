package io.github.juoliii.excel.dict;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author admin
 */
public interface AbstractDict {

    String getLabel();
    String getValue();
    String getCodeType();

    static List<? extends AbstractDict> details(Map<String,List<? extends AbstractDict>> dictMap, String codeType){
        if(dictMap==null){
            return Collections.emptyList();
        }
        return dictMap.get(codeType);
    }
}
