package sangwon.solve_it.util;

import org.springframework.http.HttpStatus;
import sangwon.solve_it.exception.CustomException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NumberedListUtil {
    static public <T extends Numbered> void validateListNumbering(List<T> list) {
        if(list == null || list.isEmpty()) throw new IllegalArgumentException("리스트의 길이가 1 이상이여야합니다.");
        int size = list.size();
        List<Boolean> checkList = new ArrayList<>(Collections.nCopies(size + 1, false));
        list.forEach((element) -> {
            int number = element.getNumber();
            if(number > size || checkList.get(number)) throw new CustomException("넘버링 오류", HttpStatus.BAD_REQUEST);
            checkList.set(number, true);
        });
    }

    static public <T extends Numbered> List<T> sortNumberedList(List<T> list) {
        List<T> listCopy = new ArrayList<>(list);
        listCopy.sort(Comparator.comparing(Numbered::getNumber));
        return listCopy;
    }
}
