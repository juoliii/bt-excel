package io.github.juoliii.excel.mapping;

import com.alibaba.fastjson.JSONObject;
import com.aspose.cells.Cells;
import com.bitian.common.dto.ExportParams;
import io.github.juoliii.excel.dict.AbstractDict;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 */
public class WriteDefaultWriteFieldMapping<T> implements WriteFieldMapping<T> {

    private Map<String,List<? extends AbstractDict>> dictMap;

    public Object getValue(Object object,String prop){
        try{
            Object tmpValue= FieldUtils.readDeclaredField(object,prop,true);
            return tmpValue;
        }catch (Exception e){
        }
        return null;
    }

    @Override
    public void handleHeader(Cells cells, List<ExportParams.Column> columns) {
        for (int i = 0; i < columns.size(); i++) {
            cells.get(0,i).setValue(columns.get(i).getTitle());
        }
    }

    @Override
    public void mapping(Cells cells, List<ExportParams.Column> columns, int row, T t) {
        //动态字段数据
        JSONObject json=new JSONObject();
        try {
            Field field = FieldUtils.getDeclaredField(t.getClass(), "data", true);
            if (field != null) {
                json = JSONObject.parseObject(field.get(t).toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        for (int i = 0; i < columns.size(); i++) {
            ExportParams.Column column=columns.get(i);
            Object object=null;
            if(column.getDynamic()){
                object=json.get(column.getKey());
            }else{
                try{
                    if(StringUtils.contains(column.getKey(),".")){
                        //嵌套属性
                        String props[]=StringUtils.split(column.getKey(),".");
                        Object origin=t;
                        for (int j = 0; j < props.length; j++) {
                            String prop=props[j];
                            object=this.getValue(origin,prop);
                            if(object!=null){
                                origin=object;
                            }else{
                                object=null;
                                break;
                            }
                        }
                    }else{
                        //直接属性
                        object=FieldUtils.readDeclaredField(t,column.getKey(),true);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(StringUtils.isNotBlank(column.getDict())){
                final Object tmpValue=object==null?"":object.toString();
                object= AbstractDict.details(dictMap,column.getDict()).stream().filter(tmp->tmp.getValue().equals(tmpValue)).findFirst().map(t1->t1.getLabel()).orElse("");
            }
            cells.get(row,i).setValue(object);
        }
    }
}
