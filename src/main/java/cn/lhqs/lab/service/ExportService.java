package cn.lhqs.lab.service;

import java.io.IOException;
import java.io.OutputStream;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-11-09 16:53
 * description : ExportService.class
 * version : 1.0
 */
public interface ExportService {

    OutputStream exportExcelForPurchase(OutputStream outputStream) throws IOException;

    OutputStream exportExcelForReim(OutputStream outputStream) throws IOException;

    // OutputStream exportCsvForLog(DataTimeSelect dataTimeSelect, OutputStream outputStream);
}
