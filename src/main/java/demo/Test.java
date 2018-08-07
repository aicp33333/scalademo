package demo;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rongpei on 2017/8/9.
 */
public class Test {
    private static Map<String, String> tableNameIndexTypeMapping = new HashMap<>();

    static {
        // QZ
        tableNameIndexTypeMapping.put("AlipayStore", "alipay_info");
        tableNameIndexTypeMapping.put("BankCardStore", "bank_card_info");
        tableNameIndexTypeMapping.put("CreditReportStore", "credit_report_info");
        tableNameIndexTypeMapping.put("CreditStore", "credit_card_info");
        tableNameIndexTypeMapping.put("MobileStore", "mobile_info");
        tableNameIndexTypeMapping.put("OpenLineStore", "basic_info");
        tableNameIndexTypeMapping.put("TaobaoStore", "taobao_info");
        // KN
        tableNameIndexTypeMapping.put("INCustomerStore", "basic_info");
        tableNameIndexTypeMapping.put("INTaobaoStore", "taobao_info");
        tableNameIndexTypeMapping.put("INMobileStore", "mobile_info");
        tableNameIndexTypeMapping.put("INCreditCardStore", "credit_card_info");
        tableNameIndexTypeMapping.put("INAlipayStore", "alipay_info");
        // NIRVANA
        tableNameIndexTypeMapping.put("UserWrapper", "basic_info");
        tableNameIndexTypeMapping.put("LocationInfoWrapper", "basic_info");
        tableNameIndexTypeMapping.put("ClientInfoWrapper", "basic_info");
        tableNameIndexTypeMapping.put("ContactInfoWrapper", "basic_info");
        tableNameIndexTypeMapping.put("UserReplenishInfoWrapper", "basic_info");
        tableNameIndexTypeMapping.put("BankCardBasicInfoWrapper", "bank_card_info");
        tableNameIndexTypeMapping.put("CreditCardBasicInfoWrapper", "credit_card_info");
        tableNameIndexTypeMapping.put("CreditCardTrsnWrapper", "credit_card_info");
        tableNameIndexTypeMapping.put("TaobaoBasicInfoWrapper", "taobao_info");
        tableNameIndexTypeMapping.put("TaobaoDetailTransBasicInfoWrapper", "taobao_info");
        tableNameIndexTypeMapping.put("TaobaoReceivedAddressWrapper", "taobao_info");
        tableNameIndexTypeMapping.put("TaobaoTransInfoWrapper", "taobao_info");
        tableNameIndexTypeMapping.put("TaobaoTransInfoAndLogisticsWrapper", "taobao_info");
        tableNameIndexTypeMapping.put("AlipayBanklistWrapper", "alipay_info");
        tableNameIndexTypeMapping.put("AlipayBasicInfoWrapper", "alipay_info");
        tableNameIndexTypeMapping.put("AlipayTransInfoWrapper", "alipay_info");
        tableNameIndexTypeMapping.put("AlipayContactsWrapper", "alipay_info");
        tableNameIndexTypeMapping.put("CreditReportAdministrativeSanctionWrapper", "credit_report_info");
        tableNameIndexTypeMapping.put("CreditReportCompulsoryExecutionWrapper", "credit_report_info");
        tableNameIndexTypeMapping.put("CreditReportCustomerInfoWrapper", "credit_report_info");
        tableNameIndexTypeMapping.put("CreditReportInfoSummaryWrapper", "credit_report_info");
        tableNameIndexTypeMapping.put("CreditReportJudgmentExecutionWrapper", "credit_report_info");
        tableNameIndexTypeMapping.put("CreditReportOwingTaxesWrapper", "credit_report_info");
        tableNameIndexTypeMapping.put("CreditReportInfoCredit60Wrapper", "credit_report_info");
        tableNameIndexTypeMapping.put("CreditReportInfoCreditNormalWrapper", "credit_report_info");
        tableNameIndexTypeMapping.put("CreditReportInfoCreditOverdueWrapper", "credit_report_info");
        tableNameIndexTypeMapping.put("CreditReportInfoHouseNormalWrapper", "credit_report_info");
        tableNameIndexTypeMapping.put("CreditReportInfoHouseOverdueWrapper", "credit_report_info");
        tableNameIndexTypeMapping.put("CreditReportInfoOtherNormalWrapper", "credit_report_info");
        tableNameIndexTypeMapping.put("CreditReportInfoOtherOverdueWrapper", "credit_report_info");
        tableNameIndexTypeMapping.put("CreditReportQueryInfoWrapper", "credit_report_info");
        tableNameIndexTypeMapping.put("MobileBasicInfoWrapper", "mobile_info");
        tableNameIndexTypeMapping.put("MobileContactsIntersectWrapper", "mobile_info");
        tableNameIndexTypeMapping.put("MobileDetailSmsWrapper", "mobile_info");
        tableNameIndexTypeMapping.put("MobileDetailVoiceWrapper", "mobile_info");
        tableNameIndexTypeMapping.put("SmsInfoWrapper", "mobile_info");
        tableNameIndexTypeMapping.put("DevicesWrapper", "device_info");
    }
    public static void main(String[] args) throws IOException {
        System.out.println("hello world !!!");

        try {
            //创建一个URL实例
            URL url = new URL("http://www.baidu.com");

            try {
                //通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
                InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is,"utf-8");

                //为字符输入流添加缓冲
                BufferedReader br = new BufferedReader(isr);
                String data = br.readLine();//读取数据

                while (data!=null){//循环读取数据
                    System.out.println(data);//输出数据
                    data = br.readLine();
                }
                br.close();
                isr.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    }

