import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DTOMain {
    public static void main(String[] args) {

        List<TestDTO> list = new ArrayList<>();

        TestDTO test = new TestDTO();
        test.setExeAmt(new BigDecimal("20000"));
        test.setAdminIdx("2");
        list.add(test);

        TestDTO test2= new TestDTO();
        test2.setExeAmt(new BigDecimal("20000"));
        test2.setAdminIdx("1");
        list.add(test2);

        TestDTO test3 = new TestDTO();
        test3.setExeAmt(new BigDecimal("20000"));
        test3.setAdminIdx("2");
        list.add(test3);

        TestDTO test4 = new TestDTO();
        test4.setExeAmt(new BigDecimal("20000"));
        test4.setAdminIdx("2");
        list.add(test4);

        List<BigDecimal> collect = list.stream().filter(testDTO -> testDTO.getAdminIdx().equals("2")).map(r -> r.getExeAmt().multiply(new BigDecimal("3"))).collect(Collectors.toList());
        System.out.println(collect);
        Stream<BigDecimal> bigDecimalStream = list.stream().filter(testDTO -> testDTO.getAdminIdx().equals("1")).map(r -> r.getExeAmt().multiply(new BigDecimal("3")));

        BigDecimal reduce = bigDecimalStream.reduce(BigDecimal.ZERO, (s1, s2) -> s1.add(s2));

        System.out.println(reduce);


    }

    static class TestDTO {
        private String adminIdx;
        private BigDecimal exeAmt;

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
    }
}
