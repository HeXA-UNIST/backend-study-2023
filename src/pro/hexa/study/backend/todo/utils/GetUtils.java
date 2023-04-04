package pro.hexa.study.backend.todo.utils;

import java.util.Optional;

public class GetUtils {

    public static int getIntegerAsInt(Integer integer) {
        return Optional.ofNullable(integer)
            .orElse(0);
    }
}
