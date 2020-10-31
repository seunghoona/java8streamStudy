import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {
        List<Map<String,Object>> list = new ArrayList<>();
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("adminIdx" ,"99");
        objectObjectHashMap.put("exeAmt"  ,"200000");
        objectObjectHashMap.put("exeDt"   ,"20200101");
        list.add(objectObjectHashMap);


        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("adminIdx" ,"2");
        stringObjectHashMap.put("exeAmt"  ,"4000000000");
        stringObjectHashMap.put("exeDt"   ,"20200501");
        list.add(stringObjectHashMap);

        HashMap<String, Object> map = new HashMap<>();
        map.put("adminIdx" ,"3");
        map.put("exeAmt"  ,"3");
        map.put("exeDt"   ,"20200301");
        list.add(map);
        HashMap<String, Object> tmap = new HashMap<>();
        tmap.put("adminIdx" ,"24");
        tmap.put("exeAmt"  ,"1000");
        tmap.put("exeDt"   ,"20200201");
        list.add(tmap);

        final Comparator<Map<String,Object>> comp = (o1,o2) ->
                Integer.compare(Integer.parseInt(o1.get("adminIdx").toString()),Integer.parseInt(o2.get("adminIdx").toString()));

        List<Map<String, Object>> collect = list.stream().sorted(comp).collect(Collectors.toList());


        System.out.println(collect.toString());
        // 사이즈 제한
        List<Map<String, Object>> collect1 = collect.stream().limit(2).collect(Collectors.toList());
        System.out.println(collect1);

    }




}
