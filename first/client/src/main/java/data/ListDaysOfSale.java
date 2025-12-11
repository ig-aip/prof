package data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class ListDaysOfSale {
    List<DayOfSale> list = new ArrayList<>();
    int daysAgo;

    public ListDaysOfSale(int daysAgo){
        this.daysAgo = daysAgo;
    }

    //List { indx:0  [2025.12.10], indx: 1 [[2025.12.11], indx: 2 [2025.12.12], indx: 3 [2025.12.13]} чем больше индекс, тем ближе к сегодняшнему
    public void setArray(List<Sales> salesList){

        for(int i = 0; i < daysAgo; ++i){
            LocalDateTime date = LocalDateTime.now().minusDays(daysAgo - i);
            DayOfSale day = new DayOfSale(date);
            day.SetSalesPerDay(salesList);
            list.add(day);
        }
    }

    public DayOfSale getIndx(int indx){
        return list.get(indx);
    }

    public List<DayOfSale> getDayOfSaleList(){
        return list;
    }

    public int getDaysAgo() {
        return daysAgo;
    }
}
