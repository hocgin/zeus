package in.hocg.zeus.ums.biz.helper;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import in.hocg.boot.utils.ClassUtils;
import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.util.Strings;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Created by hocgin on 2021/1/31
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class MyBatisPlusHelper {

    public String getInsertSql(Model<?> model) {
        String insertSql = "INSERT INTO %s (%s) VALUES (%s);\n";
        StringJoiner fieldsJoiner = new StringJoiner(",");
        StringJoiner valuesJoiner = new StringJoiner(",");
        Class<? extends Model> modelClass = model.getClass();
        TableName tableName = modelClass.getAnnotation(TableName.class);
        for (Field field : ClassUtils.getAllField(modelClass)) {
            TableField tableField = field.getAnnotation(TableField.class);
            TableId idField = field.getAnnotation(TableId.class);
            String fieldName = null;
            if (Objects.nonNull(tableField)) {
                fieldName = tableField.value();
            }
            if (Objects.nonNull(idField)) {
                fieldName = idField.value();
            }
            if (Strings.isBlank(fieldName)) {
                continue;
            }

            fieldsJoiner.add(fieldName);

            Object fieldValue = ClassUtils.getFieldValue(model, field, null);
            if (Objects.isNull(fieldValue)) {
                valuesJoiner.add("null");
            } else if (fieldValue instanceof String) {
                valuesJoiner.add(String.format("'%s'", fieldValue));
            } else if (fieldValue instanceof LocalDateTime) {
                valuesJoiner.add(String.format("'%s'", fieldValue));
            } else if (fieldValue instanceof Boolean) {
                valuesJoiner.add(Boolean.TRUE.equals(fieldValue) ? "1" : "0");
            } else {
                valuesJoiner.add(String.valueOf(fieldValue));
            }
        }


        return String.format(insertSql, tableName.value(), fieldsJoiner.toString(), valuesJoiner.toString());
    }

}
