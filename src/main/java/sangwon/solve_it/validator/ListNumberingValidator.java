package sangwon.solve_it.validator;

import org.springframework.http.HttpStatus;
import sangwon.solve_it.exception.CustomException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListNumberingValidator {
    static public <T extends Numbered> void validateListNumbering(List<T> list) {
        int size = list.size();
        List<Boolean> checkList = new ArrayList<>(Collections.nCopies(size + 1, false));
        list.forEach((element) -> {
            int number = element.getNumber();
            if(number > size || checkList.get(number)) throw new CustomException("넘버링 오류", HttpStatus.BAD_REQUEST);
            checkList.set(number, true);
        });
    }
}
