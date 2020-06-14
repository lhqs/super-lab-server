package cn.lhqs.lab.service.serviceImpl;

import cn.lhqs.lab.entity.PurchaseInfo;
import cn.lhqs.lab.entity.ReimInfo;
import cn.lhqs.lab.mapper.FeedbackMapper;
import cn.lhqs.lab.service.ExportService;
import cn.lhqs.lab.utils.Constants;
import cn.lhqs.lab.utils.TimeTools;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-11-09 16:53
 * description : ExportServiceImpl.class
 * version : 1.0
 */
@Service
public class ExportServiceImpl implements ExportService{

    @SuppressWarnings("all")
    @Resource
    FeedbackMapper feedbackMapper;

    @Override
    public OutputStream exportExcelForPurchase(OutputStream outputStream) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFCellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        XSSFSheet sheet = workbook.createSheet("采购单");

        String[] headTables = {"序列","姓名","项目说明","采购项目","金额","事由","经手人","操作时间"};
        XSSFRow row = sheet.createRow(0);
        for(int index = 0; index < headTables.length; index++){
            row.createCell(index).setCellValue(headTables[index]);
        }
        List<PurchaseInfo> list = feedbackMapper.getAllPurchaseSheet();

        for(int rowIndex = 1; rowIndex < feedbackMapper.getPurchaseTotal() + 1; rowIndex++  ){
            XSSFRow subrow = sheet.createRow(rowIndex);
            subrow.createCell(0).setCellValue(rowIndex);
            subrow.createCell(1).setCellValue(list.get(rowIndex - 1).getUsername());
            subrow.createCell(2).setCellValue(list.get(rowIndex-1).getProjectIllustration());
            subrow.createCell(3).setCellValue(list.get(rowIndex-1).getPurchaseType());
            subrow.createCell(4).setCellValue(list.get(rowIndex-1).getAmount());
            subrow.createCell(5).setCellValue(list.get(rowIndex-1).getIncident());
            subrow.createCell(6).setCellValue(list.get(rowIndex-1).getTakeOver());
            subrow.createCell(7).setCellValue(TimeTools.dateFormat(list.get(rowIndex-1).getCreateTime(),TimeTools.DATE_TYPE1));
        }
        workbook.write(outputStream);
        outputStream.close();
        return outputStream;
    }

    @Override
    public OutputStream exportExcelForReim(OutputStream outputStream) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFCellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        XSSFSheet sheet = workbook.createSheet("报销单");

        String[] headTables = {"序列","姓名","项目说明","报销类目","费用","事由","经手人","操作时间"};
        XSSFRow row = sheet.createRow(0);
        for(int index = 0; index < headTables.length; index++){
            row.createCell(index).setCellValue(headTables[index]);
        }
        List<ReimInfo> list = feedbackMapper.getAllReimSheet();

        for(int rowIndex = 1; rowIndex < feedbackMapper.getReimTotal() + 1; rowIndex++  ){
            XSSFRow subrow = sheet.createRow(rowIndex);
            subrow.createCell(0).setCellValue(rowIndex);
            subrow.createCell(1).setCellValue(list.get(rowIndex - 1).getUsername());
            subrow.createCell(2).setCellValue(list.get(rowIndex - 1).getProjectIllustration());
            subrow.createCell(3).setCellValue(list.get(rowIndex - 1).getReimbursementType());
            subrow.createCell(4).setCellValue(list.get(rowIndex - 1).getAmount());
            subrow.createCell(5).setCellValue(list.get(rowIndex - 1).getIncident());
            subrow.createCell(6).setCellValue(list.get(rowIndex - 1).getTakeOver());
            subrow.createCell(7).setCellValue(TimeTools.dateFormat(list.get(rowIndex-1).getCreateTime(),TimeTools.DATE_TYPE1));
        }
        workbook.write(outputStream);
        outputStream.close();
        return outputStream;
    }

    /*@Override
    public OutputStream exportCsvForLog(DataTimeSelect dataTimeSelect, OutputStream out) {
        String tableHead = "用户名,IP,IP位置,点击页面,查询内容,操作时间,操作设备";
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        List<OperateLogRes> logListByTime = loggerService.getLogListByTime(dataTimeSelect);
        try {
            osw = new OutputStreamWriter(out, "GBK");
            bw = new BufferedWriter(osw);
            bw.append(tableHead).append("\r");

            for (int row = 0; row < logListByTime.size(); row++) {
                OperateLogRes logRes = logListByTime.get(row);
                bw.append(logRes.getUsername()).append(",");
                bw.append(logRes.getIp()).append(",");
                bw.append(logRes.getIpAddr()).append(",");
                bw.append(logRes.getClickPage()).append(",");
                bw.append("\"").append(logRes.getOperateContent()).append("\"").append(",");
                bw.append(TimeTools.dateFormat(logRes.getCreateTime(),TimeTools.DATE_TYPE1)).append(",");
                bw.append(logRes.getOrigin()).append(",");
                bw.append("\r");
            }
        } catch (Exception e) {
            logger.error("生成csv文件出错", e);
        } finally {
            try {
                if(bw != null){
                    bw.close();
                }
                if(osw != null){
                    osw.close();
                }
            } catch (IOException e) {
                logger.error("关闭文件导出有误",e);
            }
        }
        return out;
    }*/
}
