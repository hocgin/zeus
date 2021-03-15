package in.hocg.zeus.generator;


import in.hocg.zeus.generator.core.CodeGenerator;
import in.hocg.zeus.generator.core.DataSource;
import in.hocg.zeus.generator.core.Module;

/**
 * Created by hocgin on 2020/5/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class GeneratorMain {

    public static void main(String[] args) {
        //
        CodeGenerator.generateByTables(DataSource.DEFAULT,
            Module.TPL, false,
            "mina_mobile_wallpaper");
    }

}
