package com.liw.elasticsearch.test;

import java.io.*;

/**
 * 读取文件生成sql
 */
public class generateSql {

    private static final String origin = "d:/test/123.txt";

    private static final String result = "d:/test/newSql.txt";

    
    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(origin)));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(result));
//        String lineText = null;
//        String sql ="";
//        while ((lineText = bufferedReader.readLine()) != null){
//               String r =lineText.trim();
//            //   sql ="select * from t_settlement where SELLER_CODE = '"+r+"';";
//            //   sql ="select * from t_settlement_item where settlement_id in (select settlement_id from t_settlement where SELLER_CODE = '"+r+"');";
//           //     sql ="select * from t_seller_invoice where SETTLEMENT_ID in (select SETTLEMENT_ID from t_settlement where SELLER_CODE = '"+r+"');";
//               sql="select * from t_seller_invoice_item where INVOICE_ID in (select INVOICE_ID from t_seller_invoice where SETTLEMENT_ID in (select  SETTLEMENT_ID from t_settlement where SELLER_CODE = '"+r+"'));";
//
//
//
//            bufferedWriter.write(sql);
//            bufferedWriter.newLine();
//
//        }
//        bufferedReader.close();
//        bufferedWriter.close();
        generrateBatchSql();
    }

    /**
     * 生成insert 语句
     */
    public static void generrateBatchSql() throws IOException {
        String sql="INSERT INTO `imsc`.`tibin90` (`invoice_id`, `update_time`, `BUSINESS_BILL_TYPE`,`short_taxcode_status`) VALUES ";
        String vlaue1=   "('ID', '20180816541343999','ceme','0')";
        String vlaue2=   ",('ID','20180816541343999','ceme','0')";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(result));
        for (int i=1100000;i<2000000;i++){
            if (i==1100000){
                bufferedWriter.write(sql);
                bufferedWriter.write(vlaue1.replace("ID",i+""));
            }else {
                bufferedWriter.write(vlaue2.replace("ID",i+""));
            }
            bufferedWriter.newLine();

        }
        bufferedWriter.close();



    }




}
