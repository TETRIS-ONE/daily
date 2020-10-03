package com.daily.jaspersoft;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//基本测试代码
@RestController
public class JasperController {

    @GetMapping("/testJasper")
    public void createHtml(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //引入jasper文件。由JRXML模板编译生成的二进制文件，用于代码填充数据
        Resource resource = new ClassPathResource("templates/payroll.jasper");
        //加载jasper文件创建inputStream
        FileInputStream isRef = new FileInputStream(resource.getFile());
        ServletOutputStream sosRef = response.getOutputStream();
        try {
            //创建JasperPrint对象
            /**
             * 第一个参数:输入流 第二个参数:传的文本值, 第三个参数JREmptyDataSource
             */
            Map<String, Object> map = new HashMap<>();
            map.put("fdDepartment","技术部");
            map.put("fdPayPeriod","Oct 2020");
            map.put("fdDepartment","技术部");
            map.put("fdEmployee","沧海一粟");
            map.put("fdPrintDate","02.10.2020");
            map.put("fdSubGroup","ML");
            map.put("fdDateJoin","25.07.2020");
            map.put("fdUseDaysInMonth","3");
            map.put("fdAnnualDays","5");
            map.put("fdStaffNo","EXCUTEME");

            //组装list数据源
            List<AdditionField> addList = new ArrayList<AdditionField>();
            for(int i = 1 ; i <= 3; i++) {
                AdditionField fields = new AdditionField(
                        "add_field"+i,
                        new BigDecimal(i+1),
                        new BigDecimal(i+2));
                addList.add(fields);
            }

            List<DeductionField> deducList = new ArrayList<DeductionField>();
            for(int i = 1 ; i <= 5; i++) {
                DeductionField fields = new DeductionField(
                        "deduc_field"+i,
                        new BigDecimal(i+1),
                        new BigDecimal(i+2));
                deducList.add(fields);
            }
            ModelTableSource mts = new ModelTableSource();
            mts.setFdAdditions(new JRBeanCollectionDataSource(addList));
            mts.setFdDeductions(new JRBeanCollectionDataSource(deducList));

            List<ModelTableSource> mlist = new ArrayList<ModelTableSource>();
            mlist.add(mts);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(isRef, map,new JREmptyDataSource());
            JasperPrint jasperPrint = JasperFillManager.fillReport(isRef, map, new JRBeanCollectionDataSource(mlist));
            //写入pdf数据
            JasperExportManager.exportReportToPdfStream(jasperPrint,sosRef);
            response.setContentType("application/pdf");
        } finally {
            sosRef.flush();
            sosRef.close();
        }
    }

}
