import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {
    public static void main(String[] args)  {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sf.format(date);
        System.out.println(str);
//        Date parse = sf.parse(sf.format(new Date()));
//        System.out.println(sf.format(new Date()));
//        System.out.println(parse);
    }
}
