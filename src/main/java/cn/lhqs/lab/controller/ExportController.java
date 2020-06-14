package cn.lhqs.lab.controller;

import cn.lhqs.lab.entity.ReturnResult;
import cn.lhqs.lab.service.ExportService;
import cn.lhqs.lab.utils.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-11-09 16:52
 * description : ExportController.class
 * version : 1.0
 */

@Controller
@RequestMapping("api")
public class ExportController {

    private static Logger logger = LoggerFactory.getLogger(ExportController.class);

    @Resource
    ExportService exportServiceImpl;

    @GetMapping(value = "/export/exportPurchase")
    public ReturnResult exportPurchase( HttpServletResponse response){
        response.setHeader("Content-Type", "application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=purchase-excel("+ TimeTools.dateFormatNow(TimeTools.DATE_TYPE1)+").xlsx");
        try {
            exportServiceImpl.exportExcelForPurchase(response.getOutputStream());
        } catch (IOException e) {
            logger.error("excel导出有误"+e);
            return ReturnResult.fail;
        }
        return ReturnResult.ok;
    }

    @GetMapping(value = "/export/exportReim")
    public ReturnResult exportReim( HttpServletResponse response){
        response.setHeader("Content-Type", "application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=reimbursement-excel("+ TimeTools.dateFormatNow(TimeTools.DATE_TYPE1)+").xlsx");
        try {
            exportServiceImpl.exportExcelForReim(response.getOutputStream());
        } catch (IOException e) {
            logger.error("excel导出有误"+e);
            return ReturnResult.fail;
        }
        return ReturnResult.ok;
    }

    /*@GetMapping(value = "/log/exportLogForCsv")
    public ReturnResult exportTransactionRecord(DataTimeSelect dataTimeSelect, HttpServletResponse response){
        logger.info("请求导出参数--->" + dataTimeSelect);
        response.setContentType("application/csv;charset=gb18030");
        response.setHeader("Content-Disposition", "attachment; filename=user-log-csv(" + TimeTools.dateFormatNow(TimeTools.DATE_TYPE1)+").csv");
        try {
            exportService.exportCsvForLog(dataTimeSelect, response.getOutputStream());
        } catch (IOException e) {
            logger.info("exportLogForCsv-error:", e);
            return ReturnResult.fail;
        }
        return ReturnResult.ok;
    }*/
}
