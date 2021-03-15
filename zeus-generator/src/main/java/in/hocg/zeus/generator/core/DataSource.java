package in.hocg.zeus.generator.core;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Driver;

/**
 * Created by hocgin on 2020/5/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum DataSource {
    DEFAULT(DbType.MYSQL,
        "jdbc:mysql://39.100.87.7:30962/db_chaos_dev?useSSL=false&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&allowPublicKeyRetrieval=true",
        com.mysql.cj.jdbc.Driver.class, "root", "use_youself_config");
    private final DbType dbType;
    private final String url;
    private final Class<? extends Driver> driverName;
    private final String username;
    private final String password;
}
