import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Set;

public class Greedy {
    public static void main(String[] args) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        //While loop for problems. Each loop solve one of problems.
        while ((line = reader.readLine()) != null) {
            // Getting start values for input txt
            double baseCost = Integer.parseInt(line.trim());
            int cashierCount = Integer.parseInt(reader.readLine().trim());
            int maxType = Integer.parseInt(reader.readLine().trim());
            String typeLine = reader.readLine().trim();
            String[] types = typeLine.split(",\\s*");
            List<Integer> typeValue = new ArrayList<>();
            List<Cashier> cashiers = new ArrayList<>();
            // Splitting type's number from types and creating a new type list
            for (int i = 0; i < types.length; i++) {
                String t = types[i].trim();
                String typeNumber = t.split(" ")[1];
                int number = Integer.parseInt(typeNumber);
                typeValue.add(number);
            }
            //Creating cashiers
           for (int i=0; i<cashierCount; i++){
               cashiers.add(new Cashier(i,maxType));
           }
          double totalCost=0;
           //Calculating best cashier with using type values list one by one
          for (int i=0; i< types.length; i++){
              if (i!=0&&i%5==0){
                  baseCost++;
              }
              Cashier minCostCashier = null;
              double minCost = Double.MAX_VALUE;
              int cashierNumber = 0;
              //Comparing all cashiers for finding minimum cost and best cashier
              for (int j=0; j<cashiers.size();  j++) {
                  Cashier nextCashier=cashiers.get(j);
                  double costNextCashier =nextCashier.calculateCost(typeValue.get(i),baseCost);
                  if (costNextCashier < minCost) {
                      minCostCashier = nextCashier;
                      minCost=costNextCashier;
                  }
              }
              //If there is no place for current type ,our calculating function give that cashier's mincost 999.
              //If all cashier's min value 999 it means there is no cashier for this type and total cost=-1
              if (minCost == 999) {
                  totalCost = -1;
                  break;
              }
              //Finding best cashier with using cashier number from min cost cashier
              cashierNumber= minCostCashier.getCashierNumber();
              cashiers.get(cashierNumber).addTypesHistory(typeValue.get(i));
              totalCost+=minCost;
          }
            System.out.println(totalCost);
            writer.write(String.format("%.2f\n", totalCost));
        }
        writer.close();
        System.out.println("Output file created.");
    }
}


class Cashier {
    private ArrayList<Integer> typesDone = new ArrayList<>();
    private int cashierNumber;
    private int maxTypeCount;
    private Set<Integer> possibleTypes = new HashSet<>();
    private int maxType;

    public Cashier(int cashierNumber, int maxTypeCount){
        this.cashierNumber=cashierNumber;
        this.maxTypeCount=maxTypeCount;
        this.maxType = 0;
    }

    //add type history and update max type if necessary
    public void addTypesHistory(int type) {
        if (type > this.maxType) {
            this.maxType = type;
        }
        this.typesDone.add(type);
        this.possibleTypes.add(type);
    }

    public double calculateCost(int type, double baseCost){
        //checking type limit with cashier's current types
        boolean canGetType=false;
        if (possibleTypes.size()==maxTypeCount){
            if (possibleTypes.contains(type)){
                canGetType=true;
            }
            if(canGetType==false){
                return 999;
            }
        }

        //first job's cost is 0
        if(typesDone.isEmpty()){
            return 0;
        }

        //For exhaustion rule or same Type before current Type
        if (typesDone.get(typesDone.size()-1).equals(type)){
            if(typesDone.size()>1&&typesDone.get(typesDone.size()-1).equals(typesDone.get(typesDone.size()-2))){
                baseCost= baseCost*1.5;
                return baseCost;
            }
            return 0;
        }
        else{                                      //this else block for Types that different from last Type
            if (type < this.maxType) {
                baseCost = baseCost * 0.8;         //for learning curve rule
            }
            return baseCost;
        }
    }
    //cashier number for find cashier when our cashier is min cost cashier
    public int getCashierNumber(){
        return this.cashierNumber;
    }
}