import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Madzia on 2017-01-09.
 */
public class AverageExpenses{
    public AverageExpenses(List<Deputy> deputies){
        BigDecimal total = new BigDecimal(0);
        BigDecimal divisor = new BigDecimal(0);
        BigDecimal one = new BigDecimal(1);
        for(int i=0;i<deputies.size();i++)
        {
            String id=deputies.get(i).returnID();
            if(i%10==0) System.out.println(i);
            Expenses e = new Expenses(id);
            BigDecimal totalForDeputy = e.returnOneDeputyTotalExpenses();
            total = total.add(totalForDeputy);
            divisor = divisor.add(one);

        }
        //total = total.divide(divisor);
        System.out.println(total.toString());
        System.out.println(deputies.size());
    }


}
