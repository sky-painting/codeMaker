/*
import org.junit.Test;

*/
/**
 * description: TestTableName <br>
 * date: 2020/7/7 20:42 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 *//*

public class TestTableName {
    @Test
    public void testHumpTableName(){
        String resultName = "";
        String tableName1 = "room_source_equipment";
        if(!tableName1.contains("_")){
            resultName = tableName1;
        }else {
            String [] tableNameArr = tableName1.split("_");
            int length = tableNameArr.length;

            StringBuilder builder = new StringBuilder();
            builder.append(tableNameArr[0]);
            for (int i = 1;i < length;i++){
                String tag = tableNameArr[i].substring(0,1).toUpperCase().concat(tableNameArr[i].substring(1).toLowerCase());
                builder.append(tag);
            }
            resultName = builder.toString();
        }
        System.out.println("resultName = "+resultName);

    }
}
*/
