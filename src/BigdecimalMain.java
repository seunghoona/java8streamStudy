import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BigdecimalMain {


    public static void main(String[] args) {

        List<Map<String,Object>> list = new ArrayList<>();

        HashMap<String, Object> map = new HashMap<>();

        map.put("amt", new BigDecimal("32"));
        map.put("adminIdx", new BigDecimal("32"));
        list.add(map);

        HashMap<String, Object> tmap = new HashMap<>();

        tmap.put("amt", new BigDecimal("32"));
        tmap.put("adminIdx", new BigDecimal("32"));
        list.add(tmap);

        BigDecimal amt = list.stream().map(s -> (BigDecimal) s.get("amt")).reduce(BigDecimal.ZERO, BigDecimal::add);


        System.out.println(amt);


    }



}
