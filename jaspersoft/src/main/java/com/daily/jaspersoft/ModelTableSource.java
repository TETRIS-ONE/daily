package com.daily.jaspersoft;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ModelTableSource {

    /**
     * 注入table组件的数据源
     */
    private JRBeanCollectionDataSource fdAdditions;

    private JRBeanCollectionDataSource fdDeductions;

    public JRBeanCollectionDataSource getFdAdditions() {
        return fdAdditions;
    }

    public void setFdAdditions(JRBeanCollectionDataSource fdAdditions) {
        this.fdAdditions = fdAdditions;
    }

    public JRBeanCollectionDataSource getFdDeductions() {
        return fdDeductions;
    }

    public void setFdDeductions(JRBeanCollectionDataSource fdDeductions) {
        this.fdDeductions = fdDeductions;
    }
}
