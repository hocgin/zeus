package in.hocg.zeus.chaos.biz.apiimpl;

import cn.hutool.core.convert.Convert;
import in.hocg.boot.named.ifc.NamedArgs;
import in.hocg.boot.utils.LangUtils;
import in.hocg.zeus.chaos.api.ChaosNamedApi;
import in.hocg.zeus.com.api.DataDictServiceApi;
import in.hocg.zeus.com.api.pojo.vo.DataDictItemVo;
import in.hocg.zeus.ums.api.UserServiceApi;
import in.hocg.zeus.ums.api.pojo.vo.AccountVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2020/11/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class ChaosNamedApiImpl implements ChaosNamedApi {
    private final DataDictServiceApi dataDictServiceApi;
    private final UserServiceApi userServiceApi;

    @Override
    public Map<String, Object> loadByDataDict(NamedArgs args) {
        final String type = args.getArgs()[0];
        List<String> values = args.getValues();
        final List<DataDictItemVo> result = dataDictServiceApi.listDataDictItemVoByDictIdAndCode(type, values);
        return this.toMap(result, DataDictItemVo::getCode, DataDictItemVo::getTitle);
    }

    @Override
    public Map<String, Object> loadByUserName(NamedArgs args) {
        List<Long> values = getValues(args.getValues(), Long.class);
        final List<AccountVo> result = userServiceApi.listAccountVoById(values);
        return this.toMap(result, AccountVo::getId, AccountVo::getUsername);
    }

    @Override
    public Map<String, Object> loadByNickname(NamedArgs args) {
        List<Long> values = getValues(args.getValues(), Long.class);
        final List<AccountVo> result = userServiceApi.listAccountVoById(values);
        return this.toMap(result, AccountVo::getId, AccountVo::getNickname);
    }

    private <T> List<T> getValues(List<?> values, Class<T> clazz) {
        return values.parallelStream().map(o -> Convert.convert(clazz, o)).collect(Collectors.toList());
    }

    private <K, V, Z> Map<String, Z> toMap(List<V> values,
                                           Function<? super V, K> keyFunction,
                                           Function<? super V, Z> valueFunction) {
        return LangUtils.toMap(values, v -> String.valueOf(keyFunction.apply(v)), valueFunction);
    }
}
