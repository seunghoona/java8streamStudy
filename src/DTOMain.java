import java.math.BigDecimal;
import java.security.cert.CollectionCertStoreParameters;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DTOMain {
    public static void main(String[] args) {

        List<TestDTO> list = new ArrayList<>();

        TestDTO test = new TestDTO();
        test.setExeAmt(new BigDecimal("20000"));
        test.setAdminIdx("10");
        test.setPositionCode("SM");
        test.setProductIdx("1");
        list.add(test);

        TestDTO test2= new TestDTO();
        test2.setExeAmt(new BigDecimal("10000"));
        test2.setAdminIdx("10");
        test2.setPositionCode("SM");
        test2.setProductIdx("1");
        list.add(test2);

        TestDTO test3 = new TestDTO();
        test3.setExeAmt(new BigDecimal("20000"));
        test3.setAdminIdx("20");
        test3.setPositionCode("TL");
        test3.setProductIdx("3");
        list.add(test3);


        TestDTO test4 = new TestDTO();
        test4.setExeAmt(new BigDecimal("20000"));
        test4.setAdminIdx("10");
        test4.setPositionCode("TL");
        test4.setProductIdx("4");
        list.add(test4);

        /*List<BigDecimal> collect = list.stream().filter(testDTO -> testDTO.getAdminIdx().equals("2")).map(r -> r.getExeAmt().multiply(new BigDecimal("3"))).collect(Collectors.toList());
        System.out.println(collect);
        Stream<BigDecimal> bigDecimalStream = list.stream().filter(testDTO -> testDTO.getAdminIdx().equals("1")).map(r -> r.getExeAmt().multiply(new BigDecimal("3")));

        BigDecimal reduce = bigDecimalStream.reduce(BigDecimal.ZERO, (s1, s2) -> s1.add(s2));

        System.out.println(reduce);*/


        Map<String, Map<String, List<TestDTO>>> collect = list.stream().collect(
                Collectors.groupingBy(TestDTO::getAdminIdx, Collectors.groupingBy(TestDTO::getProductIdx)));


        System.out.println(collect);

        List<INSERTDTO> list9 = new ArrayList<>();

        collect.entrySet().stream().forEach(r-> {

            r.getValue().entrySet().stream().forEach(s->{
                INSERTDTO insertdto = new INSERTDTO();
                s.getValue().stream().filter(distinctByKey(p->p.getProductIdx())).forEach(g->{

                    insertdto.setAdminIdx(r.getKey());
                    insertdto.setProductIdx(s.getKey());
                    insertdto.setExeAmt(g.exeAmt);
                    list9.add(insertdto);
                });


            });
        });
list9.forEach(System.out::println);

        //====

 /*       Map<String, List<TestDTO>> collect = list.stream().collect(
                Collectors.groupingBy(TestDTO::getAdminIdx));


        System.out.println(collect);

        List<INSERTDTO> list9 = new ArrayList<>();

        collect.entrySet().stream().distinct().forEach(r-> {
            System.out.println("1"+r);
           r.getValue().stream().forEach(s->{
               System.out.println(s);
           });

        });*/



  /*      for(Map.Entry<String, Map<String, Set<String>>> adminIdxMap:  collect.entrySet()){
            INSERTDTO insertdto = new INSERTDTO();
            adminIdxMap.getValue().entrySet().stream().forEach(s->{
                s.getValue().stream().forEach(g->{

                    insertdto.setAdminIdx(adminIdxMap.getKey());
                    insertdto.setProductIdx(g);
                    list9.add(insertdto);
                });


            });
        }*/



        /*collect.entrySet().stream().forEach(s->s.getValue().entrySet().stream()
                .forEach(s->s.getValue()));
*/
/*
        List<INSERTDTO> collect1 = g.getValue().stream().map(c -> {
            insertdto.setExeAmt(c.getExeAmt());
            insertdto.setAdminIdx(c.getAdminIdx());
            return insertdto;
        }).collect(Collectors.toList());
        return collect1;*/


    }

    static class TestDTO {
        private String adminIdx;
        private String productIdx;
        private BigDecimal exeAmt;
        private String positionCode;

        public String getAdminIdx() {
            return adminIdx;
        }

        public void setAdminIdx(String adminIdx) {
            this.adminIdx = adminIdx;
        }

        public BigDecimal getExeAmt() {
            return exeAmt;
        }

        public void setExeAmt(BigDecimal exeAmt) {
            this.exeAmt = exeAmt;
        }

        public String getProductIdx() {
            return productIdx;
        }

        public void setProductIdx(String productIdx) {
            this.productIdx = productIdx;
        }

        public String getPositionCode() {
            return positionCode;
        }

        public void setPositionCode(String positionCode) {
            this.positionCode = positionCode;
        }

        @Override
        public String  toString() {
            return "TestDTO{" +
                    "adminIdx='" + adminIdx + '\'' +
                    ", productIdx='" + productIdx + '\'' +
                    ", exeAmt=" + exeAmt +
                    ", positionCode='" + positionCode + '\'' +
                    '}';
        }
    }

    static class INSERTDTO {
        private String adminIdx;
        private String productIdx;
        private BigDecimal exeAmt;
        private String positionCode;

        public String getAdminIdx() {
            return adminIdx;
        }

        public void setAdminIdx(String adminIdx) {
            this.adminIdx = adminIdx;
        }

        public BigDecimal getExeAmt() {
            return exeAmt;
        }

        public void setExeAmt(BigDecimal exeAmt) {
            this.exeAmt = exeAmt;
        }

        public String getProductIdx() {
            return productIdx;
        }

        public void setProductIdx(String productIdx) {
            this.productIdx = productIdx;
        }

        public String getPositionCode() {
            return positionCode;
        }

        public void setPositionCode(String positionCode) {
            this.positionCode = positionCode;
        }

        @Override
        public String toString() {
            return "INSERTDTO{" +
                    "adminIdx='" + adminIdx + '\'' +
                    ", productIdx='" + productIdx + '\'' +
                    ", exeAmt=" + exeAmt +
                    ", positionCode='" + positionCode + '\'' +
                    '}';
        }
    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
